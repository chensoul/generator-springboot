# <%= appName %>

<%_ if (buildTool === 'maven') { _%>
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
$ ./mvnw spring-boot:run
```

### Using Testcontainers at Development Time
You can run `TestApplication.java` from your IDE directly.
You can also run the application using Maven as follows:

```shell
./mvnw spring-boot:test-run
```

### Run SonarQube

```shell
$ docker-compose -f docker-compose-sonar.yml up -d
$ mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

<%_ if (dbMigrationTool === 'flywaydb') { _%>
### Repair Flyway Schema History Table

```shell
<%_ if (databaseType === 'postgresql') { _%>
./mvnw flyway:repair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
<%_ } _%>
<%_ if (databaseType === 'mysql') { _%>
./mvnw flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
<%_ } _%>
<%_ if (databaseType === 'mariadb') { _%>
./mvnw flyway:repair -Dflyway.url=jdbc:mariadb://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
<%_ } _%>
```
<%_ } _%>
<%_ } _%>

<%_ if (buildTool === 'gradle') { _%>
### Format code

```shell
$ ./gradlew spotlessApply
```

### Run tests

```shell
$ ./gradlew clean build
```

### Run locally

```shell
$ docker-compose -f docker-compose.yml up -d
$ ./gradlew bootRun
```

### Using Testcontainers at Development Time
You can run `TestApplication.java` from your IDE directly.
You can also run the application using Gradle as follows:

```shell
$ ./gradlew bootTestRun
```

### Run SonarQube

```shell
$ docker-compose -f docker-compose-sonar.yml up -d
$ ./gradlew sonarqube -Dsonar.login=admin -Dsonar.password=admin
```

<%_ if (dbMigrationTool === 'flywaydb') { _%>
### Repair Flyway Schema History Table

```shell
<%_ if (databaseType === 'postgresql') { _%>
./gradlew flywayRepair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
<%_ } _%>
<%_ if (databaseType === 'mysql') { _%>
./gradlew flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
<%_ } _%>
<%_ if (databaseType === 'mariadb') { _%>
./gradlew flywayRepair -Dflyway.url=jdbc:mariadb://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
<%_ } _%>
```
<%_ } _%>
<%_ } _%>

### Useful Links
* Swagger UI: http://localhost:8080/swagger-ui.html
* Actuator Endpoint: http://localhost:8080/actuator
* Sonarqube UI: http://localhost:9001
<%_ if (traceType === 'zipkin') { _%>
* Zipkin UI: http://localhost:9411/
<%_ } _%>
<%_ if (loggingType === 'elk') { _%>
* Kibana: http://localhost:5601/
<%_ } _%>
<%_ if (features.includes('monitor')) { _%>
* Prometheus: http://localhost:9090/
* Grafana: http://localhost:3000/ (admin/admin)
<%_ } _%>
