# terminal

Terminal of cash manager project.

## Wireframes

[Application pages](https://www.figma.com/file/qCPhOBykgLhAbbLjAXzRj1/T-DEV-700-Cash-Manager?type=design&node-id=35%3A0&mode=design&t=5Ltb4mmC2RZlxf5R-1)

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

### Test

Do test with `flutter test`. You can do test coverage :

```sh
# Generate `coverage/lcov.info` file
flutter test --coverage
# Generate HTML report
# Note: on macOS you need to have lcov installed on your system (`brew install lcov`) to use this:
genhtml coverage/lcov.info -o coverage/html
# Open the report
open coverage/html/index.html
```
