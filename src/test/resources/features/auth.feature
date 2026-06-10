Feature: Validating Auth APIs

  Scenario Outline: Verify register API
    Given Register Payload with "<name>" "<email>" "<password>"
    When User Calls "registerUserApi" with "Post" http request
    Then The API call got success with status code <status>
#    And "status" in response body is "<responseStatus>"
#    And "email" in response body is "<email>"

    Examples:
      | name    | email             | password  | status | responseStatus |
      | Tester1 | tester1@gmail.com | 1234@Asdf | 200    | OK             |
