package Core

import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.CommandHandler.CliCommandHandler
import Infrastructure.Adapter.OpenDota.MsSql.DotaScribeSql
import Infrastructure.Adapter.OpenDota.OpenDotaAdaptor
import Infrastructure.Adapter.QuillDotaScribeSql.DAO.PostgresContext
import Infrastructure.Adapter.QuillDotaScribeSql.QuillDotaScribeSql
import Presentation.Cli.Cli
import com.typesafe.config.ConfigFactory
import slick.jdbc.SQLServerProfile.api._

object Main extends App {
    val conf = ConfigFactory.load()

    val profile = conf.getString("SqlServer.profile")
    val jdbcDriver = conf.getString("SqlServer.jdbcDriver")
    val url = conf.getString("SqlServer.url")
    val outputFolder = "src/main/scala"
    val pkg = "Infrastructure.Adapter.DotaScribeSql.Table"
    val user = conf.getString("SqlServer.user")
    val password = conf.getString("SqlServer.password")


//    val dbInstance = Database.forURL(url, user, password, null, jdbcDriver)

    val sqlContext = new PostgresContext()

    val openDotaAdapter = new OpenDotaAdaptor()
    //    val dotaScribeSql = new DotaScribeSql(dbInstance)
    val dotaScribeSql = new QuillDotaScribeSql(sqlContext)
    val dotaScribeDbService = new DotaScribeDbService(dotaScribeSql)
    val matchAppService = new MatchAppService(openDotaAdapter, dotaScribeSql)
    val proAppService = new ProAppService(openDotaAdapter, dotaScribeSql)
    val cliCommandHandler = new CliCommandHandler(proAppService, dotaScribeDbService, matchAppService)

    new Cli(cliCommandHandler).Init()
}