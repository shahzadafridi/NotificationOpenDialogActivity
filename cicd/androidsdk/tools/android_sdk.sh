#!/bin/bash

mkdir -p /opt/android-sdk-linux/bin/

cd $ANDROID_HOME
echo "Set ANDROID_HOME to ${ANDROID_HOME}"

echo "Copying Tools"
mkdir -p ${ANDROID_HOME}/bin
cp -v /opt/tools/*.sh ${ANDROID_HOME}/bin

source /opt/android-sdk-linux/bin/android_env.sh

if [ -f commandlinetools-linux.zip ]
then
  echo "Command line tools already bootstrapped. Skipping initial setup"
else
  echo "Bootstrapping SDK-Tools"
  wget -q https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O commandlinetools-linux.zip
  unzip commandlinetools-linux.zip
  mv cmdline-tools tools
  mkdir cmdline-tools
  mv tools cmdline-tools/tools
  rm commandlinetools-linux.zip
fi

echo "Make sure repositories.cfg exists"
mkdir -p ~/.android/
touch ~/.android/repositories.cfg

echo $PATH

echo "*** Automatically accept all SDK licences ***"
yes | sdkmanager --licenses

echo "*** Install build tools 30.0.3 ***"
sdkmanager "platforms;android-30"
sdkmanager --list | grep ndk
sdkmanager --install "ndk-bundle"
sdkmanager --install "build-tools;30.0.3"