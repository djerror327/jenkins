pipeline{
    agent any
    environment{
        VERSION="1.0.0"
        // SERVER_CREDENTIAL=credentials('cloud-server')
    }
    stages{
        stage("build"){
            steps{
                echo "Bulding components"
            }
        }
        stage("Test"){
            when{
                expression{
                    BRANCH_NAME == "dev"
                }
            }
            steps{
                echo "testing application"
            }
            
        }
        stage("Deploy"){
           steps{
                echo "Deploying application"
                echo "${VERSION}"
                // sh("echo ${SERVER_CREDENTIAL}")
                 withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'cloud-server', usernameVariable: 'USER', passwordVariable: 'PASSWD']]) {
                    sh "echo ${USER} ${PASSWD}"
                }
           }
        }
    }
    post{
        always{
            echo "always block"
        }
        success{
            echo "success block"
        }
        failure{
            echo "failiure block"
        }
    }
}