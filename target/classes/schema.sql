DROP TABLE IF EXISTS  npc_stats;
DROP TABLE IF EXISTS  stats;
DROP TABLE IF EXISTS  npc;
DROP TABLE IF EXISTS  game;

CREATE TABLE game (
	game_id int NOT NULL AUTO_INCREMENT,
	game_name varchar(256) NOT NULL,
	game_system varchar(128) NOT NULL,
	game_year varchar(20),
	game_version varchar(30),
	PRIMARY KEY (game_id)
);

CREATE TABLE npc (
	npc_id int NOT NULL AUTO_INCREMENT,
	npc_name varchar(60) NOT NULL,
	PRIMARY KEY (npc_id),
	FOREIGN KEY (game_id) REFERENCES location (game_id) ON DELETE CASCADE
	);
	
	
	CREATE TABLE stats (
		stat_id int NOT NULL AUTO_INCREMENT, 
		speed varchar(256) NOT NULL,
		tier varchar(20),
		 wins int,
		name varchar (128),
		PRIMARY KEY(stat_id)
		);
		
		CREATE TABLE npc_stats (
			npc_id int NOT NULL,
			stat_id int NOT NULL,
			FOREIGN KEY (npc_id) REFERENCES npc (npc_id) ON DELETE CASCADE,
			FOREIGN KEY (stat_id) REFERENCES stats (breed_id) ON DELETE CASCADE
			);
	
	
