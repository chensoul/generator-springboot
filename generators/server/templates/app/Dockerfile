FROM <%= JAVA_IMAGE %> AS builder
WORKDIR /build
<%_ if (buildTool === 'maven') { _%>
ARG JAR_FILE=target/<%= appName %>-<%= DEFAULT_APP_VERSION %>.jar
<%_ } _%>
<%_ if (buildTool === 'gradle') { _%>
ARG JAR_FILE=build/libs/<%= appName %>-<%= DEFAULT_APP_VERSION %>.jar
<%_ } _%>
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM <%= JAVA_IMAGE %>
WORKDIR /app
COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/application/ ./
EXPOSE 8080
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]