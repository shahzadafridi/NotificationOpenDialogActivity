#!/bin/bash

echo "Set up environment variables"

export ANDROID_HOME=/opt/android-sdk-linux

export PATH=${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin
export PATH=${PATH}:${ANDROID_HOME}/cmdline-tools/tools/bin
export PATH=${PATH}:${ANDROID_HOME}/tools/bin
export PATH=${PATH}:${ANDROID_HOME}/build-tools/30.0.3
export PATH=${PATH}:${ANDROID_HOME}/platform-tools
export PATH=${PATH}:${ANDROID_HOME}/emulator
export PATH=${PATH}:${ANDROID_HOME}/bin

echo $PATH