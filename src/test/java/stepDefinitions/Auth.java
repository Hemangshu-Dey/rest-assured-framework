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
                                        testContext.get("userId")
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
}