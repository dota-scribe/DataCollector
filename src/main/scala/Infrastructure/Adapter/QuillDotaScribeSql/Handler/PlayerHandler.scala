package Infrastructure.Adapter.QuillDotaScribeSql.Handler

import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._
import io.getquill.context.jdbc.{BooleanObjectEncoding, JdbcContext}
import io.getquill.context.sql.idiom.{ConcatSupport, SqlIdiom}
import io.getquill.{H2Dialect, PostgresEscape, SQLServerDialect}

class PlayerHandler(context: JdbcContext[_ >: SQLServerDialect with H2Dialect <: SqlIdiom with ConcatSupport, PostgresEscape.type] with BooleanObjectEncoding) extends DaoSchema {
    override val Context = context
    import Context._

    def ProcessPlayer(matchId: Long, players: List[Player]): Unit = {
        players.foreach(player => {
            val playerId = InsertPlayer(matchId, player)
            InsertAbilityTarget(playerId, player.ability_targets)
            InsertAbilityUse(playerId, player.ability_uses)
            InsertPlayerKillLog(playerId, player.kills_log)
            InsertPlayerLanePosition(playerId, player.lane_pos)
            InsertPlayerDamage(playerId, player.damage)
            InsertPlayerDamageInflictor(playerId, player.damage_inflictor)
            InsertPlayerDamageInflictorRecieved(playerId, player.damage_inflictor_received)
            InsertPlayerDamageTaken(playerId, player.damage_taken)
            InsertPlayerDamageTarget(playerId, player.damage_targets)
            InsertPlayerGoldTotal(playerId, player.gold_t)
        })
    }

    def InsertPlayer(matchId: Long, player: Player): Long = {
        val teamFightInsert = quote(PlayerSchema.insert(lift(PlayerDao(
            0,
            player.match_id,
            player.player_slot,
            player.account_id,
            player.assists,
            player.backpack_0,
            player.backpack_1,
            player.backpack_2,
            player.camps_stacked,
            player.creeps_stacked,
            player.deaths,
            player.denies,
            player.firstblood_claimed,
            player.gold,
            player.gold_per_min,
            player.gold_spent,
            player.hero_damage,
            player.hero_healing,
            player.hero_id,
            player.item_0,
            player.item_1,
            player.item_2,
            player.item_3,
            player.item_4,
            player.item_5,
            player.kills,
            player.last_hits,
            player.leaver_status,
            player.level,
            player.obs_placed,
            player.party_id,
            player.party_size,
            player.performance_others,
            player.pings,
            player.pred_vict,
            player.randomed,
            player.repicked,
            player.roshans_killed,
            player.rune_pickups,
            player.sen_placed,
            player.stuns,
            player.teamfight_participation,
            player.tower_damage,
            player.towers_killed,
            player.xp_per_min,
            player.personaname,
            player.name,
            player.last_login,
            player.radiant_win,
            player.start_time,
            player.duration,
            player.cluster,
            player.lobby_type,
            player.game_mode,
            player.is_contributor,
            player.patch,
            player.region,
            player.isRadiant,
            player.win,
            player.lose,
            player.total_gold,
            player.total_xp,
            player.kda,
            player.abandons,
            player.neutral_kills,
            player.tower_kills,
            player.courier_kills,
            player.lane_kills,
            player.hero_kills,
            player.observer_kills,
            player.sentry_kills,
            player.roshan_kills,
            player.necronomicon_kills,
            player.ancient_kills,
            player.buyback_count,
            player.observer_uses,
            player.sentry_uses,
            player.lane_efficiency,
            player.lane_efficiency_pct,
            player.lane,
            player.lane_role,
            player.is_roaming,
            player.purchase_tpscroll,
            player.actions_per_min,
            player.life_state_dead,
            player.rank_tier
        ))).returning(_.player_id))

        Context.run(teamFightInsert)
    }

