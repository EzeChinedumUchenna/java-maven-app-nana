def gv

//CODE_CHANGES = getGitChanges() 

pipeline {
    parameters{
        //string(name: 'VERSION', defaultValue: '', description: 'version to deplay on prod')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.1'], description: 'This is the version of the maven')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    /*environment {
    SERVER_CREDENTIALS = credentials('Demo-server-cred')
    NEW_VERSION = '1.3.4'

    }  
    /*tool {
        maven 'maven-3.8'
        // maven, gradle and jdk are the only built tools that you can use on tool
    }*/
    agent any
    stages {
        stage("init") {
            
            steps {
                script {
                    gv = load "script.groovy"
                    echo "this is initializing"
                    echo "this is the new version ${params.VERSION}"
                }
            }
        }
        stage("build jar") {
            /*when {
                expression{

                    BRANCH_NAME == 'main' && CODE_CHANGES == true
                }*/
            steps {
                //sh "mvn install" 
                script {
                    gv.buildApp()
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
            input {
                message "select the environment to deploy to"
                ok "don"
                parameters{
                    choice(name: 'ENV', choices:['dev','staging','prod'], description: '')
                }
            }
            steps {
                echo "deploying the application"
                /*withCredentials([
                    usernamePassword(credentials: 'Demo-server-cred', usernameVariable: USER, passwordVariable: PWD)
                ])

                sh "some script ${USER} , ${PWD}"*/
                script {
                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                }
            }
        }
    }   
    /*post {


    }*/
}
