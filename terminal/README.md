# terminal

Terminal of cash manager project.

## Getting Started

Follow installation steps of [flutter](https://docs.flutter.dev/get-started/install).

[Set up editor](https://docs.flutter.dev/get-started/test-drive) and [run](https://docs.flutter.dev/get-started/test-drive?tab=terminal) the project

## Build APK

In the root of the project do

```
docker compose up
```

This will build your apk in `terminal/build/app/outputs/flutter-apk`.

### APK

Flutter allow bulding apk in 2 different ways. One is a fat APK that contains your code compiled for all the target ABIs(armeabi-v7a (ARM 32-bit), arm64-v8a (ARM 64-bit), and x86-64 (x86 64-bit)). The other split the APK for each target. For this project we will build a fat APK.

- arm64: This refers to ARM 64-bit, which is used by many modern Android devices.
- x86: This refers to x86 32-bit, which is used by some older Android devices and emulators.
- armeabi: This refers to ARM 32-bit, which is used by some older Android devices
