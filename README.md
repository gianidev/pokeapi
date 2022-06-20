# PokeAPI

This API has been created to register and fetch Pokemon data, like a Pokedéx. <img src="https://img.pokemondb.net/sprites/home/normal/pikachu-original-cap.png" align="right" />
## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Gradle 7.4.1](https://gradle.org/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.gcruz.pokeapi.PokeApiApplication` class from your IDE. Remember to configure the posgresql db on your machine.


Alternatively you can use the **Docker Compose** commands to start both application and db with the next command:

```shell
docker-compose up
```
You can use other `docker-compose` commands as you needed.

### Application properties (w/docker)
- Server port: `8081`
- DB configuration:
```shell
Host: localhost 
Port: 6543
Database: pokedb
Username: postgres 
Password: postgres
```

