# Dating Food App



## Getting started
## How to start the application
You start the application by running "docker-compose up" in the root directory.

## How to work in the backend
1. start database via docker-compose
2. run BackendApplication in "src/main/java/com/datingfood/backend/BackendApplication.java"

## How to work in the frontend
Make sure to pull the latest changes from the backend before development.
1. start backend and database by running "docker-compose up --build" 
2. then start the frontend for local development


### notice
If you have trouble building the backend image, check if "backend/mvnw" is checkout with LF line endings.(This should be fixed with gitattributes.)

### test coverage for backend
1. mvn clean test
2. mvn jacoco:report
Afterwards you can check the coverage backend/target/site/jacoco/index.html.