    def InsertAbilityTarget(playerId: Long, abilityTargets: Map[String, Map[String, Int]]): Unit ={
        abilityTargets.map(abilityUse => {
            val ability = abilityUse._1
            val targets = abilityUse._2

            targets.map(target => {
                val abilityTargetInsert = quote(PlayerAbilityTargetSchema.insert(lift(PlayerAbilityTargetDao(
                    playerId,
                    ability,
                    target._1,
                    target._2
                ))))

                Context.run(abilityTargetInsert)
            })
        })
    }

    def InsertAbilityUse(playerId: Long, abilityUse: Map[String, Index]): Unit = {
        abilityUse.map(abilityUse => {
            val abilityUseInsert = quote(PlayerAbilityUseSchema.insert(lift(PlayerAbilityUseDao(
                playerId,
                abilityUse._1,
                abilityUse._2
            ))))

            Context.run(abilityUseInsert)
        })
    }

    def InsertPlayerKillLog(playerId: Long, killLog: List[PlayerKillLog]): Unit = {
        killLog.map(kill => {
            val killLogInsert = quote(PlayerKillLogSchema.insert(lift(PlayerKillLogDao(
                playerId,
                kill.time,
                kill.key
            ))))

            Context.run(killLogInsert)
        })
    }

    def InsertPlayerLanePosition(playerId: Long, lanePositions: Map[Index, Map[Index, Index]]): Unit = {
        lanePositions.map(xPosition => {
            val x = xPosition._1
            val yPositions = xPosition._2

            yPositions.map(yPos => {
                val abilityTargetInsert = quote(PlayerLanePositionSchema.insert(lift(PlayerLanePositionDao(
                    playerId,
                    x,
                    yPos._1,
                    yPos._2
                ))))

                Context.run(abilityTargetInsert)
            })
        })
    }

    def InsertPlayerDamage(playerId: Long, damages: Map[String, Int]): Unit ={
        damages.map(damage => {
            val damageInsert = quote(PlayerDamageSchema.insert(lift(PlayerDamageDao(
                playerId,
                damage._1,
                damage._2
            ))))

            Context.run(damageInsert)
        })
    }

    def InsertPlayerDamageInflictor(playerId: Long, damageInflictors: Map[String, Int]): Unit = {
        damageInflictors.map(damageInflictor => {
            val damageInsert = quote(PlayerDamageInflictorSchema.insert(lift(PlayerDamageInflictorDao(
                playerId,
                damageInflictor._1,
                damageInflictor._2
            ))))

            Context.run(damageInsert)
        })
    }

    def InsertPlayerDamageInflictorRecieved(playerId: Long, damageInflictorRecieved: Map[String, Int]): Unit = {
        damageInflictorRecieved.map(recieved => {
            val damageInsert = quote(PlayerDamageInflictorRecievedSchema.insert(lift(PlayerDamageInflictorRecievedDao(
                playerId,
                recieved._1,
                recieved._2
            ))))

            Context.run(damageInsert)
        })
    }

    def InsertPlayerDamageTaken(playerId: Long, damageTaken: Map[String, Int]): Unit = {
        damageTaken.map(damage => {
            val damageInsert = quote(PlayerDamageTakenSchema.insert(lift(PlayerDamageTakenDao(
                playerId,
                damage._1,
                damage._2
            ))))

            Context.run(damageInsert)
        })
    }

    def InsertPlayerDamageTarget(playerId: Long, damageTargets: Map[String, Map[String, Int]]): Unit = {
        damageTargets.map(damageTarget => {
            val source = damageTarget._1
            val targets = damageTarget._2

            targets.map(target => {
                val damageTargetInsert = quote(PlayerDamageTargetSchema.insert(lift(PlayerDamageTargetDao(
                    playerId,
                    source,
                    target._1,
                    target._2
                ))))

                Context.run(damageTargetInsert)
            })
        })
    }

    def InsertPlayerGoldTotal(playerId: Long, goldTotals: List[Int]): Unit = {
        val goldInsert = quote {
            liftQuery(goldTotals).foreach(value => PlayerGoldTotalSchema.insert(PlayerGoldTotalDao(
                lift(playerId),
                value
            )))
        }

        Context.run(goldInsert)
    }
}
