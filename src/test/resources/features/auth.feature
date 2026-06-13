Feature: Validating Auth APIs

  Scenario Outline: Verify Register API - <testCase>
    Given Register Payload with "<name>" "<email>" "<password>"
    When User Calls "registerUserApi" with "Post" http request
    Then The API call got success with status code <status>
    And "message" in response body is "<message>"

    Examples:
      | testCase           | name       | email               | password   | status | message                      |
      | Valid Registration | Tester4  | tester4@gmail.com | 1234@Asdf  | 200    | User registered successfully |

  Scenario Outline: Verify Login API - <testCase>
    Given Login Payload with "<identifier>" "<password>"
    When User Calls "loginUserApi" with "Post" http request
    Then The API call got success with status code <status>
    And "message" in response body is "<message>"
    Then Store "data.id" from response as "userId"

    Examples:
      | testCase            | identifier  | password  | status | message                     |
      | Valid Registration  | Tester4   | 1234@Asdf | 200    | User logged in successfully |

  Scenario: Verify Logout API
    Given Logout Payload
    When User Calls "logoutUserApi" with "Post" http request
    Then The API call got success with status code 200
    And "message" in response body is "User logged out successfully"