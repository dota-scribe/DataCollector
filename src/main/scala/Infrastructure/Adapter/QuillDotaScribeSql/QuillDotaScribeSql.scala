package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._
import Infrastructure.Adapter.QuillDotaScribeSql.Handler.{MatchHandler, PlayerHandler, TeamFightHandler}
import io.getquill._
import io.getquill.context.jdbc.{BooleanObjectEncoding, JdbcContext}
import io.getquill.context.sql.idiom.{ConcatSupport, SqlIdiom}

class QuillDotaScribeSql(context: JdbcContext[_ >: SQLServerDialect with H2Dialect <: SqlIdiom with ConcatSupport, PostgresEscape.type] with BooleanObjectEncoding)
    extends DotaScribeRepositoryPort
        with DaoSchema {

    override val Context = context

    import Context._

    override def GetProMatches(): List[ProMatch] = {
        val proMatchDao = Context.run(ProMatchSchema)

        proMatchDao.map(matchData => ProMatch(
            matchData.match_id,
            matchData.duration,
            matchData.start_time,
            matchData.radiant_team_id,
            matchData.radiant_name,
            matchData.dire_team_id,
            matchData.dire_name,
            matchData.leagueid,
            matchData.league_name,
            matchData.series_id,
            matchData.series_type,
            matchData.radiant_score,
            matchData.dire_score,
            matchData.radiant_win,
        ))
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


    override def RebuildDbMappings(): Unit = ???

}
