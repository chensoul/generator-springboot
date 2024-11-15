# Generator SpringBoot

The Yeoman generator for generating Spring Boot microservices.

## Prerequisites
* Node 18+
* JDK 17+

## Installation
```shell
$ npm install -g yo
$ npm install -g generator-springboot
```

## How to use?
Run the following command and answer the questions:

```shell
$ yo springboot
```

## Features
The generator-springboot generates a Spring Boot application with the following features configured:

* Spring Boot project with Maven and Gradle support
* Persistence databases like MySQL, Postgresql, MariaDB with **Spring Data JPA** or **Mybatis Plus**
* Flyway and Liquibase database migration support
* CORS configuration
* SpringDoc OpenAPI Integration
* SpringBoot Actuator configuration
* Testcontainers based Testing and Local dev mode setup
* DockerCompose for application, **ELK, Zipkin, Prometheus, Grafana, Tempo, Loki**
* GitHub Actions Configuration
* Dockerfile
* Jenkinsfile
* SonarQube and JaCoCo based static analysis tools configuration
* Code formatting using Spotless plugin
* JUnit 5 for unit testing
* ArchUnit for architecture testing

### Generate a SpringBoot Microservice
After installing the `generator-springboot`, you can generate a new Spring Boot application as follows:

```shell
$ yo springboot
Generating SpringBoot Application
? What is the application name? myservice
? What is the default package name? com.mycompany.myapp
? Which type of database you want to use? Postgresql
? Which type of database migration tool you want to use? FlywayDB
? Which type of logging tool you want to use? ELK Docker configuration
? Which type of trace tool you want to use? Zipkin Docker configuration
? Select the features you want? Prometheus, Grafana Docker configuration, Localstack Docker configuration
? Which build tool do you want to use? Maven
    force myservice/.yo-rc.json
   create myservice/mvnw
   create myservice/mvnw.cmd
   create myservice/.gitignore
   create myservice/.mvn/wrapper/maven-wrapper.jar
   create myservice/.mvn/wrapper/maven-wrapper.properties
   create myservice/pom.xml
   create myservice/Dockerfile
   create myservice/Jenkinsfile
   create myservice/README.md
   create myservice/.github/workflows/maven.yml
   create myservice/src/main/resources/db/migration/postgresql/V1__01_init.sql
   create myservice/docker-compose.yml
   create myservice/docker-compose-app.yml
   create myservice/docker-compose-monitor.yml
   create myservice/docker/prometheus/prometheus.yml
   create myservice/docker/grafana/provisioning/datasources/datasource.yml
   create myservice/docker/grafana/provisioning/dashboards/basic-dashboard.json
   create myservice/docker/grafana/provisioning/dashboards/dashboard.yml
   create myservice/docker/grafana/provisioning/dashboards/jvm.json
   create myservice/docker/grafana/provisioning/dashboards/petclinic-dashboard.json
   create myservice/docker/grafana/provisioning/dashboards/spring-boot-dashboard.json
   create myservice/docker-compose-elk.yml
   create myservice/docker/elk/logstash.conf
   create myservice/docker/elk/elasticsearch.yml
   create myservice/docker/elk/logstash.yml
   create myservice/docker/elk/kibana.yml
   create myservice/docker-compose-zipkin.yml
   create myservice/.localstack/01_init.sh
   create myservice/src/main/java/com/mycompany/myservice/Application.java
   create myservice/src/main/java/com/mycompany/myservice/config/WebMvcConfig.java
   create myservice/src/main/java/com/mycompany/myservice/config/JacksonConfig.java
   create myservice/src/main/java/com/mycompany/myservice/config/SpringdocConfig.java
   create myservice/src/main/java/com/mycompany/myservice/config/ApplicationProperties.java
   create myservice/src/main/java/com/mycompany/myservice/config/Initializer.java
   create myservice/src/main/java/com/mycompany/myservice/config/GlobalExceptionHandler.java
   create myservice/src/main/java/com/mycompany/myservice/config/aop/LoggingAspect.java
   create myservice/src/main/java/com/mycompany/myservice/exception/ResourceNotFoundException.java
   create myservice/src/main/java/com/mycompany/myservice/model/response/PagedResult.java
   create myservice/src/main/java/com/mycompany/myservice/util/AppConstants.java
   create myservice/src/main/java/com/mycompany/myservice/config/MetricConfig.java
   create myservice/src/main/java/com/mycompany/myservice/util/AggravateMetricsEndpoint.java
   create myservice/src/main/resources/application.yml
   create myservice/src/main/resources/logback-spring.xml
   create myservice/src/test/java/com/mycompany/myservice/ApplicationIntegrationTest.java
   create myservice/src/test/java/com/mycompany/myservice/SchemaValidationTest.java
   create myservice/src/test/java/com/mycompany/myservice/common/ContainersConfig.java
   create myservice/src/test/java/com/mycompany/myservice/common/AbstractIntegrationTest.java
   create myservice/src/test/java/com/mycompany/myservice/TestApplication.java
   create myservice/src/test/java/com/mycompany/myservice/SqsListenerIntegrationTest.java
   create myservice/src/test/resources/application-test.yml
   create myservice/src/test/resources/logback-test.xml

No change to package.json was detected. No package manager install will be executed.
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------< com.mycompany.myapp:myservice >------------------
[INFO] Building myservice 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- spotless:2.43.0:apply (default-cli) @ myservice ---
[INFO] Index file does not exist. Fallback to an empty index
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/TestApplication.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/SqsListenerIntegrationTest.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/SchemaValidationTest.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/common/AbstractIntegrationTest.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/common/ContainersConfig.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/util/AggravateMetricsEndpoint.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/config/ApplicationProperties.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/config/GlobalExceptionHandler.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/config/aop/LoggingAspect.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/config/SpringdocConfig.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/model/response/PagedResult.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/exception/ResourceNotFoundException.java
[INFO] Spotless.Java is keeping 21 files clean - 13 were changed to be clean, 8 were already clean, 0 were skipped because caching determined they were already clean
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.186 s
[INFO] Finished at: 2024-10-23T10:28:13+08:00
[INFO] ------------------------------------------------------------------------
==========================================
Your application is generated successfully
  cd myservice
  > ./mvnw spring-boot:run
==========================================
```

