[![CircleCI](https://circleci.com/gh/boskodjokic/LoanCalculator.svg?style=svg)](https://circleci.com/gh/boskodjokic/LoanCalculator)

# Read Me

Simple application that is calculation loan schedule payments based on the input of loan amount, interest rate and loan term (in months).
Project is build with Spring-Boot and Java 8.
PostgreSQL database is used to add data persistence.
Also, CircleCI config file is added so after every commit, tests are run and checked.

API calls can be tested with Postman or via Swagger.
Address for loan request controller is http://localhost:8090/api/requests

Detailed methods and requirements are in Swagger documentation.

### Building and running the application

Navigate to the folder of the project and first run in terminal:

~~~
mvn clean install -DskipTests
~~~

After that, run:

~~~
docker-compose up --build
~~~

Both application and PostgreSQL database are running in a Docker container.
To connect to database use pgAdmin.

Db name: <b>mydb</b>

Password: <b>password</b>

Username: <b>postgres</b>

Port: <b>5432</b>

For Swagger, navigate to http://localhost:8090/swagger-ui/#


### Running tests

Tests can be run either from IDE or from terminal.
For running tests from terminal, navigate to the project folder and run:

~~~
mvn test
~~~


### Notes
If you don't have maven installed on the machine, maven wrapper is integrated in the project, so it can be run with <b>mvnw</b> instead of <b>mvn</b>.
