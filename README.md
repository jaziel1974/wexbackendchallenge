# Wex Backend Engineer Job Interview Challenge

### Documentation

This application uses Spring Boot with:
- Spring Web
- Spring Data
- Postgres
- Lombok
- JUnit 5
- OpenAPI 3.0
- Spring Cache

Architecturally, I tried to use SOLID principles. Since the application is very small I tried avoiding over-engineering.

So, I used a common approach and created regular entity, service, and controller layers, with the use of dtos. The main goal is to avoid tight coupling among the layers.

Still thinking about avoiding over-engineering, I didn't implement interfaces for the conversation between the service layer and the model layer, although it would be a good practice.

I implemented a small bunch of integration tests that can be automatically started when deploying the application via the CI/CD approach. I didn't implement unit tests for the model layer, since it is very simple.

I used the *SonarLint* plugin to help me with code quality.

You can find the API specs (Swagger style) at the /api-docs endpoint.

Also, if you use Postman to test your APIs, you'll find both a collection and an environment example files in the /postman folder.

Regarding getting rates, when analyzing the service that provides the rates, I noticed that the rates are infrequently updated. So, aiming at the performance, I decided to use spring cache and keep the rates in cache by 24 hours.

For the sake of the tests, I created an endpoint to clear the cache and force the application to get the rates again.

I can think of at least one improvement I could implement targeting performance enhancement, which is changing to webflux, since asynchronous calls are proven to be more efficient than synchronous would be a good idea. Also, I didn't implement some physical cache like REDIS which is highly recommended for production environments.

### How to run the application LOCALLY
Having a docker and docker-compose installed, just run the following command in the root folder of the project (Linux):

```
bash +x start.sh
bash ./start.sh
```

After running the command, all the APIs will be available at http://localhost:8080

You also can find the PostmanCollection folder at the root folder with a Postman collection and environment configuration to test the APIs.

NOTES:
I didn't implement any security layer, since it was not required. I hope this will be enough.

### How the application works
It is simple.

One uses the POST /transaction to create a transaction.

If wanted, to get the transaction or all the registered transactions, use GET /transaction or GET /transaction/{id} respectively.

To get a transaction exchanged to another currency, use GET /transaction/{id}/converted, providing the proper country and currency.

To get the rates, use GET /quote;
