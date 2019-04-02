package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import io.getquill.{SnakeCase, SqlServerJdbcContext}

class QuillDotaScribeSql extends DotaScribeRepositoryPort{

    override def SaveProPlayers(proPlayers: List[ProPlayer]): Unit = ???

    override def SaveProMatches(proMatches: List[ProMatch]): Unit = ???

    override def SaveMatch(matchData: Match): Unit = {
        val ctx = new SqlServerJdbcContext(SnakeCase, "ctx")

        import ctx._

        val a = quote(query[League].insert(lift(League(Option(4595179473L), 1,Option("string"), Option("string"), "string", "string"))))
        ctx.run(a)
        val test = "test"
    }

    override def RebuildDbMappings(): Unit = ???
}
