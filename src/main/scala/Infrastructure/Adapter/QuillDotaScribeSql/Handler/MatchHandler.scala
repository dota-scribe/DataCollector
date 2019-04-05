package Infrastructure.Adapter.QuillDotaScribeSql.Handler

import Core.Application.Port.OpenDota.Model.{ChatDao, DraftTimingDao, Match, PickBanDao}
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._

class MatchHandler(context: PostgresContext) extends DaoSchema {
    override val Context: PostgresContext = context
    import Context._

    def ProcessMatch(matchData: Match): Long = {
        val teamFightHandler = new TeamFightHandler(Context)

        val matchId = InsertMatch(matchData)
        InsertChat(matchData)
        InsertDraftTiming(matchData)
        InsertPickBan(matchData)
        InsertRadiantGoldAdvantage(matchData)
        InsertRadiantXpAdvantage(matchData)
        teamFightHandler.ProcessTeamFight(matchId, matchData.teamfights)

        matchId
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

    private def InsertChat(matchData: Match): Unit = {
        val chatInsert = quote {
            liftQuery(matchData.chat).foreach(chat => ChatSchema.insert(ChatDao(
                lift(matchData.match_id),
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
