package Infrastructure.Adapter.QuillDotaScribeSql.DAO

import Core.Application.Port.OpenDota.Model._
import io.getquill.context.jdbc.{BooleanObjectEncoding, JdbcContext}
import io.getquill.context.sql.idiom.{ConcatSupport, SqlIdiom}
import io.getquill._

trait DaoSchema {
    val Context: JdbcContext[_ >: SQLServerDialect with H2Dialect <: SqlIdiom with ConcatSupport, PostgresEscape.type] with BooleanObjectEncoding

    import Context._

    val MatchSchema = quote {
        querySchema[MatchDao]("Match")
    }

    val LeagueSchema = quote {
        querySchema[LeagueDao]("League")
    }

    val RadiantGoldAdvantageSchema = quote {
        querySchema[RadiantGoldAdvantageDao]("RadiantGoldAdvantage")
    }

    val RadiantXpAdvantageSchema = quote {
        querySchema[RadiantXpAdvantageDao]("RadiantXpAdvantage")
    }

    val ProMatchSchema = quote {
        querySchema[ProMatchDao]("ProMatch")
    }

    val ProPlayerSchema = quote {
        querySchema[ProPlayerDao]("ProPlayer")
    }

    val ChatSchema = quote {
        querySchema[ChatDao]("Chat")
    }

    val ObjectiveSchema = quote {
        querySchema[ObjectiveDao] ("Objective")
    }

    val RadiantTeamSchema = quote {
        querySchema[TeamDao]("RadiantTeam")
    }

    val DireTeamSchema = quote {
        querySchema[TeamDao]("DireTeam")
    }

    val DraftTimingSchema = quote {
        querySchema[DraftTimingDao]("DraftTiming")
    }

    val PickBanSchema = quote {
        querySchema[PickBanDao] ("PickBan")
    }

    val TeamFightSchema = quote {
        querySchema[TeamFightDao] ("TeamFight")
    }

    val TeamFightPlayerSchema = quote {
        querySchema[TeamFightPlayerDao]("TeamFightPlayer")
    }

    val TeamFightPlayerDeathPositionSchema = quote {
        querySchema[TeamFightPlayerDeathPositionDao]("TeamFightPlayerDeathPosition")
    }

    val TeamFightPlayerAbilityUseSchema = quote {
        querySchema[TeamFightPlayerAbilityUseDao]("TeamFightPlayerAbilityUse")
    }

    val TeamFightPlayerAbilityTargetSchema = quote {
        querySchema[TeamFightPlayerAbilityTargetDao] ("TeamFightPlayerAbilityTarget")
    }

    val TeamFightPlayerItemUseSchema = quote {
        querySchema[TeamFightPlayerItemUseDao]("TeamFightPlayerItemUse")
    }

    val TeamFightPlayerKilledSchema = quote {
        querySchema[TeamFightPlayerKillesDao]("TeamFightPlayerKilled")
    }

    val PlayerSchema = quote {
        querySchema[PlayerDao]("Player")
    }

    val PlayerAbilityTargetSchema = quote {
        querySchema[PlayerAbilityTargetDao]("PlayerAbilityTarget")
    }

    val PlayerAbilityUseSchema = quote {
        querySchema[PlayerAbilityUseDao]("PlayerAbilityUse")
    }

    val PlayerKillLogSchema = quote {
        querySchema[PlayerKillLogDao]("PlayerKillLog")
    }

    val PlayerLanePositionSchema = quote {
        querySchema[PlayerLanePositionDao]("PlayerLanePosition")
    }

    val PlayerDamageSchema = quote {
        querySchema[PlayerDamageDao]("PlayerDamage")
    }

    val PlayerDamageInflictorSchema = quote {
        querySchema[PlayerDamageInflictorDao]("PlayerDamageInflictor")
    }

    val PlayerDamageInflictorRecievedSchema = quote {
        querySchema[PlayerDamageInflictorRecievedDao]("PlayerDamageInflictorRecieved")
    }

    val PlayerDamageTakenSchema = quote {
        querySchema[PlayerDamageTakenDao]("PlayerDamageTaken")
    }

    val PlayerDamageTargetSchema = quote {
        querySchema[PlayerDamageTargetDao]("PlayerDamageTarget")
    }

    val PlayerGoldTotalSchema = quote {
        querySchema[PlayerGoldTotalDao]("PlayerGoldTotal")
    }
}
