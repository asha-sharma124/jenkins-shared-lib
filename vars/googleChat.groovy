
def call(){
withCredentials([string(credentialsId: 'webhook', variable: 'WEBHOOK')]) {
       script{ def message = [
                       text: "Jenkins Deployment Successful for branch: staging [#${env.BUILD_NUMBER}]"
                    ]
            writeFile file: 'payload.json', text: groovy.json.JsonOutput.toJson(message)

            sh '''
                curl -X POST -H "Content-Type: application/json" \
                -d @payload.json \
                "$WEBHOOK"
            '''
        }}}
