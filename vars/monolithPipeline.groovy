import common.Global

@SuppressWarnings(["GroovyAssignabilityCheck", 'GroovyParameterNamingConvention', 'GroovyConditional', 'GroovyMethodWithMoreThanThreeNegations', 'GroovyOverlyLongMethod', 'GroovyOverlyComplexMethod'])
def call(final Map<String, Object> params = [:]) {

     Global.set(this, params)

    pipeline {

        environment {
        GOOGLE_CREDENTIALS = credentials('gcr')
    }
        
        agent {
            kubernetes {
                    label "haitham-test"
                    yaml """
                    apiVersion: v1
                    kind: Pod
                    metadata:
                    name: nginx-echo-hello-world
                    spec:
                    containers:
                      - name: nginx
                        image: nginx:latest
                        command: ["/bin/sh"]
                        args: ["sleep","3600"]
                      - name: kaniko
                        image: gcr.io/kaniko-project/executor:latest  
                        volumeMounts:
                          - name: docker-config
                            mountPath: /kaniko/.docker
                    volumes:
                      - name: docker-config
                        secret:
                          secretName: kaniko-secret
                      - name: workspace
                        emptyDir: {}    
                           """
              }
          }
        stages {
            stage('builf'){
                steps{
                    script{
                        //Global.script.sh('/kaniko/executor --context ./ --dockerfile=./Dockerfile --destination=gcr.io/playground-s-11-c6a56f22/test:1212 ')
                    }
                }
            }
            stage('Hello') {
                steps {
                    echo 'Hello World'
                }
            }
        }
    }
}