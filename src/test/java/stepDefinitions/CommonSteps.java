package stepDefinitions;

import enums.AuthResources;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.TestContext;

import static org.junit.Assert.assertEquals;

public class CommonSteps {

    private final TestContext testContext;

    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("User Calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        RequestSpecification res = testContext.getRequestSpecification();

        AuthResources apiResource = AuthResources.valueOf(resource);

        Response response;

        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(apiResource.getResource());
        }
        else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(apiResource.getResource());
        }
        else if (method.equalsIgnoreCase("PUT")) {
            response = res.when().put(apiResource.getResource());
        }
        else if (method.equalsIgnoreCase("DELETE")) {
            response = res.when().delete(apiResource.getResource());
        }
        else {
            throw new IllegalArgumentException("Unsupported HTTP Method");
        }

        testContext.setResponse(response);
    }

    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {

        assertEquals(
                expectedStatusCode.intValue(),
                testContext.getResponse().getStatusCode()
        );
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {

        assertEquals(
                expectedValue,
                testContext.getResponse()
                        .jsonPath()
                        .getString(key)
        );
    }
}