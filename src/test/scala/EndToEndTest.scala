import Core.Application.AppService.{MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import Infrastructure.Adapter.DatDota.{DatDotaAdaptor, DatDotaHttpWorker}
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

    val datDotaHttpWorker = new DatDotaHttpWorker()
    val datDotaAdapter = new DatDotaAdaptor(datDotaHttpWorker)

    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, datDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, matchAppService)

    test("Match1") {
        cliCommandHandler.CollectMatch(1)
        datDotaAdapter.GetPremiumMatches()
    }

    test("Match2") {
        cliCommandHandler.CollectMatch(2)
    }

    test("Match3") {
        cliCommandHandler.CollectMatch(3)
    }

    test("Match4") {
        cliCommandHandler.CollectMatch(4)
    }

    test("Match5") {
        cliCommandHandler.CollectMatch(5)
    }

    test("Match6") {
        cliCommandHandler.CollectMatch(6)
    }

    test("Match7") {
        cliCommandHandler.CollectMatch(7)
    }

    test("Match8") {
        cliCommandHandler.CollectMatch(8)
    }

    test("Match9") {
        cliCommandHandler.CollectMatch(9)
    }

    test("Match10") {
        cliCommandHandler.CollectMatch(10)
    }

    test("Match11") {
        cliCommandHandler.CollectMatch(11)
    }

    test("Match12") {
        cliCommandHandler.CollectMatch(12)
    }

    test("Match13") {
        cliCommandHandler.CollectMatch(13)
    }

    test("Match14") {
        cliCommandHandler.CollectMatch(14)
    }

    test("Match15") {
        cliCommandHandler.CollectMatch(15)
    }

    test("Match16") {
        cliCommandHandler.CollectMatch(16)
    }

    test("Match17") {
        cliCommandHandler.CollectMatch(17)
    }

    test("Match18") {
        cliCommandHandler.CollectMatch(18)
    }

    test("Match19") {
        cliCommandHandler.CollectMatch(19)
    }

    test("Match20") {
        cliCommandHandler.CollectMatch(20)
    }
}
