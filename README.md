# **Leaderboard Project**

This project implements a leaderboard for a running contest, supporting CRUD operations.

## **Endpoints**

**GET /users:** Retrieve a list of all registered users.

**GET /users/{userId}:** Retrieve the details of a specific user.

**POST /users:** Register a new user to the contest.

**PUT /users/{userId}:** Update the score of a specific user.

**DELETE /users/{userId}:** Deregister a specific user from the contest.

## **Persistence**

User data is persisted using MongoDB.

## **Usage**

Explore the leaderboard functionality by testing the provided endpoints using the Postman collection available here.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/adff0eda-0e4e-492d-a8b1-3c0f3b3c6ca5)
