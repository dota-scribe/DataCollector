package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._
import Infrastructure.Adapter.QuillDotaScribeSql.Handler.{MatchHandler, PlayerHandler, TeamFightHandler}
import io.getquill._
import io.getquill.context.jdbc.{BooleanObjectEncoding, JdbcContext}
import io.getquill.context.sql.idiom.{ConcatSupport, SqlIdiom}

import scala.collection.mutable

class QuillDotaScribeSql(context: JdbcContext[_ >: SQLServerDialect with H2Dialect <: SqlIdiom with ConcatSupport, PostgresEscape.type] with BooleanObjectEncoding)
    extends DotaScribeRepositoryPort
        with DaoSchema {

    override val Context = context

    import Context._

    override def GetProMatchesWithoutMatchData(): List[ProMatch] = {
        val select = quote {
            ProMatchSchema.leftJoin(MatchSchema).on((proGame, game) => proGame.match_id == game.match_id)
        }

        val joinData = Context.run(select)

        val response = mutable.MutableList[ProMatch]()


        joinData.foreach(row => {
            val test = row._2.isEmpty
            if (row._2.isEmpty) response += ProMatch(
                row._1.match_id,
                row._1.duration,
                row._1.start_time,
                row._1.radiant_team_id,
                row._1.radiant_name,
                row._1.dire_team_id,
                row._1.dire_name,
                row._1.leagueid,
                row._1.league_name,
                row._1.series_id,
                row._1.series_type,
                row._1.radiant_score,
                row._1.dire_score,
                row._1.radiant_win,
            )
        })

        response.toList
    }

    override def SaveProPlayers(proPlayers: List[ProPlayer]): Unit = {
        val proPlayerInsert = quote {
            liftQuery(proPlayers).foreach(player => ProPlayerSchema.insert(ProPlayerDao(
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
            )))
        }

        Context.run(proPlayerInsert)
    }

    override def SaveProMatches(proMatches: List[ProMatch]): Unit = {
        val proMatchesInsert = quote {
            liftQuery(proMatches).foreach(proMatch => ProMatchSchema.insert(ProMatchDao(
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
            )))
        }

        Context.run(proMatchesInsert)
    }

    override def SaveMatch(matchData: Match): Unit = {
        val matchId = new MatchHandler(Context).ProcessMatch(matchData)

        new TeamFightHandler(Context).ProcessTeamFights(matchId, matchData.teamfights)
        new PlayerHandler(Context).ProcessPlayer(matchId, matchData.players)
    }

    override def GetMinProMatchId(): Option[Long] = {
        val select = quote{
            ProMatchSchema.map(proMatch => proMatch.match_id)
        }

        Context.run(select.min)
    }

    override def RebuildDbMappings(): Unit = ???

}
