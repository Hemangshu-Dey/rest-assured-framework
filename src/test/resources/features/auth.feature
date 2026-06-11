Feature: Validating Auth APIs

  Scenario Outline: Verify Register API - <testCase>
    Given Register Payload with "<name>" "<email>" "<password>"
    When User Calls "registerUserApi" with "Post" http request
    Then The API call got success with status code <status>
    And "message" in response body is "<message>"

    Examples:
      | testCase           | name       | email               | password   | status | message                      |
      | Valid Registration | Tester45  | tester45@gmail.com | 1234@Asdf  | 200    | User registered successfully |

  Scenario Outline: Verify Login API - <testCase>
    Given Login Payload with "<identifier>" "<password>"
    When User Calls "loginUserApi" with "Post" http request
    Then The API call got success with status code <status>
    And "message" in response body is "<message>"

    Examples:
      | testCase            | identifier  | password  | status | message                     |
      | Valid Registration  | Tester45   | 1234@Asdf | 200    | User logged in successfully |