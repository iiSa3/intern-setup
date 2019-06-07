pipeline {
    agent { docker { image 'maven:3.6.1' } }
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
