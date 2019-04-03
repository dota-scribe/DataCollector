package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO.MatchDao
import io.getquill.{NamingStrategy, PostgresEscape, SqlServerJdbcContext, UpperCase}




class QuillDotaScribeSql extends DotaScribeRepositoryPort{

    val ctx = new SqlServerJdbcContext(PostgresEscape, "ctx")
    import ctx._

    override def SaveProPlayers(proPlayers: List[ProPlayer]): Unit = ???

    override def SaveProMatches(proMatches: List[ProMatch]): Unit = ???

    override def SaveMatch(matchData: Match): Unit = {


        object schema {
            def Match = quote {
                querySchema[MatchDao]("Match")
            }

            def League = quote {
                querySchema[League]("League")
            }
        }

        League(Option(1),1,Option("test"),Option("test"),"test", "test")

        val bla = quote(schema.League.insert(lift(League(Option(1),1,Option("test"),Option("test"),"test", "test"))))
        ctx.run(bla)

//        val matchInsert = quote(schema.Match.insert(lift(Match(
//            matchData.match_id,
//            matchData.barracks_status_dire,
//            matchData.barracks_status_radiant,
//            matchData.cluster,
//            matchData.dire_score,
//            matchData.dire_team_id,
//            matchData.duration,
//            matchData.engine,
//            matchData.first_blood_time,
//            matchData.game_mode,
//            matchData.human_players,
//            matchData.leagueid,
//            matchData.lobby_type,
//            matchData.match_seq_num,
//            matchData.negative_votes,
//            matchData.positive_votes,
//            matchData.radiant_score,
//            matchData.radiant_team_id,
//            matchData.skill,
//            matchData.start_time,
//            matchData.tower_status_dire,
//            matchData.tower_status_radiant,
//            matchData.version,
//            matchData.replay_salt,
//            matchData.series_id,
//            matchData.series_type,
//            matchData.patch,
//            matchData.region,
//            matchData.comeback,
//            matchData.stomp,
//            matchData.replay_url
//        ))))
//
//
//        ctx.run(matchInsert)

//        val a = quote(query[League].insert(lift(League(Option(4595179473L), 1,Option("string"), Option("string"), "string", "string"))))
//        ctx.run(a)
//
//        val b = quote(query[PickBan].insert(lift(PickBan(1, true, 1, 1, 1, 1))))
//        ctx.run(b)
        val test = "test"
    }

    override def RebuildDbMappings(): Unit = ???
}
