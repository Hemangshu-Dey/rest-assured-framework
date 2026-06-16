package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.SpecBuilder;
import utils.TestContext;

import static io.restassured.RestAssured.given;

public class Validation {

    private final TestContext testContext;

    public Validation(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Validation Payload")
    public void validation_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .cookies(testContext.getCookies())
                        .log().all()

        );
    }

    @Then("Update authentication cookies")
    public void update_authentication_cookies() {
        testContext.setCookies(
                testContext.getResponse().getDetailedCookies()
        );
    }
}