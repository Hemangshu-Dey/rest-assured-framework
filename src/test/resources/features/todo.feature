@Authenticated
Feature: Todo APIs

  @CategoryExists
  Scenario: Verify Create Todo Category

    Given Create Todo Category Payload
    When User Calls "createTodoCategoryApi" with "Post" http request
    Then The API call got success with status code 200
    And "message" in response body is "Category created successfully"
    And Store "data.id" from response as "categoryId"

  @CategoryExists
  Scenario: Verify Get Todo Categories

    Given Get Todo Categories Payload
    When User Calls "getTodoCategoryApi" with "Get" http request
    Then The API call got success with status code 200
    And "message" in response body is "Categories fetched successfully"

  @CategoryExists
  Scenario: Verify Delete Todo Category

    Given Delete Todo Category Payload
    When User Calls "deleteTodoCategoryApi" with "Delete" http request
    Then The API call got success with status code 200
    And "message" in response body is "Category deleted successfully"

  @CategoryExists
  Scenario: Verify Create Todo

    Given Create Todo Payload
    When User Calls "createTodoApi" with "Post" http request
    Then The API call got success with status code 200
    And "message" in response body is "Todos added successfully"
    And Store "data.id" from response as "todoId"

  @CategoryExists
  @TodoExists
  Scenario: Verify Get Todo

    Given Get Todo Payload
    When User Calls "getTodoApi" with "Get" http request
    Then The API call got success with status code 200
    And "message" in response body is "ToDos fetched successfully"

  @CategoryExists
  @TodoExists
  Scenario: Verify Toggle Todo

    Given Toggle Todo Payload
    When User Calls "toggleTodoApi" with "Patch" http request
    Then The API call got success with status code 200
    And "message" in response body is "ToDo completion toggled successfully"

  @CategoryExists
  @TodoExists
  Scenario: Verify Delete Todo

    Given Delete Todo Payload
    When User Calls "deleteTodoApi" with "Delete" http request
    Then The API call got success with status code 200
    And "message" in response body is "Todo deleted successfully"