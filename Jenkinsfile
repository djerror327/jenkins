pipeline{
    agent any
    stages{
        stage("build"){
            steps{
                echo "Bulding components"
            }
        }
        stage("Test"){
            steps{
                echo "testing application"
            }
            
        }
        stage("Deploy"){
           steps{
                echo "Deploying application"
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
            "failiure block"
        }
    }
}