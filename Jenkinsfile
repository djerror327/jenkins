def gv
pipeline{
    agent any
    environment{
        VERSION="1.0.0"
        // SERVER_CREDENTIAL=credentials('cloud-server')
    }
    tools{
        maven "Maven"
    }
    parameters{
        string(name: "Deployment settings", defaultValue: "", description: "setting the Deployment String test")
        choice(name:"Version", choices: ["1.0.0","2.0.0","3.0.0"],description: "")
        booleanParam(name: "executeTest", defaultValue: true, description: "check testing")
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("build"){
            steps{
                script{
                    gv.buid()
                }
            }
        }
        stage("Test"){
            when{
                expression{
                    BRANCH_NAME == "dev"
                }
            }
            steps{
                script{
                    gv.test()
                }
            }
            
        }
        stage("Post-test"){
            when{
                expression{
                    params.executeTest
                }
            }
            steps{
                script{
                    gv.post_test()
                }
            }
        }
        stage("Deploy"){
           steps{
                script{
                    gv.deploy()
                }
                // sh("echo ${SERVER_CREDENTIAL}")
                
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