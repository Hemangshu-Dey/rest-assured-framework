@Authenticated
Feature: Validate Access Token API

  Scenario: Verify access token is valid

    Given Validation Payload
    When User Calls "validateAccessTokenApi" with "Get" http request
    Then The API call got success with status code 200
    And "message" in response body is "Validated successfully"

  Scenario: Verify refresh token generates new access token

    Given Validation Payload
    When User Calls "refreshAccessTokenApi" with "Get" http request
    Then The API call got success with status code 200
    And "message" in response body is "User authorized successfully"
    And Update authentication cookies