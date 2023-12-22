import haitham.com.au.common.Global

@SuppressWarnings(["GroovyAssignabilityCheck", 'GroovyParameterNamingConvention', 'GroovyConditional', 'GroovyMethodWithMoreThanThreeNegations', 'GroovyOverlyLongMethod', 'GroovyOverlyComplexMethod'])
def call() {

     Global.set(this, params)

    pipeline {
        
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
                           """
              }
          }
        stages {
            stage('Hello') {
                steps {
                    echo 'Hello World'
                }
            }
        }
    }
}