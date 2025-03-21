pipeline {
    agent any

    tools {
        maven "M3"
    }

    triggers {
        cron('0 8 * * *')
    }

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'BRANCH', type: 'PT_BRANCH'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/m1nuano/regoffice-selenium-practice.git'
            }
        }
        stage('Build and Test') {
            steps {
                bat "mvn clean test"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            emailext(
                subject: "Результаты тестов: ${currentBuild.currentResult} (Сборка #${currentBuild.number})",
                body: """
                        <p>Статус сборки: ${currentBuild.currentResult}</p>
                        <p>Ссылка на сборку: ${env.BUILD_URL}</p>
                        <p>Отчет Allure: <a href="${env.BUILD_URL}allure/">здесь</a></p>
                    """,
                to: 'aliakseiliyutich@gmail.com',
                attachmentsPattern: 'target/allure-report/**/*.zip',
                mimeType: 'text/html'
            )
        }
    }
}