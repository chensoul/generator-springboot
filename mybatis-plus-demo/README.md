# myservice

This is a template project for a Spring Boot application which was generated
by https://github.com/sivaprasadreddy/generator-springboot.

### Features

* Spring Boot project with Maven and Gradle support
* Spring Data JPA integration with Postgresql.
* Flyway database migration support.
* CORS configuration
* Swagger UI Integration
* SpringBoot Actuator configuration
* Testcontainers based Testing and Local dev mode setup
* DockerCompose configuration for application, ELK, Prometheus, Grafana
* GitHub Actions Configuration
* Dockerfile
* Jenkinsfile
* SonarQube and JaCoCo based static analysis tools configuration
* Code formatting using Spotless Maven plugin
* JUnit 5

### Format code

```shell
$ ./mvnw spotless:apply
```

### Run tests

```shell
$ ./mvnw clean verify
```

### Run locally

```shell
$ docker-compose -f docker-compose.yml up -d
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Using Testcontainers at Development Time

You can run `TestApplication.java` from your IDE directly.
You can also run the application using Maven as follows:

```shell
./mvnw spring-boot:test-run
```

### Run SonarQube

```shell
$ ./gradlew sonarqube -Dsonar.login=admin -Dsonar.password=admin
```

### Useful Links

* Swagger UI: http://localhost:8080/swagger-ui.html
* Actuator Endpoint: http://localhost:8080/actuator
* Sonarqube UI: http://localhost:9001
* Prometheus: http://localhost:9090/
* Grafana: http://localhost:3000/ (admin/admin)
* Kibana: http://localhost:5601/
