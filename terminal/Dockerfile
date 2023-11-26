FROM mobiledevops/flutter-sdk-image

#Update sdkmanager packages
RUN $ANDROID_HOME/cmdline-tools/bin/sdkmanager --update  --sdk_root=${ANDROID_SDK_ROOT}

#Flutter need the latest android command line tools
RUN $ANDROID_HOME/cmdline-tools/bin/sdkmanager --install "cmdline-tools;latest"  --sdk_root=${ANDROID_SDK_ROOT}

RUN  flutter doctor --android-licenses

WORKDIR /terminal

RUN flutter upgrade

COPY . .

#The entrypoint command require root access
USER root

CMD [ "flutter" , "build" , "apk" ]
