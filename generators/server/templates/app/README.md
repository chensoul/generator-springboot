# <%= appName %>

This project was created with [generator-springboot](https://github.com/chensoul/generator-springboot/).

## Feature

- JDK：<%= JAVA_VERSION %>
<%_ if (buildTool === 'maven') { _%>
- Maven：<%= MAVEN_VERSION %>
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
- Gradle：<%= GRADLE_VERSION %>
<%_ } _%>
- Spring Boot：<%= SPRING_BOOT_VERSION %>
- Spring Cloud：<%= SPRING_CLOUD_VERSION %>
<%_ if (databaseType === 'postgresql') { _%>
- Postgres：<%= POSTGRESQL_IMAGE_VERSION %>
<%_ } _%>
<%_ if (databaseType === 'mysql') { _%>
- MySQL：<%= MYSQL_IMAGE_VERSION %>
<%_ } _%>
<%_ if (databaseType === 'mariadb') { _%>
- Mariadb：<%= MARIADB_IMAGE_VERSION %>
<%_ } _%>
<%_ if (persistence === 'mybatis') { _%>
- Mybatis plus：<%= MYBATIS_PLUS_VERSION %>
<%_ } _%>
<%_ if (dbMigrationTool === 'flywaydb') { _%>
- Flywaydb
<%_ } _%>
<%_ if (dbMigrationTool === 'liquibase') { _%>
- Liquibase
<%_ } _%>
- Springdoc：<%= SPRINGDOC_OPENAPI_VERSION %>
<%_ if (loggingType === 'elk' ) { _%>
- ELK：<%= ES_IMAGE_VERSION %>
<%_ } _%>
<%_ if (features.includes('monitor')) { _%>
- Grafana：<%= GRAFANA_IMAGE_VERSION %>
<%_ } _%>

## Build

The application can be built using the following command:

```bash
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw clean package
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew clean bootJar
<%_ } _%>
```

To ensure everything worked, run:

```bash
$ java -jar target/*.jar
```

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Others

### Format code

```bash
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw spotless:apply
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew spotlessApply
<%_ } _%>
```

### Repair Flyway Schema History Table

```bash
<%_ if (buildTool === 'maven') { _%>
  <%_ if (databaseType === 'postgresql') { _%>
$ ./mvnw flyway:repair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mysql') { _%>
$ ./mvnw flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mariadb') { _%>
$ ./mvnw flyway:repair -Dflyway.url=jdbc:mariadb://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
  <%_ if (databaseType === 'postgresql') { _%>
$ ./gradlew flywayRepair -Dflyway.url=jdbc:postgresql://localhost:5432/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mysql') { _%>
$ ./gradlew flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
  <%_ if (databaseType === 'mariadb') { _%>
$ ./gradlew flywayRepair -Dflyway.url=jdbc:mariadb://localhost:3306/appdb -Dflyway.user=appuser -Dflyway.password=secret
  <%_ } _%>
<%_ } _%>
```

### Code quality using Sonar

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```bash
$ docker compose -f docker-compose-sonar.yml up -d
```

Note: we have turned off forced authentication redirect for UI in [docker-compose-sonar.yml](docker-compose-sonar.yml)
for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using
the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven
plugin.

Then, run a Sonar analysis:

```bash
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew clean check jacocoTestReport sonarqube -Dsonar.login=admin -Dsonar.password=admin
<%_ } _%>
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```bash
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw initialize sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew initialize sonarqube -Dsonar.login=admin -Dsonar.password=admin
<%_ } _%>
```

Additionally, Instead of passing `sonar.password` and `sonar.login` as CLI arguments, these parameters can be configured
from [sonar-project.properties](sonar-project.properties) as shown below:

```
sonar.login=admin
sonar.password=admin
```

For more information, refer to
the [Code quality page](https://www.jhipster.tech/documentation-archive/v8.7.3/code-quality/).

### Docker Compose support

To start required services in Docker containers, run:

```bash
$ docker compose -f docker-compose.yml up -d
```

To stop and remove the containers, run:

```bash
$ docker compose -f docker-compose.yml down
```

[Spring Docker Compose Integration](https://docs.spring.io/spring-boot/reference/features/dev-services.html) is enabled
by default. It's possible to disable it in application.yml:

```yaml
spring:
  docker:
    compose:
      enabled: false
```

You can also fully dockerized your application and all the services that it depends on.
To achieve this, first build a Docker image of your app by running:

```bash
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw -ntp verify -DskipTests jib:dockerBuild
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew jibDockerBuild
<%_ } _%>
```

Or build a arm64 Docker image when using an arm64 processor os like MacOS with M1 processor family running:

```bash
<%_ if (buildTool === 'maven') { _%>
$ ./mvnw -ntp verify -DskipTests jib:dockerBuild -Djib-maven-plugin.architecture=arm64
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
$ ./gradlew jibDockerBuild -Djib-maven-plugin.architecture=arm64
<%_ } _%>
```

Then run:

```bash
$ docker compose -f docker-compose-app.yml up -d
```

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
