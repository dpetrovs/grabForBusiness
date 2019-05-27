@HomePage
Feature: Home Page

  Scenario: Verify Monthly Frequency can be selected
    Given user select "STATEMENT_PREFERENCES_TAB" tab
    When user select "NONE" frequency
    Then "NONE" frequency is selected
    When user select "MONTHLY" frequency
    Then "MONTHLY" frequency is selected

  Scenario Outline: Verify Payment Method Filter functionality
    Given user select "<Drop Down Menu Item>" from Payment Method list
    Then Payment Method "<Drop Down Menu Item>" is selected
    Examples:
      |Drop Down Menu Item|
      |CASH|
      |CORPORATE_CREDIT_CARD|
      |CORPORATE_BILLING|
      |GRAB_PAY_CREDIT|
      |ALL|

  Scenario: Verify Cash and Employee Group QA filters functionality
    Given user select "CASH" from Payment Method list
    And user select "QA" from employee list
    Then Payment Method "CASH" is selected
    And Employee Group "QA" is selected
    And trips with Payment Method "CASH" are shown in trips table
    And trips with Employee Group "QA" are shown in trips table