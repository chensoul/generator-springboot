# <%= appName %>

This project was created with [generator-springboot](https://github.com/chensoul/generator-springboot/).

## Build

The application can be built using the following command:

```bash
<%_ if (buildTool === 'maven') { _%>
./mvnw clean package
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew clean build
<%_ } _%>
```

## Format code

```shell
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw spotless:apply
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew spotlessApply
<%_ } _%>
```

## Run SonarQube

```shell
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew sonarqube -Dsonar.login=admin -Dsonar.password=admin
<%_ } _%>
```

<%_ if (dbMigrationTool === 'flywaydb') { _%>
## Repair Flyway Schema History Table

```shell
<%_ if (buildTool === 'maven') { _%>
  <%_ if (databaseType === 'postgresql') { _%>
./mvnw flyway:repair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mysql') { _%>
./mvnw flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mariadb') { _%>
./mvnw flyway:repair -Dflyway.url=jdbc:mariadb://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
  <%_ if (databaseType === 'postgresql') { _%>
./gradlew flywayRepair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mysql') { _%>
./gradlew flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mariadb') { _%>
./gradlew flywayRepair -Dflyway.url=jdbc:mariadb://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
<%_ } _%>
```
<%_ } _%>

## Useful Links

* Springdoc UI: http://localhost:8080/swagger-ui.html
* Actuator Endpoint: http://localhost:8080/actuator
* Sonarqube UI: http://localhost:9001
<%_ if (features.includes('zipkin')) { _%>
* Zipkin UI: http://localhost:9411/
<%_ } _%>
<%_ if (loggingType === 'elk') { _%>
* Kibana: http://localhost:5601/
<%_ } _%>
<%_ if (features.includes('monitor')) { _%>
* Prometheus: http://localhost:9090/
* Grafana: http://localhost:3000/ (admin/admin)
<%_ } _%>