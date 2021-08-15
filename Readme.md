# PROJECT OVERVIEW

it is a solo project build during holidays for practising object oriented programming.

## Tech Used

- Java
- Sqlite3

## What does it do ?

this project is a basic CLI based hotel management java application, it provides basic functionalities like:
- checking if the user is a admin or not
- providing with a menu to user to :
    - add a user to access the app
    - show list of guest
    - check in a new guest
    - check out a guest

## Aim of the Project
the main aim of this project was to::
- implement the OOPS concept
- learn how to make connection with database using java

## Challenges faced

there were a lot of minor issues out of those few are worth mentioning
- comparing 2 strings<br>
    there was a requirement to match two string i.e one in DB and one provided by the user at the point i was using (==) but later i realised that it is useful to compare if the reference variable is pointing to a same object or not to compare the actual value i had to use (String.equals() method)
- difference between execut(), executeUpdate(), executeQuery()
- database locked exception <br>
    in early implementation i was holding the db connection even after login fuction is complete it took about 2 hrs to figure out that then i restructured the implementation, so that it releases the DB after function completes

## Objectives achieved

during the duration of this project i was able to:
- actually use the concept of abstraction and encapsulation,
- implement interface, and use a class for implementing the interface method.
- use sqlite3 as a database for storing and accessing data

- did tried containerization of this app(since this app depends on external dependency i.e. sqlite3 i have to look into how to make two containers communicate for functionong of this app in containerized format)

## Any extension to this ??
I thought of implementing a CI/CD pipline to publish a package on github registry, couldn't do that yet 
looking for some help on how to setup github actions and maven

