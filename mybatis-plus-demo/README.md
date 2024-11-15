# mybatis-plus-demo

This project was created with [generator-springboot](https://github.com/chensoul/generator-springboot/).

## Build

The application can be built using the following command:

```bash
./mvnw clean package
```

## Format code

```shell
$ ./mvnw spotless:apply
```

## Run SonarQube

```shell
$ ./mvnw clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

## Repair Flyway Schema History Table

```shell
./mvnw flyway:repair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
```

## Useful Links

* Springdoc UI: http://localhost:8080/swagger-ui.html
* Actuator Endpoint: http://localhost:8080/actuator
* Sonarqube UI: http://localhost:9001
* Kibana: http://localhost:5601/
