#This is exactly the same test as in EnterSvenCodedTest.java

Adding contacts

Narrative:
In order to manage the addressbok
As a user
I want to add contacts

Scenario: Add a new contact and it shows in the results

Given the front page
When the user clicks the add contact button
And fills the contact details with these values:
	|Firstname	|Lastname	|Company	|
	|Sven		|Svensson	|Vaadin Ltd.|
And searches for "Sven"
Then the only row should contain these values:
	|Firstname	|Lastname	|Company	|
	|Sven		|Svensson	|Vaadin Ltd.|
