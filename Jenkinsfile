pipeline {
    agent { docker { image 'maven:3.6.1' } }
    stages {
        stage('build') {
            steps {
                sh 'gradle cucumber'
                sh './gradlew sonarqube -Dsonar.projectKey=iiSa3_intern-setup -Dsonar.organization=iisa3-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=0ad84e748159459a4284d191602e8e20c64a13df'
            }
        }
    }
}
