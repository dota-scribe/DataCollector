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
DROP TABLE IF EXISTS Objective
DROP TABLE IF EXISTS TeamFightPlayerAbilityUse
DROP TABLE IF EXISTS TeamFightPlayerAbilityTarget
DROP TABLE IF EXISTS TeamFightPlayerDeathPosition
DROP TABLE IF EXISTS TeamFightPlayerItemUse
DROP TABLE IF EXISTS TeamFightPlayerKilled
DROP TABLE IF EXISTS TeamFightPlayer
DROP TABLE IF EXISTS TeamFight
DROP TABLE IF EXISTS Player
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
	cluster INT NOT NULL,
	dire_score INT NOT NULL,
	dire_team_id INT NOT NULL,
	duration INT NOT NULL,
	engine INT NOT NULL,
	first_blood_time INT NOT NULL,
	game_mode INT NOT NULL,
	human_players INT NOT NULL,
	leagueid INT NOT NULL,
	lobby_type INT NOT NULL,
	match_seq_num BIGINT NOT NULL,
	negative_votes INT NOT NULL,
	positive_votes INT NOT NULL,
	radiant_score INT NOT NULL,
	radiant_team_id INT NOT NULL,
	skill INT,
	start_time INT NOT NULL,
	tower_status_dire INT NOT NULL,
	tower_status_radiant INT NOT NULL,
	version INT NOT NULL,
	replay_salt INT NOT NULL,
	series_id INT NOT NULL,
	series_type INT NOT NULL,
	patch INT NOT NULL,
	region INT NOT NULL,
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

