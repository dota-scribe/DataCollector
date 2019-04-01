-- ======================
-- == DB Creation
-- ======================

USE [Master]
IF NOT EXISTS (SELECT name FROM master.dbo.sysdatabases  WHERE name = N'DotaScribe')
	CREATE DATABASE DotaScribe
GO

-- ======================
-- == User Creation
-- ======================
IF NOT EXISTS (SELECT name FROM sys.server_principals WHERE NAME = 'DataCollector')
    CREATE LOGIN [DataCollector] WITH PASSWORD = 'DataCollector', DEFAULT_DATABASE=[DotaScribe], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF

USE [DotaScribe]
	
IF NOT EXISTS (SELECT name FROM sys.database_principals WHERE name = 'DataCollector')
	CREATE USER [DataCollector] FOR LOGIN [DataCollector] WITH DEFAULT_SCHEMA=[dbo]
	ALTER ROLE [db_datareader] ADD MEMBER [DataCollector]
	ALTER ROLE [db_datawriter] ADD MEMBER [DataCollector]
GO

-- ======================
-- == Table Creation
-- ======================

USE DotaScribe

-- Drop tables - Order by foreign keys

DROP TABLE IF EXISTS ProPlayer
DROP TABLE IF EXISTS Chat
DROP TABLE IF EXISTS ProMatch
DROP TABLE IF EXISTS DraftTiming
DROP TABLE IF EXISTS PickBan
DROP TABLE IF EXISTS RadiantGoldAdvantage
DROP TABLE IF EXISTS RadiantXpAdvantage
DROP TABLE IF EXISTS RadiantTeam
DROP TABLE IF EXISTS DireTeam
DROP TABLE IF EXISTS League
DROP TABLE IF EXISTS Match

CREATE TABLE ProPlayer (
    account_id INT NOT NULL,
	steamid VARCHAR(255) NOT NULL,
	avatar VARCHAR(255) NOT NULL,
	avatarmedium VARCHAR(255) NOT NULL,
	avatarfull VARCHAR(255) NOT NULL,
	profileurl VARCHAR(255) NOT NULL,
	personaname VARCHAR(255) NOT NULL,
	last_login VARCHAR(255),
	full_history_time VARCHAR(255),
	cheese INT NOT NULL,
	fh_unavailable BIT,
	loccountrycode VARCHAR(255),
	last_match_time VARCHAR(255) NOT NULL,
	plus BIT,
	name VARCHAR(255) NOT NULL,
	country_code VARCHAR(255) NOT NULL,
	fantasy_role INT NOT NULL,
	team_id INT NOT NULL,
	team_name VARCHAR(255),
	team_tag VARCHAR(255),
	is_locked BIT NOT NULL,
	is_pro BIT NOT NULL,
	locked_until VARCHAR(255),

	PRIMARY KEY (account_id)
)

CREATE TABLE ProMatch (
	match_id BIGINT NOT NULL,
	duration BIGINT NOT NULL,
	start_time BIGINT NOT NULL,
	radiant_team_id BIGINT,
	radiant_name VARCHAR(255),
	dire_team_id BIGINT,
	dire_name VARCHAR(255),
	leagueid BIGINT NOT NULL,
	league_name VARCHAR(255) NOT NULL,
	series_id BIGINT NOT NULL,
	series_type BIGINT NOT NULL,
	radiant_score BIGINT NOT NULL,
	dire_score BIGINT NOT NULL,
	radiant_win BIT NOT NULL,

	PRIMARY KEY (match_id)
)

CREATE TABLE Match (
	match_id BIGINT NOT NULL,
	barracks_status_dire INT NOT NULL,
	barracks_status_radiant INT NOT NULL,
	--chat List[Chat] NOT NULL,
	cluster INT NOT NULL,
	--cosmetics Map[String, INT] NOT NULL,
	dire_score INT NOT NULL,
	dire_team_id INT NOT NULL,
	--draft_timings List[DraftTiming] NOT NULL,
	duration INT NOT NULL,
	engine INT NOT NULL,
	first_blood_time INT NOT NULL,
	game_mode INT NOT NULL,
	human_players INT NOT NULL,
	leagueid INT NOT NULL,
	lobby_type INT NOT NULL,
	match_seq_num BIGINT NOT NULL,
	negative_votes INT NOT NULL,
	--//    objectives List[Objective] NOT NULL,
	--picks_bans List[PickBans] NOT NULL,
	positive_votes INT NOT NULL,
	--radiant_gold_adv List[INT] NOT NULL,
	radiant_score INT NOT NULL,
	radiant_team_id INT NOT NULL,
	--radiant_win Boolean NOT NULL,
	--radiant_xp_adv List[INT] NOT NULL,
	skill INT,
	start_time INT NOT NULL,
	--teamfights List[TeamFights] NOT NULL,
	tower_status_dire INT NOT NULL,
	tower_status_radiant INT NOT NULL,
	version INT NOT NULL,
	replay_salt INT NOT NULL,
	series_id INT NOT NULL,
	series_type INT NOT NULL,
	--league League NOT NULL,
	--radiant_team Team NOT NULL,
	--dire_team Team NOT NULL,
	--players List[Player] NOT NULL,
	patch INT NOT NULL,
	region INT NOT NULL,
	--//    all_word_counts Map[String, INT] NOT NULL,
	--//    my_word_counts Map[String, INT] NOT NULL,
	comeback INT NOT NULL,
	stomp INT NOT NULL,
	replay_url VARCHAR(255) NOT NULL,

	PRIMARY KEY (match_id),
)

CREATE TABLE Chat (
	match_id BIGINT NOT NULL,
	time FLOAT NOT NULL,
	unit VARCHAR(255),
	[key] VARCHAR(255) NOT NULL,
	slot Int NOT NULL,
	player_slot Int NOT NULL,

	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE DraftTiming (
	match_id BIGINT NOT NULL,
	[order] INT NOT NULL,
	pick BIT NOT NULL,
	active_team INT NOT NULL,
	hero_id INT NOT NULL,
	player_slot INT,
	extra_time INT NOT NULL,
	total_time_taken INT NOT NULL

	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE PickBan (
	match_id BIGINT NOT NULL,
	is_pick BIT NOT NULL,
	hero_id INT NOT NULL,
	team INT NOT NULL,
	[order] INT NOT NULL,
	ord INT NOT NULL,

	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE RadiantGoldAdvantage (
	match_id BIGINT NOT NULL,
	gold_advantage INT NOT NULL,
	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE RadiantXpAdvantage (
	match_id BIGINT NOT NULL,
	gold_advantage INT NOT NULL,

	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE RadiantTeam (
	match_id BIGINT NOT NULL,
	team_id INT NOT NULL,
	name VARCHAR(255) NOT NULL,
	tag VARCHAR(255) NOT NULL,
	logo_url VARCHAR(255) NOT NULL
	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE DireTeam (
	match_id BIGINT NOT NULL,
	team_id INT NOT NULL,
	name VARCHAR(255) NOT NULL,
	tag VARCHAR(255) NOT NULL,
	logo_url VARCHAR(255) NOT NULL
	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE League (
	match_id BIGINT NOT NULL,
	leagueid INT NOT NULL,
	ticket VARCHAR(255),
	banner VARCHAR(255),
	tier VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,

	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)