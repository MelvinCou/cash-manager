# Cash-Manager

Original repository: https://github.com/MelvinCou/cash-manager

## Initialization of the back project (CashManagerServer)

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

### Use of API_GATEWAY and EUREKA_SERVER
To manage the microservices of the application, you need to first launch the module eureka_server (see the command above).
Then launch the module api_gateway.
You can have access to the Dashboard of the eureka_server at http://localhost:8761. You will see all the microservices.