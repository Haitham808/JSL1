import common.Global

@SuppressWarnings(["GroovyAssignabilityCheck", 'GroovyParameterNamingConvention', 'GroovyConditional', 'GroovyMethodWithMoreThanThreeNegations', 'GroovyOverlyLongMethod', 'GroovyOverlyComplexMethod'])
def call(final Map<String, Object> params = [:]) {

     Global.set(this, params)

    pipeline {

        
        agent {
            kubernetes {
                    label "haitham-test"
                    yaml """
apiVersion: v1
kind: Pod
spec:
    containers:
    - name: nginx
      image: nginx
    - name: kaniko
      image: gcr.io/kaniko-project/executor:debug  
      command:
      - cat
      tty: true
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
            stage('build'){
                steps{
                    container('kaniko'){
                            script{
                             Global.script.sh('/kaniko/executor --context ./ --dockerfile=./Dockerfile --destination=us-central1-docker.pkg.dev/playground-s-11-334a3823/my-gcr/test:1212 ')
                            //Global.script.sh('echo hii')
                        }
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