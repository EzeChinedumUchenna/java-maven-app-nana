 def buildJar() {
    echo "Building the application in .jar."
    sh 'mvn package'
} 

 def buildApp() {
    echo "building"
    //gv.buildJar()
    def test = 1 + 5 > 3 ? 'yes' : 'Not at all'
    echo test
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-ub-credentials', passwordVariable: 'PWD', usernameVariable: 'USER')]) {
        sh 'docker build -t nedumdocker/maven-java-nana:jma-2.0 .'
        sh "echo $PWD | docker login -u $USER --password-stdin"
        sh 'docker push nedumdocker/maven-java-nana:jma-2.0'
    }
} 

def testApp() {
    echo "testing App"
    //gv.buildImage()
    echo "testing App version ${params.VERSION}"
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