# RewardsCalculator
A Customer Rewards Calculator

This application creates a Rest service that can be used to calculate the
rewards points for a customer based on the following specification:

A customer receives 2 points for every dollar spent over $100 in each 
transaction, plus 1 point for every dollar spent between $50 and $100 in 
each transaction.

The following end points are available:

GET: /rewards/{userid}
	Return the total reward points for a given user.
	
GET: /rewards/{userid}/{year}/{month}
	Return the reward points for a given user in a given month.