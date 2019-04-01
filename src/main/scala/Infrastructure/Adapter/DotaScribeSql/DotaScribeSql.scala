package Infrastructure.Adapter.OpenDota.MsSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model.{Match, ProMatch, ProPlayer}
import Infrastructure.Adapter.DotaScribeSql.Table.Tables
import com.typesafe.config.ConfigFactory
import slick.jdbc.SQLServerProfile
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.{Await}
import scala.concurrent.duration.Duration
import Infrastructure.Adapter.DotaScribeSql.Table

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
        val matchTable = TableQuery[Tables.Match]
        val chatTable = TableQuery[Tables.Chat]
        val draftTimingTable = TableQuery[Tables.Drafttiming]
        val pickBanTable = TableQuery[Tables.Pickban]
        val radiantGoldAdvantageTable = TableQuery[Tables.Radiantgoldadvantage]
        val radiantXpAdvantageTable = TableQuery[Tables.Radiantxpadvantage]
        val radiantTeamTable = TableQuery[Tables.Radiantteam]
        val direTeamTable = TableQuery[Tables.Direteam]
        val leagueTable = TableQuery[Tables.League]

        //        import scala.concurrent._
        //        import ExecutionContext.Implicits.global
        //
        //        val tx2 = (matchTable returning matchTable.map(_.matchId) += matchTableRow).flatMap(id =>
        //            chatTable ++= matchData.chat.map(chat => Tables.ChatRow(id, chat.time, chat.unit, chat.key, chat.slot, chat.player_slot)))

        val seq = DBIO.seq(
            matchTable += Tables.MatchRow(
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
            ),

            chatTable ++= matchData.chat.map(chat => Tables.ChatRow(
                matchData.match_id,
                chat.time,
                chat.unit,
                chat.key,
                chat.slot,
                chat.player_slot)
            ),

            draftTimingTable ++= matchData.draft_timings.map(timing => Tables.DrafttimingRow(
                matchData.match_id,
                timing.order,
                timing.pick,
                timing.active_team,
                timing.hero_id,
                timing.player_slot,
                timing.extra_time,
                timing.total_time_taken
            )),

            pickBanTable ++= matchData.picks_bans.map(pick => Tables.PickbanRow(
                matchData.match_id,
                pick.is_pick,
                pick.hero_id,
                pick.team,
                pick.order,
                pick.ord
            )),

            radiantGoldAdvantageTable ++= matchData.radiant_gold_adv.map(adv => Tables.RadiantgoldadvantageRow(
                matchData.match_id,
                adv
            )),

            radiantXpAdvantageTable ++= matchData.radiant_xp_adv.map(adv => Tables.RadiantxpadvantageRow(
                matchData.match_id,
                adv
            )),

            radiantTeamTable += Tables.RadiantteamRow(
                matchData.match_id,
                matchData.radiant_team.team_id,
                matchData.radiant_team.name,
                matchData.radiant_team.tag,
                matchData.radiant_team.logo_url
            ),

            direTeamTable += Tables.DireteamRow(
                matchData.match_id,
                matchData.dire_team.team_id,
                matchData.dire_team.name,
                matchData.dire_team.tag,
                matchData.dire_team.logo_url
            ),

            leagueTable += Tables.LeagueRow(
                matchData.match_id,
                matchData.league.leagueid,
                matchData.league.ticket,
                matchData.league.banner,
                matchData.league.tier,
                matchData.league.name
            )
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

