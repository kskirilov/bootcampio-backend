## Bootcamp Backend Project
This project was created as part of Bright Network Tech Academy, composing key layers of web app backend.  
To understand the thought process of this project, please go through the comments in commit history.

**This is open source code for learning purposes only, all rights reserved by the repository owner**

### Table of contents
- [Introduction](#introduction)
- [Technologies](#technologies)
- [Set up](#set-up)

## Introduction
Bootcamp is a web app that contains a collection of all live courses: users can register, add courses to wishlist and submit feedback to courses.
Each course has an average rating which is calculated from all submitted feedback for the course.

### Endpoints
#### List of courses
GET `/courses`  

Returns a list of courses with their predefined properties.

Optional query parameters:
- sort by properties of the courses (e.g. price, rating), default is course id

#### Get a single course
GET `/courses/[insert course id]`  
Retrieve detailed information about a course.

#### Add a new course
POST `/courses`  
Allows you to add a new course.  
The request body needs to be in JSON format.  
  
Example
```
{
"name": "How to be a part-time youtuber",
"rating": 4,
"description": "super cool",
"category": "YOUTUBE",
"deadline": "2022-01-05",
"cost": 3000,
"location": "ONLINE",
"place": "Slack",
"spacesAvailable": 50,
"signUpThrough": "{{$randomCompanyName}}"
}
```
#### Get users
GET `/users`  
Returns a list of users with their encryted password, resgiter and last updated profile date time  


Optional query parameters:
- sort by properties of the user (e.g. name, created_at date), deafault is id

#### Add a new user
POST `/users`
Allows you to add a new user.  
The request body needs to be in JSON format and include the following properties:
- `name` - String, required
- `email` - String required
- `password` - String, required

Example
```
{     
      "name" "Jack N"
      "email": "prince@gmail.com",
      "password":"pass"
}
```

#### Login as a user
POST `/users/login`
Allows the registerd user to login, requires the following parameters
- `name` - string, required
- `password` - string, required

Throws an authorized error if password incorrect. To change the password, send PUT request to `\users`  

To logout, send DELETE request to the endpoint
#### Add course to wishlist
POST `/wishlist/[insert user id]?courseID=[insert course id]`

#### Get wishlist of a particular user
GET `/wishlist/[insert user id]`

#### Submit a feedback for a course
POST `/feedback`
Allows you to submit a feedback for a course, requires following parameters
- `courseId` - int, required
- `userId` - int, required
- `courseRating` - double, required
- `comment` - string, optional

Example
```
{ 
        "courseId": 1,
        "userId": 10,
        "courseRating": 5.0,
        "comment": "loving it"
}
```

All four main endpoints, `users`, `courses`, `wishlist`, `feedback` have all the core CRUD functionalities.

## Technologies
Project is created with:
- IntelliJ IDEA (Spring Boot)
- PostgreSQL
- Postman

## Set up
To start the application, run `BootcampBackendApplication`, test the endpoints with Postman

Please refer to the ERD to setup your database  

Any question about the project, please feel free to get in touch :)

