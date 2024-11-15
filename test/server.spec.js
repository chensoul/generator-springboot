const path = require('path');
const assert = require('yeoman-assert');
const helpers = require('yeoman-test');

describe('SpringBoot Generator', () => {

    // Maven based generation
    describe('Generate minimal microservice using Maven', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "buildTool": "maven",
                    "features": []
                })
                .on('end', done);
        });

        it('creates expected default files for minimal microservice with maven', () => {
            assert.file('myservice/pom.xml');
        });
    });

    describe('Generate basic microservice using Maven with Flyway', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "databaseType": "postgresql",
                    "dbMigrationTool": "flywaydb",
                    "buildTool": "maven",
                    "features": []
                })
                .on('end', done);
        });

        it('creates expected default files for basic microservice with maven', () => {
            assert.file('myservice/pom.xml');
        });
    });

    describe('Generate basic microservice using Maven with Liquibase', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "databaseType": "postgresql",
                    "dbMigrationTool": "liquibase",
                    "buildTool": "maven",
                    "features": []
                })
                .on('end', done);
        });

        it('creates expected default files for basic microservice with maven', () => {
            assert.file('myservice/pom.xml');
        });
    });

    describe('Generate complete microservice using Maven', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "databaseType": "postgresql",
                    "dbMigrationTool": "flywaydb",
                    "buildTool": "maven",
                    "features": ["elk", "monitor"]
                })
                .on('end', done);
        });

        it('creates expected default files for complete microservice with maven', () => {
            assert.file('myservice/pom.xml');
            assert.file('myservice/docker-compose.yml');
            assert.file('myservice/docker-compose-elk.yml');
            assert.file('myservice/docker-compose-monitor.yml');
        });
    });

    // Gradle based generation
    describe('Generate minimal microservice using Gradle', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "buildTool": "gradle",
                    "features": []
                })
                .on('end', done);
        });

        it('creates expected default files for minimal microservice with Gradle', () => {
            assert.file('myservice/build.gradle');
        });
    });

    describe('Generate basic microservice using Gradle with Flyway', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "databaseType": "postgresql",
                    "dbMigrationTool": "flywaydb",
                    "buildTool": "gradle",
                    "loggingType": "elk",
                    "features": []
                })
                .on('end', done);
        });

        it('creates expected default files for basic microservice with Gradle', () => {
            assert.file('myservice/build.gradle');
        });
    });

    describe('Generate basic microservice using Gradle with Liquibase', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "databaseType": "postgresql",
                    "dbMigrationTool": "liquibase",
                    "buildTool": "gradle",
                    "loggingType": "elk",
                    "features": []
                })
                .on('end', done);
        });

        it('creates expected default files for basic microservice with maven', () => {
            assert.file('myservice/build.gradle');
        });
    });

    describe('Generate complete microservice using Gradle', () => {
        before(done => {
            helpers
                .run(path.join(__dirname, '../generators/server'))
                .withPrompts({
                    "appName": "myservice",
                    "packageName": "com.mycompany",
                    "packageFolder": "com/mycompany/myservice",
                    "databaseType": "postgresql",
                    "dbMigrationTool": "flywaydb",
                    "buildTool": "gradle",
                    "loggingType": "elk",
                    "features": ["monitor"]
                })
                .on('end', done);
        });

        it('creates expected default files for complete microservice with Gradle', () => {
            assert.file('myservice/build.gradle');
            assert.file('myservice/docker-compose.yml');
            assert.file('myservice/docker-compose-elk.yml');
            assert.file('myservice/docker-compose-monitor.yml');
        });
    });
});