#Author: Deepa Kaur
  #Keywords Summary : Contact page
@Contact
Feature:  JupiterToys contact page

  Scenario: Testing missing Forename on contact page
    Given A user is on home page
    And navigates to contact page
    When User does not populate 'forename' and submits
      | forename | surname | email          | telephone | message                    |
      |          | Kaur    | deepa1@abc.com | 01234678  | run 1 testing contact page |
    And User submits the page
    Then Error alert message is displayed
    And Input error message 'Forename is required' is displayed

  Scenario: Testing missing Email on contact page
    Given A user is on home page
    And navigates to contact page
    When User does not populate 'email' and submits
      | forename | surname | email | telephone | message                    |
      | Deepa2   | Kaur    |       | 01234678  | run 2 testing contact page |
    And User submits the page
    Then Error alert message is displayed
    And Input error message 'Email is required' is displayed

  Scenario: Testing missing Message on contact page
    Given A user is on home page
    And navigates to contact page
    When User does not populate 'message' and submits
      | forename | surname | email          | telephone | message |
      | Deepa1   | Kaur    | deepa1@abc.com | 01234678  |         |
    And User submits the page
    Then Error alert message is displayed
    And Input error message 'Message is required' is displayed

  Scenario Outline: Testing mandatory fields on contact page
    Given A user is on home page
    And navigates to contact page
    When User populates mandatory fields
      | forename   | surname   | email   | telephone   | message   |
      | <forename> | <surname> | <email> | <telephone> | <message> |
    And User submits the page
    Then Successful submission message 'Thanks <forename>, we appreciate your feedback.' is displayed
    Examples:
      | forename | surname | email          | telephone | message                    |
      | Deepa 1  |         | deepa1@abc.com | 09743666  | run 1 testing contact page |
      | Deepa 2  | Kaur    | deepa2@abc.com |           | run 2 testing contact page |
      | Deepa 3  |         | deepa3@abc.com |           | run 3 testing contact page |
      | Deepa 4  | Kaur    | deepa4@abc.com |           | run 4 testing contact page |
      | Deepa 5  |         | deepa5@abc.com | 77484379  | run 5 testing contact page |


