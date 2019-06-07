pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V'
                sh 'mvn test -B'
                sh 'mvn spotbugs:check'
            }
        }
    }
}