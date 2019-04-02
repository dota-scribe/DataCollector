package Infrastructure.Adapter.OpenDota.MsSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model.{Match, ProMatch, ProPlayer}
import Infrastructure.Adapter.DotaScribeSql.Table.Tables
import com.typesafe.config.ConfigFactory
import slick.jdbc.SQLServerProfile
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration

import scala.concurrent._
import ExecutionContext.Implicits.global

class DotaScribeSql(db: SQLServerProfile.backend.Database) extends DotaScribeRepositoryPort {
    override def SaveProPlayers(proPlayers: List[ProPlayer]): Unit = {
        val proPlayerTable = TableQuery[Tables.Proplayer]

        val inserts = for (player <- proPlayers) yield {
            val proPlayerRow = Tables.ProplayerRow(
                player.account_id,
                player.steamid,
                player.avatar,
                player.avatarmedium,
                player.avatarfull,
                player.profileurl,
                player.personaname,
                player.last_login,
                player.full_history_time,
                player.cheese,
                player.fh_unavailable,
                player.loccountrycode,
                player.last_match_time,
                player.plus,
                player.name,
                player.country_code,
                player.fantasy_role,
                player.team_id,
                player.team_name,
                player.team_tag,
                player.is_locked,
                player.is_pro,
                player.locked_until
            )
            proPlayerTable += proPlayerRow
        }

        Await.result(
            db.run(DBIO.seq(inserts: _*))
            , Duration(10000, "millis")
        )
    }

    override def SaveProMatches(proMatches: List[ProMatch]): Unit = {
        val proMatchTable = TableQuery[Tables.Promatch]

        val inserts = for (proMatch <- proMatches) yield {
            val proMatchRow = Tables.PromatchRow(
                proMatch.match_id,
                proMatch.duration,
                proMatch.start_time,
                proMatch.radiant_team_id,
                proMatch.radiant_name,
                proMatch.dire_team_id,
                proMatch.dire_name,
                proMatch.leagueid,
                proMatch.league_name,
                proMatch.series_id,
                proMatch.series_type,
                proMatch.radiant_score,
                proMatch.dire_score,
                proMatch.radiant_win
            )
            proMatchTable += proMatchRow
        }

        Await.result(
            db.run(DBIO.seq(inserts: _*))
            , Duration(10000, "millis")
        )
    }

    override def SaveMatch(matchData: Match): Unit = {
        val matchTableInsert = TableQuery[Tables.Match] += Tables.MatchRow(
            matchData.match_id,
            matchData.barracks_status_dire,
            matchData.barracks_status_radiant,
            matchData.cluster,
            matchData.dire_score,
            matchData.dire_team_id,
            matchData.duration,
            matchData.engine,
            matchData.first_blood_time,
            matchData.game_mode,
            matchData.human_players,
            matchData.leagueid,
            matchData.lobby_type,
            matchData.match_seq_num,
            matchData.negative_votes,
            matchData.positive_votes,
            matchData.radiant_score,
            matchData.radiant_team_id,
            matchData.skill,
            matchData.start_time,
            matchData.tower_status_dire,
            matchData.tower_status_radiant,
            matchData.version,
            matchData.replay_salt,
            matchData.series_id,
            matchData.series_type,
            matchData.patch,
            matchData.region,
            matchData.comeback,
            matchData.stomp,
            matchData.replay_url
        )

        val chatTableInsert = TableQuery[Tables.Chat] ++= matchData.chat.map(chat => Tables.ChatRow(
            matchData.match_id,
            chat.time,
            chat.unit,
            chat.key,
            chat.slot,
            chat.player_slot)
        )

        val draftTimingInsert = TableQuery[Tables.Drafttiming] ++= matchData.draft_timings.map(timing => Tables.DrafttimingRow(
            matchData.match_id,
            timing.order,
            timing.pick,
            timing.active_team,
            timing.hero_id,
            timing.player_slot,
            timing.extra_time,
            timing.total_time_taken
        ))

        val pickBanInsert = TableQuery[Tables.Pickban] ++= matchData.picks_bans.map(pick => Tables.PickbanRow(
            matchData.match_id,
            pick.is_pick,
            pick.hero_id,
            pick.team,
            pick.order,
            pick.ord
        ))

        val radiantGoldAdvantageInsert = TableQuery[Tables.Radiantgoldadvantage] ++= matchData.radiant_gold_adv.map(adv => Tables.RadiantgoldadvantageRow(
            matchData.match_id,
            adv
        ))

        val radiantXpAdvantageInsert = TableQuery[Tables.Radiantxpadvantage] ++= matchData.radiant_xp_adv.map(adv => Tables.RadiantxpadvantageRow(
            matchData.match_id,
            adv
        ))


        val teamFightTable = TableQuery[Tables.Teamfight]
        val teamFightPlayerTable = TableQuery[Tables.Teamfightplayer]

        val teamFightInsert = (teamFightTable returning teamFightTable.map(_.teamfightId) ++= matchData.teamfights.map(fight => Tables.TeamfightRow(
            0,
            matchData.match_id,
            fight.start,
            fight.end,
            fight.last_death,
            fight.deaths
        ))).flatMap(teamFightIds => {
            var teamFightKey = -1
            teamFightPlayerTable returning teamFightPlayerTable.map(_.teamfightPlayerId) ++= teamFightIds.flatMap(teamFightId => {
                teamFightKey += 1
                matchData.teamfights(teamFightKey).players.map(player => Tables.TeamfightplayerRow(
                    0,
                    teamFightId,
                    player.deaths,
                    player.buybacks,
                    player.damage,
                    player.healing,
                    player.gold_delta,
                    player.xp_delta,
                    player.xp_start,
                    player.xp_end
                ))
            })
        })

        val radiantTeamInsert = TableQuery[Tables.Radiantteam] += Tables.RadiantteamRow(
            matchData.match_id,
            matchData.radiant_team.team_id,
            matchData.radiant_team.name,
            matchData.radiant_team.tag,
            matchData.radiant_team.logo_url
        )

        val direTeamInsert = TableQuery[Tables.Direteam] += Tables.DireteamRow(
            matchData.match_id,
            matchData.dire_team.team_id,
            matchData.dire_team.name,
            matchData.dire_team.tag,
            matchData.dire_team.logo_url
        )

        val leagueInsert = TableQuery[Tables.League] += Tables.LeagueRow(
            matchData.match_id,
            matchData.league.leagueid,
            matchData.league.ticket,
            matchData.league.banner,
            matchData.league.tier,
            matchData.league.name
        )

        val seq = DBIO.seq(
            matchTableInsert,
            chatTableInsert,
            draftTimingInsert,
            pickBanInsert,
            radiantGoldAdvantageInsert,
            radiantXpAdvantageInsert,
            teamFightInsert,
            radiantTeamInsert,
            direTeamInsert,
            leagueInsert
        )

        Await.result(db.run(seq), Duration(10000, "millis"))
    }

    def RebuildDbMappings(): Unit = {
        val conf = ConfigFactory.load()

        val profile = conf.getString("SqlServer.profile")
        val jdbcDriver = conf.getString("SqlServer.jdbcDriver")
        val url = conf.getString("SqlServer.url")
        val outputFolder = "src/main/scala"
        val pkg = "Infrastructure.Adapter.DotaScribeSql.Table"
        val user = conf.getString("SqlServer.user")
        val password = conf.getString("SqlServer.password")

        slick.codegen.SourceCodeGenerator.run(profile, jdbcDriver, url, outputFolder, pkg, Some(user), Some(password), false, true)
    }
}

