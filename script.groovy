def buid() {
    echo 'Bulding components'
    sh 'mvn -v'
}

def test() {
    echo 'testing application'
}

def  post_test() {
    echo "Post test running on ${params.Version}"
}

def deploy() {
    echo 'Deploying application'
    echo "${VERSION}"
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'cloud-server', usernameVariable: 'USER', passwordVariable: 'PASSWD']]) {
        sh "echo ${USER} ${PASSWD}"
    }
}

return this