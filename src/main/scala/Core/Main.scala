package Core

//import Core.Application.AppService.ProAppService
//import Core.Application.CommandHandler.CliCommandHandler
//import Infrastructure.Adapter.OpenDota.MsSql.DotaScribeSql
//import Infrastructure.Adapter.OpenDota.OpenDotaAdaptor
//import Presentation.Cli.Cli
//
//object Main extends App {
//    val DotaScribeSql = new DotaScribeSql()
//    val openDotaAdapter = new OpenDotaAdaptor()
//    val proAppService = new ProAppService(openDotaAdapter, DotaScribeSql)
//    val cliCommandHandler = new CliCommandHandler(proAppService)
//
//    println("starting up")
//    val sql = new DotaScribeSql();
//    DotaScribeSql.TestDb()
//
////    new Cli(cliCommandHandler).Init()
//}






import Infrastructure.Adapter.DotaScribeSql.Table.ProPlayerTable
import Infrastructure.Adapter.OpenDota.MsSql.DotaScribeSql
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
//#imports
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import scala.collection.mutable.ArrayBuffer

import Infrastructure.Adapter.DotaScribeSql.Table.Suppliers
import Infrastructure.Adapter.DotaScribeSql.Table.Coffees

/**
  * A simple example that uses statically typed queries against an in-memory
  * H2 database. The example data comes from Oracle's JDBC tutorial at
  * http://docs.oracle.com/javase/tutorial/jdbc/basics/tables.html.
  */
object Main extends App {
    val lines = new ArrayBuffer[Any]()
    def println(s: Any) = lines += s

    val proPlayerTable =TableQuery[ProPlayerTable]

    // Connect to the database and execute the following block within a session
//    val db = Database.forConfig("h2mem1")
    val db = Database.forConfig("sqlserver")

    try {
        val sql = new DotaScribeSql()

        Await.result(sql.InsertRows(db), Duration.Inf)

            //#readall
            // Read all coffees and print them to the console
            println("Coffees:")
        val resultFuture = db.run(proPlayerTable.result).map(_.foreach {
                case (avatarfull) =>
                    println(avatarfull)
            })
            // Equivalent SQL code:
            // select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES
            //#readall

        Await.result(resultFuture, Duration.Inf)
        lines.foreach(Predef.println _)
    } finally db.close
}