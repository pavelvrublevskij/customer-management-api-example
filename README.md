# Customer Bill Management API (Example with integrations), v 1.0.0-dev-version
__OpenAPI generator used (lombok supported)__

## TODO
* Build with Gradle (Latest)
* Upgrade to openJDK 11
* Get data from DB as another retrieval example:
    - Use liquibase
    - Persistence

## Standard 
TMF678_CustomerBill
TM Forum Open APIs (Apache 2.0) Customer Bill Management API
API fetched from: https://github.com/tmforum-apis/TMF678_CustomerBill/blob/master/TMF678-CustomerBill-v4.0.0.swagger.json

This API allows to find and retrieve one or several customer bills (also called invoices) produced for a customer.

To access this API; (the Specification, Postman Collection, Swagger file, Swagger-UI, Reference Implementation, Conformance Profile, and CTK) go to the Open API table at:

https://projects.tmforum.org/wiki/display/API/Open+API+Table

## Requirements
* Language JAVA 8
* Maven (Latest)

## Run Docker image localy (docker required)
-------------------
First time to build a container locally:

    docker build -t cbm-api .

after that you always could call:

	docker run -p 8080:8080 cbm-api:latest


## Access the application:
-------------------
* v2: http://localhost:8080/api/swagger-ui/index.html
* v3: http://localhost:8080/api/swagger-ui/index.html?url=/api/v3/api-docs&validatorUrl=#


----
# How-To

## OpenApi with Lombok

### Overview
As I like lombok very much I need it functionality for openapi generator also. I have created a templates to achieve my desires.

This project has template for use of Lombok in generated models.
They are not enabled by default, but are enabled with "additional-properties"

### Additional Properties
This template set relies on the additional properties listed in this table

| Name         | Data Type | Description                                |
|--------------|-----------|--------------------------------------------|
| useLombok    | Boolean   | Enables Lombok for models                  |

### How generate last template by OpenAPI version (without lombok)
Lombok will be supported in the future

    npm install @openapitools/openapi-generator-cli -g
    openapi-generator-cli version-manager set 5.0.0
    npx @openapitools/openapi-generator-cli author template -g spring

More info at https://openapi-generator.tech/docs/installation
