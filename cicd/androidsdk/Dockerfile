FROM --platform=linux/amd64 ubuntu:latest

ENV ANDROID_HOME      /opt/android-sdk-linux
ENV ANDROID_SDK_HOME  ${ANDROID_HOME}
ENV ANDROID_SDK_ROOT  ${ANDROID_HOME}
ENV ANDROID_SDK       ${ANDROID_HOME}

ENV PATH "${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin"
ENV PATH "${PATH}:${ANDROID_HOME}/cmdline-tools/tools/bin"
ENV PATH "${PATH}:${ANDROID_HOME}/tools/bin"
ENV PATH "${PATH}:${ANDROID_HOME}/build-tools/${SDK_VERSION}"
ENV PATH "${PATH}:${ANDROID_HOME}/platform-tools"
ENV PATH "${PATH}:${ANDROID_HOME}/emulator"
ENV PATH "${PATH}:${ANDROID_HOME}/bin"

RUN apt-get update \
 && apt-get install -y openjdk-11-jdk wget unzip git \
 && apt-get clean

RUN groupadd android && useradd -d /opt/android-sdk-linux -g android android

COPY tools /opt/tools

WORKDIR /opt/android-sdk-linux

RUN ["chmod", "+x", "/opt/tools/entrypoint.sh"]
RUN /opt/tools/entrypoint.sh built-in

# RUN /opt/android-sdk-linux/cmdline-tools/tools/bin/sdkmanager "cmdline-tools;latest"
# RUN /opt/android-sdk-linux/cmdline-tools/tools/bin/sdkmanager "build-tools;30.0.2"
# RUN /opt/android-sdk-linux/cmdline-tools/tools/bin/sdkmanager "platform-tools"
# RUN /opt/android-sdk-linux/cmdline-tools/tools/bin/sdkmanager "platforms;android-30"

# CMD /opt/tools/entrypoint.sh built-in