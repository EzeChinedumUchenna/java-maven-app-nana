def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps { 
                script {
                    echo "building jar"
                    //gv.buildJar()
                    def test 1 + 3 > 3 ? 'yes' : 'Not at all'
                    echo test
                }
            }
        }
        stage("build image") {
            steps {  
                script {
                    echo "building image"
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    //gv.deployApp()
                }
            }
        }
    }   
}
