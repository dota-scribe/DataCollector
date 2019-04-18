package Infrastructure.Adapter.QuillDotaScribeSql.Handler

import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._
import io.getquill.context.jdbc.{BooleanObjectEncoding, JdbcContext}
import io.getquill.context.sql.idiom.{ConcatSupport, SqlIdiom}
import io.getquill.{H2Dialect, PostgresEscape, SQLServerDialect, SqlServerJdbcContext}

class MatchHandler(context: JdbcContext[_ >: SQLServerDialect with H2Dialect <: SqlIdiom with ConcatSupport, PostgresEscape.type] with BooleanObjectEncoding) extends DaoSchema {
    override val Context = context
    import Context._

    def ProcessMatch(matchData: Match): Long = {
        val matchId = InsertMatch(matchData)
        InsertChat(matchId, matchData.chat)
        InsertObjective(matchId, matchData.objectives)
        InsertDraftTiming(matchData)
        InsertPickBan(matchData)
        InsertRadiantGoldAdvantage(matchData)
        InsertRadiantXpAdvantage(matchData)
        InsertLeague(matchId, matchData.league)
        InsertTeam(matchId, matchData.radiant_team, RadiantTeamSchema)
        InsertTeam(matchId, matchData.dire_team, DireTeamSchema)

        matchId
    }

    private def InsertTeam(matchId: Long, team: Option[Team], schema: Context.Quoted[Context.EntityQuery[TeamDao]]): Unit = {
        team.map(team=> {
            val teamInsert = quote(schema.insert(lift(TeamDao(
                matchId,
                team.team_id,
                team.name,
                team.tag,
                team.logo_url
            ))))

            Context.run(teamInsert)
        })
    }

    private def InsertLeague(matchId: Long, league: League): Unit = {
        val leagueInsert = quote(LeagueSchema.insert(lift(LeagueDao(
            matchId,
            league.leagueid,
            league.ticket,
            league.banner,
            league.tier,
            league.name
        ))))

        Context.run(leagueInsert)
    }

    private def InsertRadiantXpAdvantage(matchData: Match): Unit = {
        val xpAdvantageInsert = quote {
            liftQuery(matchData.radiant_xp_adv).foreach(adv => RadiantXpAdvantageSchema.insert(RadiantXpAdvantageDao(
                lift(matchData.match_id),
                adv
            )))
        }

        Context.run(xpAdvantageInsert)
    }

    private def InsertRadiantGoldAdvantage(matchData: Match): Unit = {
        val goldAdvantageInsert = quote {
            liftQuery(matchData.radiant_gold_adv).foreach(adv => RadiantGoldAdvantageSchema.insert(RadiantGoldAdvantageDao(
                lift(matchData.match_id),
                adv
            )))
        }

        Context.run(goldAdvantageInsert)
    }

    private def InsertObjective(matchId: Long, objectives: List[Objective]): Unit = {
        objectives.foreach{objective =>{
            val key: Option[String] = objective.key match {
                case Some(key) => key.fold(l => Option(l), r => Option(r.toString))
                case None => None
            }

            val objectiveInsert = quote(ObjectiveSchema.insert(lift(ObjectiveDao(
                matchId,
                objective.time,
                objective.`type`,
                objective.unit,
                objective.slot,
                key,
                objective.player_slot,
                objective.team
            ))))

            Context.run(objectiveInsert)
        }}
    }

    private def InsertPickBan(matchData: Match): Unit = {
        val pickBanInsert = quote {
            liftQuery(matchData.picks_bans).foreach(pick => PickBanSchema.insert(PickBanDao(
                lift(matchData.match_id),
                pick.is_pick,
                pick.hero_id,
                pick.team,
                pick.order,
                pick.ord
            )))
        }

        Context.run(pickBanInsert)
    }

    private def InsertDraftTiming(matchData: Match): Unit = {
        val draftTimingInsert = quote {
            liftQuery(matchData.draft_timings).foreach(timing => DraftTimingSchema.insert(DraftTimingDao(
                lift(matchData.match_id),
                timing.order,
                timing.pick,
                timing.active_team,
                timing.hero_id,
                timing.player_slot,
                timing.extra_time,
                timing.total_time_taken
            )))
        }

        Context.run(draftTimingInsert)
    }

    private def InsertChat(matchId: Long, chatMessages: List[Chat]): Unit = {
        val chatInsert = quote {
            liftQuery(chatMessages).foreach(chat => ChatSchema.insert(ChatDao(
                lift(matchId),
                chat.time,
                chat.unit,
                chat.key,
                chat.slot,
                chat.player_slot
            )))
        }

        Context.run(chatInsert)
    }

    private def InsertMatch(matchData: Match): Long = {
        val matchInsert = quote(MatchSchema.insert(lift(MatchDao(
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
        ))))

        Context.run(matchInsert)

        return matchData.match_id
    }
}
