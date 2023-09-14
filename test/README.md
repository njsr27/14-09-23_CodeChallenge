<!-- GETTING STARTED -->

## Getting Started

### Installation

1. Clone the project from the
   gitlab [repo](https://gitlab.agilecontent.com/tech-challenges/Nicolas-Saavedra-java-backend-code-callenge)

```sh
  git clone git@gitlab.agilecontent.com:tech-challenges/Nicolas-Saavedra-java-backend-code-callenge.git
```

2. Open a **Terminal** at the root of the project and move to the `demo` folder

```sh
  cd .\demo\
```

3. Here, you can execute the command below to run the application:

```sh
./mvnw spring-boot:run
```

4. The application should have started under port **8080**, to validate it, you can use any of the ways mentioned
   under **Usage**

## Usage

### H2 Database

If the application started correctly, you should be able to access the H2 console using the URL:

```sh
http://localhost:8080/h2-console/ 
```

Where you can access the in-memory database using the form values:

```
Saved settings: Generic H2 (Embedded)
Saved Name: Generic H2 (Embedded)
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:mydb
User name: sa
Password: 
```

In this case, the table `TBL_USERS` will be the one we use for this service, it should have 5 entities created as base

### Swagger

To access the swagger of the service, use the URL:

```sh
http://localhost:8080/swagger-ui/index.html
```

### Postman

To test the service, under the directory `test` at the root of the project, you can find the
file `CodeChallenge.postman_collection.json`
which includes the Postman collection that can be imported, with the endpoints of the service plus some extra
endpoints to test errors