package stepDefinitions;

import enums.AuthResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testData.testDataBuilder;
import utils.SpecBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Auth {

    RequestSpecification res;
    testDataBuilder testData = new testDataBuilder();
    Response response;
    private static String userId;
    @Given("Register Payload")
    public void register_payload() {

        res = given()
                .spec(SpecBuilder.getRequestSpec())
                .log().all()
                .body(testData.registerUserPayload());
    }

    @Given("Login Payload")
    public void login_payload() {

        res = given()
                .spec(SpecBuilder.getRequestSpec())
                .log().all()
                .body(testData.loginUserPayload());
    }

    @Given("Logout Payload")
    public void logout_payload() {

        res = given()
                .spec(SpecBuilder.getRequestSpec())
                .log().all()
                .body(testData.logoutUserPayload(userId));
    }

    @When("User Calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        AuthResources apiResource = AuthResources.valueOf(resource);
        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(apiResource.getResource());
        }
    }

    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {

        assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        String actualValue = response.jsonPath().getString(key);
        assertEquals(expectedValue, actualValue);
    }

    @Then("Store {string} from response as {string}")
    public void store_from_response_as(String jsonPath, String variable) {

        if (variable.equals("userId")) {
            userId = response.jsonPath().getString(jsonPath);
        }
    }
}
