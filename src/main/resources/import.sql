INSERT INTO stats (health_points, attack, defense, speed_attack, speed_defense, speed, total) VALUES (49, 49, 45, 65, 65, 45, 318);
INSERT INTO stats (health_points, attack, defense, speed_attack, speed_defense, speed, total) VALUES (60, 62, 63, 80, 80, 60, 405);
INSERT INTO stats (health_points, attack, defense, speed_attack, speed_defense, speed, total) VALUES (80, 82, 83, 100, 100, 80, 525);

INSERT INTO regions (name) VALUES ('Kanto');
INSERT INTO regions (name) VALUES ('Johto');

INSERT INTO generations (name, region_id) VALUES ('Generation I', 1);
INSERT INTO generations (name, region_id) VALUES ('Generation II', 2);

INSERT INTO artworks (url) VALUES ('https://img.pokemondb.net/artwork/bulbasaur.jpg');
INSERT INTO artworks (url) VALUES ('https://img.pokemondb.net/artwork/ivysaur.jpg');
INSERT INTO artworks (url) VALUES ('https://img.pokemondb.net/artwork/venusaur.jpg');


INSERT INTO types (name) VALUES ('Bug');
INSERT INTO types (name) VALUES ('Dragon');
INSERT INTO types (name) VALUES ('Electric');
INSERT INTO types (name) VALUES ('Fighting');
INSERT INTO types (name) VALUES ('Fire');
INSERT INTO types (name) VALUES ('Flying');
INSERT INTO types (name) VALUES ('Ghost');
INSERT INTO types (name) VALUES ('Grass');
INSERT INTO types (name) VALUES ('Ground');
INSERT INTO types (name) VALUES ('Ice');
INSERT INTO types (name) VALUES ('Normal');
INSERT INTO types (name) VALUES ('Poison');
INSERT INTO types (name) VALUES ('Psychic');
INSERT INTO types (name) VALUES ('Rock');
INSERT INTO types (name) VALUES ('Water');

INSERT INTO pokemons (name, height, weight, artwork_id, generation_id, region_id, stats_id) VALUES ('Bulbasaur', 07, 69, 1, 1, 1 ,1);
INSERT INTO pokemons (name, height, weight, artwork_id, generation_id, region_id, stats_id) VALUES ('Ivysaur', 10, 130, 2, 1, 1 ,2);
INSERT INTO pokemons (name, height, weight, artwork_id, generation_id, region_id, stats_id) VALUES ('Venusaur', 20, 1000, 3, 1, 1 ,3);

INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (1, 8);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (1, 13);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (2,8);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (2,13);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (3,8);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (3,13);