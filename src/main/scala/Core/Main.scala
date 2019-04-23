package Core

import Core.Application.AppService.{MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import Infrastructure.Adapter.DatDota.{DatDotaAdaptor, DatDotaHttpWorker}
import Infrastructure.Adapter.OpenDota.{OpenDotaAdaptor, OpenDotaHttpWorker}
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Presentation.Cli.Cli
import io.getquill.{PostgresEscape, SqlServerJdbcContext}

object Main extends App {
    val sqlContext = new SqlServerJdbcContext(PostgresEscape, "ctx")
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)

    val httpWorker = new OpenDotaHttpWorker()
    val openDotaAdapter = new OpenDotaAdaptor(httpWorker)


    val datDotaHttpWorker = new DatDotaHttpWorker()
    val datDotaAdapter = new DatDotaAdaptor(datDotaHttpWorker)


    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, datDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, matchAppService)

    new Cli(cliCommandHandler).Init()
}