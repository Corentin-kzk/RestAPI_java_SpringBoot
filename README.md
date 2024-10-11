# Java Spring Boot Project

## Prerequisites
- Docker
- Docker Compose
- JDK 17+

## Running the project

To start the database and PhpMyAdmin with Docker Compose, run the following command:

```bash
docker compose up -d
```

This command will start the database and PhpMyAdmin only.

## Running the Java application

Since the Java application is not included in Docker, you need to run it from the command line. Follow these steps:

1. Ensure that Java JDK 17 or a compatible version is installed.
2. Compile the project using Maven (or Gradle if you're using it) by running the following command:

```bash
./mvnw clean install
```

3. Then, start the Spring Boot server with the following command:

```bash
./mvnw spring-boot:run
```

The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Accessing PhpMyAdmin

PhpMyAdmin will be available at the following URL:

- [http://localhost:8081](http://localhost:8081)

The database credentials are configured in the `docker-compose.yml` file.

## Database configuration

The project uses a MySQL database. The connection configurations are as follows:

- Host: `localhost`
- Port: `3306`
- User: `root`
- Password: `your_password`
- Database: `your_database_name`

## Environment variables

Make sure the environment variables are correctly set in the `.env` file or the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password
```

## Stopping the containers

To stop and remove the Docker containers for the database and PhpMyAdmin, run the following command:

```bash
docker compose down
```

## Additional instructions

If any database migrations or specific actions are required after starting the containers, you can add them here.
