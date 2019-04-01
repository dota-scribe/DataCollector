IF NOT EXISTS 
   (
     SELECT name FROM master.dbo.sysdatabases 
     WHERE name = N'DotaScribe'
    )
CREATE DATABASE DotaScribe

GO

USE DotaScribe

DROP TABLE IF EXISTS ProPlayer

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
	locked_until VARCHAR(255)
)

DROP TABLE IF EXISTS ProMatch

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
	radiant_win BIT NOT NULL
)