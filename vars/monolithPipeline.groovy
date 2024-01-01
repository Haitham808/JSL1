import common.Global

@SuppressWarnings(["GroovyAssignabilityCheck", 'GroovyParameterNamingConvention', 'GroovyConditional', 'GroovyMethodWithMoreThanThreeNegations', 'GroovyOverlyLongMethod', 'GroovyOverlyComplexMethod'])
def call(final Map<String, Object> params = [:]) {

     Global.set(this, params)

    pipeline {

        agent any
        
        // agent {
        //     kubernetes {
        //             label "haitham-test"
        //             yaml """
        //             apiVersion: v1
        //             kind: Pod
        //             metadata:
        //             name: nginx-echo-hello-world
        //             spec:
        //             containers:
        //               - name: nginx
        //                 image: nginx:latest
        //                 command: ["/bin/sh"]
        //                 args: ["sleep","3600"]
        //                    """
        //       }
        //   }
        stages {
            stage('Hello') {
                steps {
                    script{
                        Global.script.sh('cat /etc/hostname')
                    }
                     
                }
            }
        }
    }
}