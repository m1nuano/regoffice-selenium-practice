@regression @createDriver @quitDriver
Feature: Application status update via BDD

  Background:
    Given I create the birth application
    And I log in as admin

  Scenario Outline: Update application status via action
    When I perform action with '"<actionType>"' icon to change the application status to "<expectedStatus>"
    Then I check that the status of the application is "<expectedStatus>"

    Examples:
      | actionType     | expectedStatus |
      | ThumbUpIcon    | Одобрена       |
      | ThumbDownIcon  | Отклонена      |

