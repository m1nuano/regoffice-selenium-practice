@regression @createDriver @quitDriver
Feature: Application status update via BDD

  Background:
    Given birth application is created
    And logging in as admin

  Scenario Outline: Update application status via action
    When I perform action with '"<actionType>"' icon to change the application status to "<expectedStatus>"
    Then the application status should be "<expectedStatus>"

    Examples:
      | actionType     | expectedStatus |
      | ThumbUpIcon    | Одобрена       |
      | ThumbDownIcon  | Отклонена      |

