pipeline {
    agent any

    stages {
        stage('Get Source Code') {
            steps {
                git credentialsId: '3958ba82-6892-42d1-921c-7576c31b2866', url: 'https://github.com/Hemant-Purswani/JenkinsDemo.git'
                echo 'Hello World'
            }
        }
        stage('Build code')
                {
                    steps
                            {
                                bat script: 'mvn compile'
                            }
                }
        stage('Run Test')
                {
                    steps
                            {
                                bat script: 'mvn test -Dbrowser=localchrome'
                            }
                }
        stage('Publish Report')
                {
                    steps
                            {
                                publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: '', reportFiles: 'target/surefire-reports/Extent*.html', reportName: 'Pipeline', reportTitles: ''])
                            }
                }
    }
}
