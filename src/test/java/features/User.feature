Feature: Verify user functionalities works as designed.

  Scenario: User info is retrievable using user API
    Given user info with Id 1 is retrieved using API
    Then information of user with Id 1 is correct

  Scenario: Create user through API should work
    Given new user info is submitted to API
    Then API should return submitted user information

  Scenario: Create new user with existing user Id should not be accepted
    Given user info with existing user Id is submitted to API
    Then API should not accept the request and should return error