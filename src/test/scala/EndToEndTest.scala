import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory
import Infrastructure.Adapter.OpenDota.OpenDotaAdaptor
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Mocks.MockHttpWorker
import io.getquill._

class EndToEndTest extends FunSuite with MockFactory  {
    val sqlContext = new  H2JdbcContext(PostgresEscape, "testCtx")
//    val mockSql = stub[SqlServerJdbcContext[PostgresEscape]]
    val httpWorker = new MockHttpWorker()

    val openDotaAdapter = new OpenDotaAdaptor(httpWorker)
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)

    val dotaScribeDbService = new DotaScribeDbService(dotaScribeSql)
    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, dotaScribeDbService, matchAppService)

    test("EndToEndTest") {
        CreateSchema()

        cliCommandHandler.CollectMatch(1)
        cliCommandHandler.CollectMatch(2)
        cliCommandHandler.CollectMatch(3)
        cliCommandHandler.CollectMatch(4)
        cliCommandHandler.CollectMatch(5)
        cliCommandHandler.CollectMatch(6)
        cliCommandHandler.CollectMatch(7)
        cliCommandHandler.CollectMatch(8)
        cliCommandHandler.CollectMatch(9)
        cliCommandHandler.CollectMatch(10)
    }

    def CreateSchema(): Unit = {
        val ctx = sqlContext

        import ctx._

        val rawQuery = quote {
            infix"""
                CREATE TABLE ProPlayer (
                    account_id INT NOT NULL,
                    steamid VARCHAR(255) NOT NULL,
                    avatar VARCHAR(255) NOT NULL,
                    avatarmedium VARCHAR(255) NOT NULL,
                    avatarfull VARCHAR(255) NOT NULL,
                    profileurl VARCHAR(255) NOT NULL,
                    personaname VARCHAR(255) NOT NULL,
                    last_login VARCHAR(255),
                    full_history_time VARCHAR(255),
                    cheese INT NOT NULL,
                    fh_unavailable BIT,
                    loccountrycode VARCHAR(255),
                    last_match_time VARCHAR(255) NOT NULL,
                    plus BIT,
                    name VARCHAR(255) NOT NULL,
                    country_code VARCHAR(255) NOT NULL,
                    fantasy_role INT NOT NULL,
                    team_id INT NOT NULL,
                    team_name VARCHAR(255),
                    team_tag VARCHAR(255),
                    is_locked BIT NOT NULL,
                    is_pro BIT NOT NULL,
                    locked_until VARCHAR(255),
                )
             """.as[Query[String]]
        }
        ctx.run(rawQuery)
    }
}
