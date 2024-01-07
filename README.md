# Cash-Manager

Original repository: https://github.com/MelvinCou/cash-manager

## Initialization of the back project (Server)

This part of the project is organized as modules, that can be launch independently

### Launch one module

1. Go the `Server` folder

```sh
cd Server
```

2. Load the dependencies and create the jar files that represents each module

```sh
mvn clean install
```

3. if the module contains a compose.yaml file, copy the `.env.local.exemple` file to `.env.local` in the `Server` folder and fill it with the correct data. Also edit the file `src/resources/application.properties` of the module.

```sh
cp .env.local.exemple .env.local
```

> [!Note]
> Don't forget to launch your docker engine if it's not already running

4. Run a particular module

```sh
mvn --projects :[module] spring-boot:run 
```

### Use of API_GATEWAY and EUREKA_SERVER and MEDIATOR
To manage the microservices of the application, you need to first launch the module eureka_server (see the command above).
Then launch the module api_gateway and the module mediator.
You can have access to the Dashboard of the eureka_server at http://localhost:8080. You will see all the microservices.

Server port for the modules :
- EUREKA_SERVER : 8080
- API_GATEWAY : 9191
- MEDIATOR :  8081
- ACCOUNT : 8082
- BANK-DATABASE : 8083
- TRANSACTION : 8084
- PRODUCT : 8085
- SHOP-DATABASE : 8086
- ORDER : 8087

### For the endpoints documentation
You can access the documentation of each endpoints after launching at this address : http://<localhost:service port>/swagger-ui/index.html#. This page also allow you to try the different endpoints of the concerned service.


# terminal

Terminal of cash manager project.

## Wireframes

[Application pages](https://www.figma.com/file/qCPhOBykgLhAbbLjAXzRj1/T-DEV-700-Cash-Manager?type=design&node-id=35%3A0&mode=design&t=5Ltb4mmC2RZlxf5R-1)

## Getting Started

Follow installation steps of [flutter](https://docs.flutter.dev/get-started/install).

1. [Set up editor](https://docs.flutter.dev/get-started/test-drive)

2. Get the project dependencies
If your not already in the terminal folder :

```sh
cd terminal
```
Then :

```sh
flutter pub get
```

3. [run](https://docs.flutter.dev/get-started/test-drive?tab=terminal) the project


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