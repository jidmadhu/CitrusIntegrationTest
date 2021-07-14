Feature: Outline test

  Scenario Outline: Search <homepage> Scenario

    Given The User is navigate to "<homepage>" Home page

    Examples:
      | homepage | searchword |
      | google   | kanmani    |
      | yahoo    | Tamil      |
      | google   | kanmani    |
      | yahoo    | Tamil      |
      | google   | kanmani    |
      | yahoo    | Tamil      |