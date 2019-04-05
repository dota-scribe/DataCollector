package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._
import Infrastructure.Adapter.QuillDotaScribeSql.Handler.{MatchHandler, TeamFightHandler}

class QuillDotaScribeSql extends DotaScribeRepositoryPort with DaoSchema {
    override val Context: PostgresContext = new PostgresContext()

    import Context._

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

        new TeamFightHandler(Context).ProcessTeamFight(matchId, matchData.teamfights)
    }


    override def RebuildDbMappings(): Unit = ???

}
