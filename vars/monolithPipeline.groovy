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
                            // Global.script.sh('/kaniko/executor --context ./ --dockerfile=./Dockerfile --destination=gcr.io/playground-s-11-c6a56f22/test:1212 ')
                            Global.script.sh('echo hii')
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