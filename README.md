# Cash-Manager

Original repository: https://github.com/MelvinCou/cash-manager

## Initialization of the back project (CashManagerServer)
This part of the project is organized as modules, that can be launch independently

- Go the folder CashManagerServer with the command cd CashManagerServer
- command mvn clean install => to load the dependencies and create the jar files that represents each module
- if the module contains a compose.yaml file, you will need to create at its root a .env.local file that follow this template :

POSTGRES_DB=*******

POSTGRES_PASSWORD=******

POSTGRES_USER=*******

(don't forget to launch your docker engine if it's not already running)

- command mvn --projects com.cashmanager.server:[module] spring-boot:run => to run a particular module

Be careful ! If you're not using the last command, but the running tool of your IDE, uncomment the second line in the application.properties of the corresponding module.

