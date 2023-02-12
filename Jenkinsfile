#!/usr/bin/env groovy

//@Library('Jenkins-share-Lib')       /*Note this is used when you confiured jenkins share lib on the global level and If you dont have import or def below use @Library('Jenkins-share-Lib')_*/

library identifier: 'jenkins-shared-lib@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/EzeChinedumUchenna/Jenkins-Share-Library-Project.git',
    credentialsId: 'nedu-cred']
)
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
        stage("initiating..........") {
            
            steps {
                script {
                    gv = load "script.groovy"
                    echo "this is initializing"
                    echo "this is the new version ${params.VERSION}"
                }
            }
        }

        stage('increment Version') {
            steps  {
                script {
                    echo 'incrementing App version...'
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnweVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                 
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_VERSION = "${version}-${BUILD_NUMBER}" 
                 }
            }
        }

        stage("build jar..") {
            when {
                expression{

                    BRANCH_NAME == 'main'
                }
            }
            steps {
                //sh "mvn install" 
                script {
                    buildJar()
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
                     BRANCH_NAME == 'main'
                }
            }
            steps {  
                script {
                    buildImage("nedumdocker/maven-java-nana:${IMAGE_VERSION}")
                }
            }
        }
        stage("deploying...") {
            /*input {
                message "select the environment to deploy to"
                ok "apply"
                parameters{
                    choice(name: 'ENV_1', choices:['dev','staging','prod'], description: '')
                    choice(name: 'ENV_2', choices:['dev','staging','prod'], description: '')
                }*/

            // when {
            //         expression {
            //             BRANCH_NAME == 'main'
            //         }
            //     }

            when {

            expression {
                    params.executeTests == true
                    BRANCH_NAME == 'main'
                    
                }
            }
            
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
