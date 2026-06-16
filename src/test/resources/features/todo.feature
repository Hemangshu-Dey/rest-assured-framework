@Authenticated
Feature: Todo Category APIs

  Scenario: Verify Create Todo Category

    Given Create Todo Category Payload
    When User Calls "createTodoCategoryApi" with "Post" http request
    Then The API call got success with status code 200
    And "message" in response body is "Category created successfully"
    And Store "data.id" from response as "categoryId"

  Scenario: Verify Get Todo Categories

    Given Get Todo Categories Payload
    When User Calls "getTodoCategoryApi" with "Get" http request
    Then The API call got success with status code 200
    And "message" in response body is "Categories fetched successfully"

  @Authenticated
  @CategoryExists
  Scenario: Verify Delete Todo Category

    Given Delete Todo Category Payload
    When User Calls "deleteTodoCategoryApi" with "Delete" http request
    Then The API call got success with status code 200
    And "message" in response body is "Category deleted successfully"