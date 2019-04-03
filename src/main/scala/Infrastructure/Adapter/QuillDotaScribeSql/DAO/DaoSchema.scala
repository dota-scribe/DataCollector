package Infrastructure.Adapter.QuillDotaScribeSql.DAO

import Core.Application.Port.OpenDota.Model.LeagueDao

trait DaoSchema {
    val Context: PostgresContext
    import Context._

    val MatchSchema = quote {
        querySchema[MatchDao]("Match")
    }

    val LeagueSchema = quote {
        querySchema[LeagueDao]("League")
    }
}