### Generate REST API with CRUD operations
You can generate REST API with CRUD operation using the following command:

**IMPORTANT:** You should run the following command from within the generated project folder.

```shell
$ cd myservice
$ yo springboot:controller Customer --base-path /api/customers
```

This sub-generator will generate the following:

* JPA entity
* Spring Data JPA Repository
* Service
* Spring MVC REST Controller with CRUD operations
* Unit and Integration Tests for REST Controller
* Flyway or Liquibase migration to create table

```shell
$ yo springboot:controller Customer --base-path /api/customers
Generating JPA entity, repository, service and controller
EntityName: Customer, basePath: /api/customers
    force .yo-rc.json
   create src/main/java/com/mycompany/myservice/exception/CustomerNotFoundException.java
   create src/main/java/com/mycompany/myservice/mapper/CustomerMapper.java
   create src/main/java/com/mycompany/myservice/model/query/FindCustomerQuery.java
   create src/main/java/com/mycompany/myservice/model/request/CustomerRequest.java
   create src/main/java/com/mycompany/myservice/model/response/CustomerResponse.java
   create src/main/java/com/mycompany/myservice/repository/CustomerRepository.java
   create src/main/java/com/mycompany/myservice/service/CustomerService.java
   create src/main/java/com/mycompany/myservice/web/controller/CustomerController.java
   create src/test/java/com/mycompany/myservice/web/controller/CustomerControllerTest.java
   create src/test/java/com/mycompany/myservice/web/controller/CustomerControllerIT.java
   create src/test/java/com/mycompany/myservice/service/CustomerServiceTest.java
   create src/main/resources/db/migration/postgresql/V2__create_customers_table.sql

No change to package.json was detected. No package manager install will be executed.
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------< com.mycompany.myapp:myservice >------------------
[INFO] Building myservice 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- spotless:2.43.0:apply (default-cli) @ myservice ---
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/web/controller/CustomerControllerTest.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/web/controller/CustomerControllerIT.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/test/java/com/mycompany/myservice/service/CustomerServiceTest.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/web/controller/CustomerController.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/mapper/CustomerMapper.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/model/request/CustomerRequest.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/model/query/FindCustomerQuery.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/service/CustomerService.java
[INFO] Writing clean file: /Users/chensoul/codes/github/generator-springboot/myservice/src/main/java/com/mycompany/myservice/exception/CustomerNotFoundException.java
[INFO] Spotless.Java is keeping 31 files clean - 9 were changed to be clean, 2 were already clean, 20 were skipped because caching determined they were already clean
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.598 s
[INFO] Finished at: 2024-10-23T10:52:35+08:00
[INFO] ------------------------------------------------------------------------
```

## Local Development Setup

```shell
$ git clone https://github.com/sivaprasadreddy/generator-springboot.git
$ cd generator-springboot
$ npm install -g yo
$ npm install 
$ npm link
$ yo springboot
```

## Releasing a new version
Before publishing a new release, make sure to update the version number in `package.json` updated.

```shell
$ npm login
$ npm publish
```

## License
The **generator-springboot** is an Open Source software released under the [MIT Licence](https://opensource.org/license/mit/)
