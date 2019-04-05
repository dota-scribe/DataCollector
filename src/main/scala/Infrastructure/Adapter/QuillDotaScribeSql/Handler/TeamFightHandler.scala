package Infrastructure.Adapter.QuillDotaScribeSql.Handler

import Core.Application.Port.OpenDota.Model.{TeamFightDao, TeamFightPlayer, TeamFightPlayerDao, TeamFights}
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._

class TeamFightHandler(context: SqlServerContext) extends DaoSchema {
    override val Context = context
    import Context._

    def ProcessTeamFights(matchId: Long, teamfights: List[TeamFights]): Unit = {
        teamfights.foreach(fight => {
            val teamFightId = InsertTeamFight(matchId, fight)
            ProcessTeamFightPlayers(teamFightId, fight.players)
        })
    }

    private def ProcessTeamFightPlayers(teamFightId: Long, players: List[TeamFightPlayer]): Unit = {
        players.foreach(player => {
            val teamFightPlayerId = InsertTeamFightPlayer(teamFightId, player)
            InsertTeamFightDeathPosition(teamFightPlayerId, player.deaths_pos)
            InsertTeamFightAbilityUse(teamFightPlayerId, player.ability_uses)
            InsertTeamFightAbilityTargets(teamFightPlayerId, player.ability_targets)
            InsertTeamFightItemUse(teamFightPlayerId, player.item_uses)
            InsertTeamFightKilled(teamFightPlayerId, player.killed)
        })
    }

    private def InsertTeamFightKilled(teamFightPlayerId: Long, kills: Map[String, Int]): Unit = {
        kills.foreach(kill => {
            val killInsert = quote(TeamFightPlayerKilledSchema.insert(lift(TeamFightPlayerKillesDao(
                teamFightPlayerId,
                kill._1,
                kill._2
            ))))

            Context.run(killInsert)
        })
    }

    private def InsertTeamFightItemUse(teamFightPlayerId: Long, itemUse: Map[String, Int]): Unit = {
        itemUse.foreach(item => {
            val itemUseInsert = quote(TeamFightPlayerItemUseSchema.insert(lift(TeamFightPlayerItemUseDoa(
                teamFightPlayerId,
                item._1,
                item._2
            ))))

            Context.run(itemUseInsert)
        })
    }

    private def InsertTeamFightAbilityTargets(teamFightPlayerId: Long, abilityTargets: Map[String, Map[String, Int]]): Unit = {
        abilityTargets.map(abilityUse => {
            val ability = abilityUse._1
            val targets = abilityUse._2

            targets.map(target => {
                val teamFightAbilityTargetInsert = quote(TeamFightPlayerAbilityTargetSchema.insert(lift(TeamFightPlayerAbilityTargetDoa(
                    teamFightPlayerId,
                    ability,
                    target._1,
                    target._2
                ))))

                Context.run(teamFightAbilityTargetInsert)
            })
        })
    }

    private def InsertTeamFightAbilityUse(teamFightPlayerId: Long, abilityUse: Map[String, Int]): Unit = {
        abilityUse.foreach(ability => {
            val abilityUseInsert = quote(TeamFightPlayerAbilityUseSchema.insert(lift(TeamFightPlayerAbilityUseDoa(
                teamFightPlayerId,
                ability._1,
                ability._2
            ))))

            Context.run(abilityUseInsert)
        })
    }

    private def InsertTeamFightDeathPosition(teamFightPlayerId: Long, deathPos: Map[Int, Map[Int, Int]]): Unit = {
        var hasDeath: Boolean = false
        var x: Int = 0
        var y: Int = 0
        var z: Int = 0

        deathPos.foreach {
            case (key, value) => {
                hasDeath = true;
                x = key
                value.foreach {
                    case (key, value) => {
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
}
