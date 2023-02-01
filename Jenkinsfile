def gv

CODE_CHANGES = getGitChanges() 

pipeline {
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.1'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description '')
    }
    environment {
        NEW_VERSION = '1.3.0'
    }
    tool {
        maven 'maven-3.8'
        // gradle and jdk are the only built tools that you can use on tool
    }
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
            when {
                expression{

                    BRANCH_NAME == 'main' && CODE_CHANGES == true
                }
            steps { 
                script {
                    gv.buildApp()
                }
            }
        }
        stage("test") {
            when {
                expression{

                    BRANCH_NAME == 'main' || BRANCH_NAME == 'dev'

                }
                
            }
            steps {  
                script {
                    gv.testApp()
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
                    gv.buildingApp() 
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
    post {


    }
}
