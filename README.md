# CPSC 210 project 

## balance splitting app

*What does the app do*:
- splits balance of all products entered between the respective users who use that product
- Full liberty to **add as many users and products**
- If one product is only being used by 2 users for example, then the product cost will only be split between those two 
users, so the people who don't use that product don't have to pay for it.

*Who will use it*:

- Group of friends who go out shopping
- Group of people who go out to a restaurant to eat, etc.

*What inspired me to do this project*:

- Initially I planned on making a grocery list app due to the personal experience of the tedious task of buying 
groceries, and planning out what all stuff to buy. However, that led me to think how my room-mates, and I would go shopping
and it was even more tedious to split the bill between us after one of us payed(as there were things only some of us used)
This led me to the idea of building an app that splits the amount between users based on the products they specifically use.


*Phase 4* :
- UsersList Class uses methods from ProductsList class to store information on which products the user is using,
 and the ProductsList Class uses methods from UsersList Class to store information on which product uses which users.