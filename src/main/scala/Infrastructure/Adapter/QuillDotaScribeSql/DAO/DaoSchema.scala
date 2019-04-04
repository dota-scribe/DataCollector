package Infrastructure.Adapter.QuillDotaScribeSql.DAO

import Core.Application.Port.OpenDota.Model._

trait DaoSchema {
    val Context: PostgresContext

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
        querySchema[TeamFightPlayerAbilityUseDoa]("TeamFightPlayerAbilityUse")
    }
}
