package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import testData.testDataBuilder;
import utils.SpecBuilder;
import utils.TestContext;

import static io.restassured.RestAssured.given;

public class Auth {

    private final TestContext testContext;

    private final testDataBuilder testData = new testDataBuilder();

    public Auth(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Register Payload")
    public void register_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .log().all()
                        .body(testData.registerUserPayload())

        );
    }

    @Given("Login Payload")
    public void login_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .log().all()
                        .body(testData.loginUserPayload())

        );
    }

    @Given("Logout Payload")
    public void logout_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .log().all()
                        .body(
                                testData.logoutUserPayload(
                                        testContext.getUserId()
                                )
                        )

        );
    }

    @Then("Store authentication cookies")
    public void store_authentication_cookies() {

        testContext.setCookies(
                testContext.getResponse().getDetailedCookies()
        );
    }

    @Then("Store {string} from response as {string}")
    public void store_from_response_as(String jsonPath, String variable) {

        if(variable.equalsIgnoreCase("userId")){

            testContext.setUserId(
                    testContext.getResponse()
                            .jsonPath()
                            .getString(jsonPath)
            );

        }
        else if(variable.equalsIgnoreCase("categoryId")){

            testContext.setCategoryId(
                    testContext.getResponse()
                            .jsonPath()
                            .getString(jsonPath)
            );

        }
    }
}