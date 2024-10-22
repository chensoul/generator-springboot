FROM eclipse-temurin:<%= JDK_IMAGE_VERSION %> AS builder
WORKDIR /build
<%_ if (buildTool === 'maven') { _%>
ARG JAR_FILE=target/<%= appName %>-<%= DEFAULT_APP_VERSION %>.jar
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
ARG JAR_FILE=build/libs/<%= appName %>-<%= DEFAULT_APP_VERSION %>.jar
<%_ } _%>
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# the second stage of our build will copy the extracted layers
FROM eclipse-temurin:<%= JDK_IMAGE_VERSION %>
WORKDIR /app
COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/application/ ./
EXPOSE 8080
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]