
module.exports = {
    prompting
};

function prompting() {

    const done = this.async();

    const prompts = [
        {
            type: 'string',
            name: 'appName',
            validate: input =>
                /^([a-z_][a-z0-9_\-]*)$/.test(input)
                    ? true
                    : 'The application name you have provided is not valid',
            message: 'What is the base name of your application?',
            default: 'myservice'
        },
        {
            type: 'string',
            name: 'packageName',
            validate: input =>
                /^([a-z_][a-z0-9_]*(\.[a-z_][a-z0-9_]*)*)$/.test(input)
                    ? true
                    : 'The package name you have provided is not a valid Java package name.',
            message: 'What is your default Java package name?',
            default: 'com.mycompany.myapp'
        },
        {
            type: 'list',
            name: 'databaseType',
            message: 'Which type of database you want to use?',
            choices: [
                {
                    value: 'postgresql',
                    name: 'PostgreSQL'
                },
                {
                    value: 'mysql',
                    name: 'MySQL'
                },
                {
                    value: 'mariadb',
                    name: 'MariaDB'
                }
            ],
            default: 'postgresql'
        },
        {
            type: 'list',
            name: 'dbMigrationTool',
            message: 'Which type of database migration tool you want to use?',
            choices: [
                {
                    value: 'flywaydb',
                    name: 'FlywayDB'
                },
                {
                    value: 'liquibase',
                    name: 'Liquibase'
                },
                {
                    value: 'none',
                    name: 'None'
                }
            ],
            default: 'flywaydb'
        },
        {
            type: 'list',
            name: 'persistence',
            message: 'Which type of persistence framework you want to use?',
            choices: [
                {
                    value: 'jpa',
                    name: 'JPA'
                },
                {
                    value: 'mybatis',
                    name: 'Mybatis'
                }
            ],
            default: 'jpa'
        },
        {
            when: (answers) => answers.dbMigrationTool === 'liquibase',
            type: 'list',
            name: 'dbMigrationFormat',
            message: 'Which format do you want to use for database migrations?',
            choices: [
                {
                    value: 'xml',
                    name: 'XML (like \'001-init.xml\')'
                },
                {
                    value: 'yaml',
                    name: 'YAML (like \'001-init.yaml\')'
                },
                {
                    value: 'sql',
                    name: 'SQL (like \'001-init.sql\')'
                }
            ],
            default: 'xml'
        },
        {
            type: 'list',
            name: 'loggingType',
            message: 'Which type of logging tool you want to use?',
            choices: [
                {
                    value: 'loki',
                    name: 'Loki'
                } ,
                {
                    value: 'elk',
                    name: 'Elasticsearch, Kibana, Logstash'
                },
                {
                    value: 'none',
                    name: 'None'
                }
            ],
            default: 'none'
        },
        {
            type: 'checkbox',
            name: 'features',
            message: 'Select the features you want?',
            choices: [
                {
                    value: 'zipkin',
                    name: 'Zipkin'
                },
                {
                    value: 'localstack',
                    name: 'Localstack'
                },
                {
                    value: 'monitor',
                    name: 'Prometheus, Grafana, Tempo'
                }
            ]
        },
        {
            type: 'list',
            name: 'buildTool',
            message: 'Which type of building tool you want to use?',
            choices: [
                {
                    value: 'maven',
                    name: 'Maven'
                },
                {
                    value: 'gradle',
                    name: 'Gradle'
                }
            ],
            default: 'maven'
        }
    ];

    this.prompt(prompts).then(answers => {
        Object.assign(this.configOptions, answers);
        this.configOptions.packageFolder = this.configOptions.packageName.replace(/\./g, '/');
        this.configOptions.features = this.configOptions.features || [];
        done();
    });
}
