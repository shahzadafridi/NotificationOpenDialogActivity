#!/bin/bash

function checkbin() {
  type -P su-exec
}

function su_mt_user() {
  su android -c '"$0" "$@"' -- "$@"
}

chown android:android /opt/android-sdk-linux

if checkbin; then
  echo "checkbin success"
  exec su-exec android:android /opt/tools/android_sdk.sh
else
  echo "checkbin failed"
  chmod +x /opt/tools/android_sdk.sh
  su -c /opt/tools/android_sdk.sh android
fi

chmod -R 0777 /opt/android-sdk-linux