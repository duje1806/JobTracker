pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK24'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/duje1806/JobTracker'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
    }
}
