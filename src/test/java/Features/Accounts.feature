Feature: Create an Account object
  As an authenticated user, I should be able to create an account record
 @classic
  Scenario Outline: Create and Verify Account record
    Given I open chrome browser
    And I enter valid username "<username>"
    And I enter valid password "<password>"
    When I click the Login button

    Examples: 
      | username                 | password  |
      | test.user@gmail.com.test | Pa555word |

 
  Scenario Outline: Create multiple account records
    Given I open chrome browser
    And I enter valid username "<username>"
    And I enter valid password "<password>"
    When I click the "Login" button
    Then I create "Accounts" with the following values
      | Account Name | Account Number | Active | Account Source   | Annual Revenue | Rating | Type                 |
      | ekuber 1     |           1000 | Yes    | Web              |           5000 | Hot    | Prospect             |
      | ekuber 2     |           2000 | No     | Phone Inquiry    |           6000 | Warm   | Customer - Direct    |
      | ekuber 3     |           3000 | Yes    | Partner Referral |           7000 | Hot    | Installation Partner |
      | ekuber 4     |           4000 | No     | Web              |           8000 | Cold   | Prospect             |
      | ekuber 5     |           5000 | Yes    | Partner Referral |           9000 | Hot    | Installation Partner |
    Then I see the record got created
    And I quit

    Examples: 
      | username                 | password  |
      | test.user@gmail.com.test | Pa555word |

  Scenario Outline: Verify the drop down values
    #Given I go to the "qa" environment using "chrome" browser
    Given I open chrome browser
    And I enter valid username "<username>"
    And I enter valid password "<password>"
    When I click the "Login" button
    Then I should see the "Accounts" tab
    Then I should see the "Accounts" home page
    When I click the "new" button
    And I should see the following values for "acc9" field
      | --None-- |
      | Hot      |
      | Warm     |
      | Cold     |
    And I should see the following values for "acc6" field
      | --None--                   |
      | Prospect                   |
      | Customer - Direct          |
      | Customer - Channel         |
      | Channel Partner / Reseller |
      | Installation Partner       |
      | Technology Partner         |
      | Other                      |

    Examples: 
      | username                 | password  |
      | test.user@gmail.com.test | Pa555word |
