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
}

return this