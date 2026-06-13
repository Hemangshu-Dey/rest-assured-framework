Feature: Validating Auth APIs

  Scenario Outline: Verify Register API
    Given Register Payload
    When User Calls "registerUserApi" with "Post" http request
    Then The API call got success with status code <status>
    And "message" in response body is "<message>"

    Examples:
      | status | message                      |
      | 200    | User registered successfully |

  Scenario Outline: Verify Login API
    Given Login Payload
    When User Calls "loginUserApi" with "Post" http request
    Then The API call got success with status code <status>
    And "message" in response body is "<message>"
    Then Store "data.id" from response as "userId"

    Examples:
      | status | message                     |
      | 200    | User logged in successfully |

  Scenario: Verify Logout API
    Given Logout Payload
    When User Calls "logoutUserApi" with "Post" http request
    Then The API call got success with status code 200
    And "message" in response body is "User logged out successfully"