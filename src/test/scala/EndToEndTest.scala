import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import org.scalatest.FunSuite
import Infrastructure.Adapter.OpenDota.OpenDotaAdaptor
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Mocks.MockHttpWorker
import io.getquill._

class EndToEndTest extends FunSuite {
//    val sqlContext = new  H2JdbcContext(PostgresEscape, "testCtx")
    val sqlContext = new SqlServerJdbcContext(PostgresEscape, "ctx")
    val httpWorker = new MockHttpWorker()

    val openDotaAdapter = new OpenDotaAdaptor(httpWorker)
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)

    val dotaScribeDbService = new DotaScribeDbService(dotaScribeSql)
    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, dotaScribeDbService, matchAppService)

    test("EndToEndTest") {
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
        cliCommandHandler.CollectMatch(11)
        cliCommandHandler.CollectMatch(12)
        cliCommandHandler.CollectMatch(13)
        cliCommandHandler.CollectMatch(14)
        cliCommandHandler.CollectMatch(15)
        cliCommandHandler.CollectMatch(16)
        cliCommandHandler.CollectMatch(17)
        cliCommandHandler.CollectMatch(18)
        cliCommandHandler.CollectMatch(19)
        cliCommandHandler.CollectMatch(20)
    }
}
