pipeline {

  agent any

  stages {
    stage('Test authors') {
      agent {
        dockerfile {
          dir 'cicd/python'
          args '--network bridge'
          reuseNode true
        }
      }
      steps {
        sh 'git shortlog -s -n --all  --summary --numbered --email  >> /tmp/git_authors.txt'
        sh 'python3 test_authors/femom_test_authors.py'
      }
    }

    stage('Local unit test') {
      agent {
        dockerfile {
          dir 'cicd/androidsdk'
          args '--network bridge'
          reuseNode true
        }
      }
      steps {
          sh './gradlew test'
      }
    }
    stage('Build') {
      agent {
        dockerfile {
          dir 'cicd/androidsdk'
          args '--network bridge'
          reuseNode true
        }
      }
      steps {
          sh './gradlew assembleRelease'
          sh 'ls -R app/build/outputs/apk'
          stash name: "apk", includes: 'app/build/outputs/apk/**', allowEmpty: true
          archiveArtifacts artifacts: 'app/build/outputs/apk/**', fingerprint: true
      }
    }
  }
}
