# A Sample Spring init project 

This helps understanding all the basic configs needed to create a basic spring app 
This project contains all of this 

- Mongo DB connect
- Security
	- Single user
	- User in DB - Bcrypt
	- OAUTH 
- Logging
	- Console appender
	- FileAppender
	- Logstash appender
- Actuators 
- Exception handling
  - Controller Advice
- Dockerise the app

# Steps to run

> ./gradlew clean bootJar 
> 
> docker-compose up --build
