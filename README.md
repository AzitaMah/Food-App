# Dating Food App
Our dating food app is an application to help you set up dates with other people based on the food you are currently craving. You can use the food swipe to choose your food and then select people you want to meet up with. If these people also liked you, you will see their contact information on the matches page.

## Getting started
## How to start the application
You start the application by running "docker-compose up" in the root directory.
Our frontend is running on localhost:3000 and our backend on localhost:8080.

### notice
If you have trouble building the backend image, check if "backend/mvnw" is checkout with LF line endings.(This should be fixed with gitattributes.)

## How to use the application
You can login with any user that can be found in "user.md". 
The username and the password of the admin is both "admin".

## Documentation
In our notes folder you can find helpful documentation for better understanding. For example, there is an overview for all endpoints and a file that describe the workflow of the matching.

## How to work in the backend
1. start database via docker-compose
2. run BackendApplication in "src/main/java/com/datingfood/backend/BackendApplication.java"

## How to work in the frontend
Make sure to pull the latest changes from the backend before development.
1. start backend and database by running "docker-compose up --build" 
2. then start the frontend for local development


### test coverage for backend
1. mvn clean test
2. mvn jacoco:report
Afterwards you can check the coverage backend/target/site/jacoco/index.html.