CREATE TABLE Objective (
	match_id BIGINT NOT NULL,
	time INT NOT NULL,
	type VARCHAR(255) NOT NULL,
	Unit VARCHAR(255),
	slot INT,
	[key] VARCHAR(255),
	player_slot INT,
	team INT,

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
	"order" INT NOT NULL,
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
	xp_advantage INT NOT NULL,

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

CREATE TABLE TeamFight (
	teamfight_id BIGINT NOT NULL IDENTITY(1,1),
	match_id BIGINT NOT NULL,
	start INT NOT NULL,
	[end] INT NOT NULL,
	last_death INT NOT NULL,
	deaths INT NOT NULL,

	PRIMARY KEY (teamfight_id),
	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)

CREATE TABLE TeamFightPlayer (
	teamfight_player_id BIGINT NOT NULL IDENTITY(1,1),
	teamfight_id BIGINT NOT NULL,
	deaths INT NOT NULL,
	buybacks INT NOT NULL,
	damage INT NOT NULL,
	healing INT NOT NULL,
	gold_delta INT NOT NULL,
	xp_delta INT NOT NULL,
	xp_start INT NOT NULL,
	xp_end INT NOT NULL,

	PRIMARY KEY (teamfight_player_id),
	FOREIGN KEY (teamfight_id) REFERENCES TeamFight(teamfight_id)
)

CREATE TABLE TeamFightPlayerDeathPosition (
	teamfight_player_id BIGINT NOT NULL,
	x INT NOT NULL,
	y INT NOT NULL,
	z INT NOT NULL

	FOREIGN KEY (teamfight_player_id) REFERENCeS TeamFightPlayer(teamfight_player_id)
)

CREATE TABLE TeamFightPlayerAbilityUse (
	teamfight_player_id BIGINT NOT NULL,
	ability VARCHAR(255) NOT NULL,
	count INT NOT NULL,

	FOREIGN KEY (teamfight_player_id) REFERENCeS TeamFightPlayer(teamfight_player_id)
)

CREATE TABLE TeamFightPlayerAbilityTarget (
	teamfight_player_id BIGINT NOT NULL,
	ability VARCHAR(255) NOT NULL,
	target VARCHAR(255) NOT NULL, 
	count INT NOT NULL,

	FOREIGN KEY (teamfight_player_id) REFERENCeS TeamFightPlayer(teamfight_player_id)
)

CREATE TABLE TeamFightPlayerItemUse (
	teamfight_player_id BIGINT NOT NULL,
	item VARCHAR(255) NOT NULL,
	count INT NOT NULL,

	FOREIGN KEY (teamfight_player_id) REFERENCeS TeamFightPlayer(teamfight_player_id)
)

CREATE TABLE TeamFightPlayerKilled (
	teamfight_player_id BIGINT NOT NULL,
	killed VARCHAR(255) NOT NULL,
	count INT NOT NULL,

	FOREIGN KEY (teamfight_player_id) REFERENCeS TeamFightPlayer(teamfight_player_id)
)

CREATE TABLE Player (
	player_id BIGINT NOT NULL IDENTITY(1,1),
    match_id BIGINT NOT NULL,
    player_slot INT NOT NULL,
    account_id BIGINT NOT NULL,
    additional_units VARCHAR(255),
    assists INT NOT NULL,
    backpack_0 INT NOT NULL,
    backpack_1 INT NOT NULL,
    backpack_2 INT NOT NULL,
    camps_stacked INT NOT NULL,
    creeps_stacked INT NOT NULL,
    deaths INT NOT NULL,
    denies INT NOT NULL,
    firstblood_claimed INT NOT NULL,
    gold INT NOT NULL,
    gold_per_min INT NOT NULL,
    gold_spent INT NOT NULL,
    hero_damage INT NOT NULL,
    hero_healing INT NOT NULL,
    hero_id INT NOT NULL,
    item_0 INT NOT NULL,
    item_1 INT NOT NULL,
    item_2 INT NOT NULL,
    item_3 INT NOT NULL,
    item_4 INT NOT NULL,
    item_5 INT NOT NULL,
    kills INT NOT NULL,
    last_hits INT NOT NULL,
    leaver_status INT NOT NULL,
    level INT NOT NULL,
    obs_placed INT NOT NULL,
    party_id INT NOT NULL,
    party_size INT NOT NULL,
    performance_others VARCHAR(255),
    pings INT NOT NULL,
    pred_vict BIT NOT NULL,
    randomed BIT NOT NULL,
    repicked BIT,
    roshans_killed INT NOT NULL,
    rune_pickups INT NOT NULL,
    sen_placed INT NOT NULL,
    stuns DECIMAL(32, 12) NOT NULL,
    teamfight_participation DECIMAL(32, 12) NOT NULL,
    tower_damage INT NOT NULL,
    towers_killed INT NOT NULL,
    xp_per_min INT NOT NULL,
    personaname VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    last_login VARCHAR(255),
    radiant_win BIT NOT NULL,
    start_time INT NOT NULL,
    duration INT NOT NULL,
    cluster INT NOT NULL,
    lobby_type INT NOT NULL,
    game_mode INT NOT NULL,
    is_contributor BIT NOT NULL,
    patch INT NOT NULL,
    region INT NOT NULL,
    isRadiant BIT NOT NULL,
    win INT NOT NULL,
    lose INT NOT NULL,
    total_gold INT NOT NULL,
    total_xp INT NOT NULL,
    kda INT NOT NULL,
    abandons INT NOT NULL,
    neutral_kills INT NOT NULL,
    tower_kills INT NOT NULL,
    courier_kills INT NOT NULL,
    lane_kills INT NOT NULL,
    hero_kills INT NOT NULL,
    observer_kills INT NOT NULL,
    sentry_kills INT NOT NULL,
    roshan_kills INT NOT NULL,
    necronomicon_kills INT NOT NULL,
    ancient_kills INT NOT NULL,
    buyback_count INT NOT NULL,
    observer_uses INT NOT NULL,
    sentry_uses INT NOT NULL,
    lane_efficiency DECIMAL(32, 12) NOT NULL,
    lane_efficiency_pct INT NOT NULL,
    lane INT NOT NULL,
    lane_role INT NOT NULL,
    is_roaming BIT NOT NULL,
    purchase_tpscroll INT NOT NULL,
    actions_per_min INT NOT NULL,
    life_state_dead INT NOT NULL,
    rank_tier INT

	PRIMARY KEY (player_id),
	FOREIGN KEY (match_id) REFERENCES Match(match_id)
)