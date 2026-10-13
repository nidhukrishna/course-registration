pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'course-registration-app/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'course-registration-app/target/*.jar', fingerprint: true
                }
            }
        }
    }
}