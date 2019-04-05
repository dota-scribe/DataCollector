import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import org.scalatest.FunSuite
import Infrastructure.Adapter.OpenDota.OpenDotaAdaptor
import Infrastructure.Adapter.QuillDotaScribeSql.DAO.SqlServerContext
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Mocks.MockHttpWorker

class EndToEndTest extends FunSuite {
    val sqlContext = new SqlServerContext()
    val httpWorker = new MockHttpWorker()

    val openDotaAdapter = new OpenDotaAdaptor(httpWorker)
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)
    val dotaScribeDbService = new DotaScribeDbService(dotaScribeSql)
    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, dotaScribeDbService, matchAppService)


    test("EndToEndTest") {
        cliCommandHandler.GetMatch(1)
        cliCommandHandler.GetMatch(2)
        cliCommandHandler.GetMatch(3)
        cliCommandHandler.GetMatch(4)
        cliCommandHandler.GetMatch(5)
        cliCommandHandler.GetMatch(6)
        cliCommandHandler.GetMatch(7)
        cliCommandHandler.GetMatch(8)
        cliCommandHandler.GetMatch(9)
        cliCommandHandler.GetMatch(10)
    }
}
