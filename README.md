# Microservicios

## TO START THE APPLICATION FOLLOW THESE STEPS.
You need to be connected to internet to start the app.
### 1st step: START config-server

### 2nd step: START eureka-service

### 3rd step: START gateway

### 4th step: START microservicio-auth

### 5th step: START all remaining microservicios

#### CREATE USERS FOR LOGIN

Examples:
Using Postman in the url  http://localhost:8080/auth/register to create authentication users

{
"username": "admin",
"password": "password",
"roles": ["ADMIN"]
}

{
"username": "common-user",
"password": "password",
"roles": ["USER"]
}

#### LOGIN

Only use credentials.
Once registered, proceed to log in a user at the url  http://localhost:8080/auth/login
{
"username": "admin",
"password": "password"
}
or
{
"username": "user",
"password": "password"
}

### In this way a token is obtained which will be used to check the security of the remaining endpoints.

#### Steps to create and connect a MongoDB NoSQL database:
#### 1- Run the docker-compose.yml file found in the microservice-bike (go through the terminal to the location of the file and then run the command **docker-compose up -d**).
#### 2- go to Datagrip and create a MongoDB connection with user: root and password: root (verify the connection)
#### then go to properties, in the Schemas tab create the *bikes* schema and save it. save what you have done
#### add the word *bikes* in the Schema pattern