def buildApp() {
    echo "building"
    //gv.buildJar()
    def test = 1 + 5 > 3 ? 'yes' : 'Not at all'
    echo test
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
