package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res=given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @When("User Calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        resSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response =res.when().post("/maps/api/place/add/json").
                then().spec(resSpec).extract().response();
    }
    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(200, response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(expectedValue,js.get(keyValue).toString());
    }
}
