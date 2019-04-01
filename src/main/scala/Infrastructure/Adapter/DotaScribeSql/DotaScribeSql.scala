package Infrastructure.Adapter.OpenDota.MsSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model.{ProMatch, ProPlayer}
import Infrastructure.Adapter.DotaScribeSql.Table.Tables
import com.typesafe.config.ConfigFactory
import slick.jdbc.SQLServerProfile
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

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

