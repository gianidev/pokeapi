INSERT INTO stats (stats_id, health_points, attack, defense, speed_attack, speed_defense, speed, total) VALUES (1, 49, 49, 45, 65, 65, 45, 318);
INSERT INTO stats (stats_id, health_points, attack, defense, speed_attack, speed_defense, speed, total) VALUES (2, 60, 62, 63, 80, 80, 60, 405);
INSERT INTO stats (stats_id, health_points, attack, defense, speed_attack, speed_defense, speed, total) VALUES (3, 80, 82, 83, 100, 100, 80, 525);

INSERT INTO regions (region_id, name) VALUES (1, 'Kanto');
INSERT INTO regions (region_id, name) VALUES (2, 'Johto');

INSERT INTO generations (generation_id, name, region_id) VALUES (1, 'Generation I', 1);
INSERT INTO generations (generation_id, name, region_id) VALUES (2, 'Generation II', 2);

INSERT INTO artworks (artwork_id, url) VALUES (1, 'https://img.pokemondb.net/artwork/bulbasaur.jpg');
INSERT INTO artworks (artwork_id, url) VALUES (2, 'https://img.pokemondb.net/artwork/ivysaur.jpg');
INSERT INTO artworks (artwork_id, url) VALUES (3, 'https://img.pokemondb.net/artwork/venusaur.jpg');

INSERT INTO types (type_id, name) VALUES (1, 'Bug');
INSERT INTO types (type_id, name) VALUES (2, 'Dragon');
INSERT INTO types (type_id, name) VALUES (3, 'Electric');
INSERT INTO types (type_id, name) VALUES (4, 'Fighting');
INSERT INTO types (type_id, name) VALUES (5, 'Fire');
INSERT INTO types (type_id, name) VALUES (6, 'Flying');
INSERT INTO types (type_id, name) VALUES (7, 'Ghost');
INSERT INTO types (type_id, name) VALUES (8, 'Grass');
INSERT INTO types (type_id, name) VALUES (9, 'Ground');
INSERT INTO types (type_id, name) VALUES (10, 'Ice');
INSERT INTO types (type_id, name) VALUES (11, 'Normal');
INSERT INTO types (type_id, name) VALUES (12, 'Poison');
INSERT INTO types (type_id, name) VALUES (13, 'Psychic');
INSERT INTO types (type_id, name) VALUES (14, 'Rock');
INSERT INTO types (type_id, name) VALUES (15, 'Water');

INSERT INTO pokemons (pokemon_id, name, height, weight, artwork_id, generation_id, region_id, stats_id) VALUES (1,'Bulbasaur', 0.7, 6.9, 1, 1, 1 ,1);
INSERT INTO pokemons (pokemon_id, name, height, weight, artwork_id, generation_id, region_id, stats_id) VALUES (2,'Ivysaur', 1.0, 13.0, 2, 1, 1 ,2);
INSERT INTO pokemons (pokemon_id, name, height, weight, artwork_id, generation_id, region_id, stats_id) VALUES (3,'Venusaur', 2.0, 100.0, 3, 1, 1 ,3);

INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (1, 8);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (1, 13);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (2,8);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (2,13);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (3,8);
INSERT INTO pokemon_type (pokemon_id, type_id) VALUES (3,13);