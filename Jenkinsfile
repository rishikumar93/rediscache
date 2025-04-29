pipeline {
    agent any
    
    tools {
        maven 'local_maven'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    echo 'Archiving the artifacts'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage('Deployments') {
            steps {
                deploy adapters: [tomcat7(path: '', url: 'http://localhost:9090/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
