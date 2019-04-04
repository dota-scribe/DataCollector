package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._

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
        val matchId = InsertMatch(matchData)
        InsertChat(matchData)
        InsertDraftTiming(matchData)
        InsertPickBan(matchData)
        InsertRadiantGoldAdvantage(matchData)
        InsertRadiantXpAdvantage(matchData)
        ProcessTeamFight(matchId, matchData.teamfights)
    }

    private def ProcessTeamFight(matchId: Long, teamfights: List[TeamFights]): Unit = {
        teamfights.foreach(fight => {
            val teamFightId = InsertTeamFight(matchId, fight)
            ProcessTeamFightPlayers(teamFightId, fight.players)
        })
    }

        private def ProcessTeamFightPlayers(teamFightId: Long, players: List[TeamFightPlayer]): Unit = {
            players.foreach(player => {
                val teamFightPlayerId = InsertTeamFightPlayer(teamFightId, player)
                InsertTeamFightDeathPosition(teamFightPlayerId, player.deaths_pos)
            })
        }

        private def InsertTeamFightDeathPosition(teamFightPlayerId: Long, deathPos: Map[Int, Map[Int, Int]]): Unit = {
            var hasDeath: Boolean = false
            var x : Int = 0
            var y : Int = 0
            var z : Int = 0

            deathPos.foreach{
                case(key, value) => {
                    hasDeath = true;
                    x = key
                    value.foreach{
                        case(key, value) => {
                            y = key
                            z = value
                        }
                    }
                };
            }

            if (hasDeath) {
                val teamFightPlayerDeathPositionInsert = quote(TeamFightPlayerDeathPositionSchema.insert(lift(TeamFightPlayerDeathPositionDao(
                    teamFightPlayerId, x, y, z
                ))))

                Context.run(teamFightPlayerDeathPositionInsert)
            }
        }

        private def InsertTeamFightPlayer(teamFightId: Long, player: TeamFightPlayer): Long = {
            val teamFightPlayerInsert = quote(TeamFightPlayerSchema.insert(lift(TeamFightPlayerDao(
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
            ))).returning(_.teamfight_player_id))

            Context.run(teamFightPlayerInsert)
        }

    private def InsertTeamFight(matchId: Long, teamFight: TeamFights): Long = {

        val teamFightInsert = quote(TeamFightSchema.insert(lift(TeamFightDao(
            0,
            matchId,
            teamFight.start,
            teamFight.end,
            teamFight.last_death,
            teamFight.deaths
        ))).returning(_.teamfight_id))

        Context.run(teamFightInsert)
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

    override def RebuildDbMappings(): Unit = ???

}
