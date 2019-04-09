import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import org.scalatest.FunSuite
import Infrastructure.Adapter.OpenDota.OpenDotaAdaptor
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Mocks.MockHttpWorker
import io.getquill._

class EndToEndTest extends FunSuite {
    val sqlContext = new  H2JdbcContext(PostgresEscape, "testCtx")
//    val sqlContext = new SqlServerJdbcContext(PostgresEscape, "ctx")
    val httpWorker = new MockHttpWorker()

    val openDotaAdapter = new OpenDotaAdaptor(httpWorker)
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)

    val dotaScribeDbService = new DotaScribeDbService(dotaScribeSql)
    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, dotaScribeDbService, matchAppService)

    test("EndToEndTest") {
        cliCommandHandler.CollectMatch(1)
//        cliCommandHandler.CollectMatch(2)
//        cliCommandHandler.CollectMatch(3)
//        cliCommandHandler.CollectMatch(4)
//        cliCommandHandler.CollectMatch(5)
//        cliCommandHandler.CollectMatch(6)
//        cliCommandHandler.CollectMatch(7)
//        cliCommandHandler.CollectMatch(8)
//        cliCommandHandler.CollectMatch(9)
//        cliCommandHandler.CollectMatch(10)
    }
}

//Error Decoding Match :4623745742
//Error Decoding Match :4624098952
//Error Decoding Match :4624208453
//Error Decoding Match :4624337382
//Error Decoding Match :4624902509
//Error Decoding Match :4624957904
//Error Decoding Match :4625059213
//Error Decoding Match :4625416833
//Error Decoding Match :4627129272
//Error Decoding Match :4627357788
//Error Decoding Match :4627543814
//Error Decoding Match :4627678985
//Error Decoding Match :4627850702
//Error Decoding Match :4628006433
//Error Decoding Match :4628286508
//Error Decoding Match :4628539172
//Error Decoding Match :4630689935
//Error Decoding Match :4630828315
//Error Decoding Match :4631174067
//Error Decoding Match :4631286513
//Error Decoding Match :4631286965
//Error Decoding Match :4631391840
//Error Decoding Match :4631463128
//Error Decoding Match :4631502463
//Error Decoding Match :4631529068
//Error Decoding Match :4631558002
//Error Decoding Match :4631640965
//Error Decoding Match :4631704362
//Error Decoding Match :4631732656
//Error Decoding Match :4631753111
//Error Decoding Match :4631784887
//Error Decoding Match :4631858530
