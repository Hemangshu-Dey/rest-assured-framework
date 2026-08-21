package stepDefinitions;

import enums.ApiResources;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testData.testDataBuilder;
import utils.SpecBuilder;
import utils.TestContext;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Hooks {

    private final TestContext testContext;
    private final testDataBuilder testData = new testDataBuilder();

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before(value = "@Authenticated", order = 1)
    public void authenticateUser() {

        // Register User
        RequestSpecification registerRequest =
                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .body(testData.registerUserPayload());

        Response registerResponse =
                registerRequest
                        .when()
                        .post(ApiResources.registerUserApi.getResource());

        assertEquals(200, registerResponse.getStatusCode());

        // Login User
        RequestSpecification loginRequest =
                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .body(testData.loginUserPayload());

        Response loginResponse =
                loginRequest
                        .when()
                        .post(ApiResources.loginUserApi.getResource());

        assertEquals(200, loginResponse.getStatusCode());

        testContext.setCookies(
                loginResponse.getDetailedCookies()
        );

        testContext.put(
                "userId",
                loginResponse.jsonPath().getString("data.id")
        );
    }

    @Before(value = "@CategoryExists", order = 2)
    public void createCategory() {

        RequestSpecification request =
                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .cookies(testContext.getCookies())
                        .body(testData.createTodoCategoryPayload());

        Response response =
                request
                        .when()
                        .post(ApiResources.createTodoCategoryApi.getResource());

        assertEquals(200, response.getStatusCode());

        testContext.put(
                "categoryId",
                response.jsonPath().getString("data.id")
        );
    }

    @Before(value = "@TodoExists", order = 3)
    public void createTodo() {

        RequestSpecification request =
                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .cookies(testContext.getCookies())
                        .body(
                                testData.createTodoPayload(
                                        testContext.get("categoryId")
                                )
                        ).log().all();;

        Response response =
                request
                        .when()
                        .post(ApiResources.createTodoApi.getResource());

        assertEquals(200, response.getStatusCode());

        testContext.put(
                "todoId",
                response.jsonPath().getString("data.id")
        );
    }
}