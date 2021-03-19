Feature: Create an Opportunity object
  As an authenticated user, I should be able to create an account record

  @test1 @classic
  Scenario Outline: Create and Verify Opportunity record
    Given I open chrome browser
    And I enter valid username "<username>"
    And I enter valid password "<password>"
    When I click the Login button

    Examples: 
      | username                 | password  |
      | test.user@gmail.com.test | Pa555word |
      