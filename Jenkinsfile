def gv

pipeline {
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.1'], description: '')
        booleanParam(name: 'executeTests', defaultValue: false, description: '')
    }
    //environment {
        //NEW_VERSION = '1.3.0'
    //}
    /*tool {
        maven 'maven-3.8'
        // gradle and jdk are the only built tools that you can use on tool
    }*/
    agent any
    stages {
        stage("init") {
            
            steps {
                script {
                    gv = load "script.groovy"
                    echo "this is initializing"
                }
            }
        }
        stage("build jar") {
            /*when {
                expression{

                    BRANCH_NAME == 'main' && CODE_CHANGES == true
                }*/
            steps { 
                script {
                    echo "building jar"
                    //gv.buildJar()
                    def test = 1 + 5 > 3 ? 'yes' : 'Not at all'
                    echo test
                }
            }
        }
        stage("test") {
            /*when {
                expression{

                    BRANCH_NAME == 'main' || BRANCH_NAME == 'dev'

                }
                
            }*/
            steps {  
                script {
                    echo "building image"
                    //gv.buildImage()
                    echo "building version ${params.NEW_VERSION}"
                }
            }
        }
        stage("build image") {
            when {

                expression {
                     params.executeTests == true
                }
            }
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
                    echo "deploying ${params.VERSION}"
                    //gv.deployApp()
                }
            }
        }
    }   
    /*post {


    }*/
}
