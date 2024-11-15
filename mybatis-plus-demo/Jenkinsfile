pipeline {
    agent any

    triggers {
            pollSCM('* * * * *')
    }

    environment {
        APPLICATION_NAME = 'myservice'
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean verify'
            }
        }
    }
}
