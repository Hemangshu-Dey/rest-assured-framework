package stepDefinitions;

import enums.AuthResources;
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

    @Before("@Authenticated")
    public void authenticateUser() {

        RequestSpecification registerRequest = given()
                .spec(SpecBuilder.getRequestSpec())
                .log().all()
                .body(testData.registerUserPayload());

        Response registerResponse = registerRequest
                .when()
                .post(AuthResources.registerUserApi.getResource());

        assertEquals(200, registerResponse.getStatusCode());

        RequestSpecification loginRequest = given()
                .spec(SpecBuilder.getRequestSpec())
                .log().all()
                .body(testData.loginUserPayload());

        Response loginResponse = loginRequest
                .when()
                .post(AuthResources.loginUserApi.getResource());

        assertEquals(200, loginResponse.getStatusCode());

        testContext.setCookies(
                loginResponse.getDetailedCookies()
        );

        testContext.setUserId(
                loginResponse
                        .jsonPath()
                        .getString("data.id")
        );
    }
}