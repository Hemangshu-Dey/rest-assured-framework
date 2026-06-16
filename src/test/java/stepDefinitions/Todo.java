package stepDefinitions;

import io.cucumber.java.en.Given;
import testData.testDataBuilder;
import utils.SpecBuilder;
import utils.TestContext;

import static io.restassured.RestAssured.given;

public class Todo {

    private final TestContext testContext;
    private final testDataBuilder testData = new testDataBuilder();

    public Todo(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Get Todo Categories Payload")
    public void get_todo_categories_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .cookies(testContext.getCookies())
                        .log().all()

        );
    }

    @Given("Create Todo Category Payload")
    public void create_todo_category_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .cookies(testContext.getCookies())
                        .body(testData.createTodoCategoryPayload())
                        .log().all()

        );
    }

    @Given("Delete Todo Category Payload")
    public void delete_todo_category_payload() {

        testContext.setRequestSpecification(

                given()
                        .spec(SpecBuilder.getRequestSpec())
                        .cookies(testContext.getCookies())
                        .queryParam("id", testContext.getCategoryId())
                        .log().all()

        );
    }

}