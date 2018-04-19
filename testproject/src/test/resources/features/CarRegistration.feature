
Feature: Functional test to compare car registration details


#Scenario: To get the file to a directory
#Given I want to write a step with precondition
	#And some other precondition
#When I complete action
	#And some other action
	#And yet another action
#Then I validate the outcomes
	#And check more outcomes

#Scenario: To compare vehicle the details at DVLA
#		Given I navigate to DVLA website
#		And I click on 'Start Now' button
#		When I enter 'LC64 VFN' as the Registration number 
# 		And I click 'Continue'
#		Then I should see 'LC64 VFN' in the Vehicle details page
#		And I should see the details 'LC64VFN', 'BMW', 'January 2015', '2015', '1995 cc', 'DIESEL', 'No', 'Tax not due', 'BLACK', 'M1', '2-AXLE-RIGID BODY', 'Not available' on the screen
#		And I click on Home button
#    And I close the browser

Scenario: To read and compare vehicle the details at DVLA
		Given I read files from folder 'C:\Temp\Cars' and verify vehicle details at DVLA
