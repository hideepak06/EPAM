Feature: MAke my trip Homepage
        
 Scenario Outline: Search for a given flight
    
Given I navigate to "https://www.makemytrip.com"
Then I wait for 20 seconds
When I enter "<Source>" into input field having xpath "fromtextbox"
When I enter "<Destination>" into input field having xpath "totextbox"
When I click on element having xpath "searchbtn"
Then I wait for 4 seconds
And I take screenshot


Examples:
|Source  |Destination|
|Delhi   |Mumbai     |

Scenario: Search for a flight with fastest and chepest
Given flights with fastest and chepest
And I take screenshot

Scenario: Search for a evening flight when multiple fastest and chepest option available
When I click on element having xpath "nighttimeflights"
And I take screenshot

