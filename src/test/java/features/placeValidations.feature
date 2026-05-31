Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User Calls "addPlaceAPI" with "Post" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "getPlaceAPI"

  Examples:
    | name    | language | address             |
    | House   | English  | World Cross Centre  |

  @DeletePlace
  Scenario: Verify if the Delete Place functionality is working

    Given DeletePlace Payload
    When User Calls "deletePlaceAPI" with "Post" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"