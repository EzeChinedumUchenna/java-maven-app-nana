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
    

    }  */
    tools {
        maven 'maven-3.8'
        // maven, gradle and jdk are the only built tools that you can use on tool
    }
    agent any 
    stages {
        stage("initiating") {
            
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
                    gv.buildJar()
                }
            }
        }

        stage("testing") {
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
        stage("building image") {
            when {

                expression {
                     params.executeTests == true
                }
            }
            steps {  
                script {
                    gv.buildImage()
                }
            }
        }
        stage("deploying") {
            /*input {
                message "select the environment to deploy to"
                ok "apply"
                parameters{
                    choice(name: 'ENV_1', choices:['dev','staging','prod'], description: '')
                    choice(name: 'ENV_2', choices:['dev','staging','prod'], description: '')
                }*/
            
            steps {
                echo "deploying the application"
                /*withCredentials([
                    usernamePassword(credentials: 'Demo-server-cred', usernameVariable: USER, passwordVariable: PWD)
                ])

                sh "some script ${USER} , ${PWD}"*/
                script {
                    env.ENV = input message: "select the environment to deploy to", ok: "apply", parameters: [choice(name: 'ONE', choices: ['dev','stage', 'prod'], description: '')]
                    env.ENV = input message: "select the environment to deploy to", ok: "apply", parameters: [choice(name: 'TWO', choices: ['dev','stage', 'prod'], description: '')]
                    gv.deployApp()
                    echo "Deploying to ${params.ONE}"
                    echo "Deploying to ${params.TWO}"
                }
            }
        }
    }   
    /*post {


    }*/
}