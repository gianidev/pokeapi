# PokéAPI
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=fff&style=for-the-badge) ![Docker Badge](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff&style=for-the-badge)
<img src="https://img.pokemondb.net/sprites/home/normal/pikachu-original-cap.png" align="right" /> ![Gradle Badge](https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=fff&style=for-the-badge) 

This API has been created to create and fetch Pokémon data, like a Pokedéx. 

With this API you can perform crud operations on Pokémons, Generations, Regions, Stats, Artworks, Regions and Pokémon's types.

It has been created for practice purposes and still work in progress. :) 

## Requirements
For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Gradle 7.4.1](https://gradle.org/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.gcruz.pokeapi.PokeApiApplication` class from your IDE. Remember to configure the posgresql db on your machine.


Alternatively you can use `docker-compose` commands to start or stop this application and its database, i.e

```shell
docker-compose up
```

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

## API Collection
In the link below you will find the Postman collection with pre-made examples.

[PokeAPI Postman Collection](https://documenter.getpostman.com/view/17083920/UzBjt8H6)

## Credits
Pokémon artwork and sprites urls used on this Api has been obtained from [Pokémon Database](https://pokemondb.net/) page.
