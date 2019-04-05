package Core

import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import Infrastructure.Adapter.OpenDota.{HttpWorker, OpenDotaAdaptor}
import Infrastructure.Adapter.QuillDotaScribeSql.DAO.SqlServerContext
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Presentation.Cli.Cli

object Main extends App {
    val sqlContext = new SqlServerContext()
    val httpWorker = new HttpWorker()

    val openDotaAdapter = new OpenDotaAdaptor(httpWorker)
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)
    val dotaScribeDbService = new DotaScribeDbService(dotaScribeSql)
    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, dotaScribeDbService, matchAppService)

    new Cli(cliCommandHandler).Init()
}