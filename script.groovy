 def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

 def buildApp() {
    echo "building jar"
    //gv.buildJar()
    def test = 1 + 5 > 3 ? 'yes' : 'Not at all'
    echo test
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t nanajanashia/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push nanajanashia/demo-app:jma-2.0'
    }
} 

def testApp() {
    echo "building image"
    //gv.buildImage()
    echo "building version ${params.VERSION}"
} 

def buildingApp() {
    echo "building image"
    //gv.buildImage()
}

def deployApp() {
    echo "deploying ${params.VERSION}"
    //gv.deployApp()
}


return this