# define base docker image
FROM openjdk:11
LABEL maintainer="gcruz"
ADD build/libs/pokeapi-0.0.1-SNAPSHOT.jar pokeapi.jar
ENTRYPOINT ["java", "-jar","pokeapi.jar"]
