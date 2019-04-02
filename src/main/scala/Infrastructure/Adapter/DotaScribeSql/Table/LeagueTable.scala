package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table League
trait LeagueTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table League
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param leagueid Database column leagueid SqlType(int)
   *  @param ticket Database column ticket SqlType(varchar), Length(255,true)
   *  @param banner Database column banner SqlType(varchar), Length(255,true)
   *  @param tier Database column tier SqlType(varchar), Length(255,true)
   *  @param name Database column name SqlType(varchar), Length(255,true) */
  case class LeagueRow(matchId: Long, leagueid: Int, ticket: Option[String], banner: Option[String], tier: String, name: String)
  /** GetResult implicit for fetching LeagueRow objects using plain SQL queries */
  implicit def GetResultLeagueRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Option[String]], e3: GR[String]): GR[LeagueRow] = GR{
    prs => import prs._
    LeagueRow.tupled((<<[Long], <<[Int], <<?[String], <<?[String], <<[String], <<[String]))
  }
  /** Table description of table League. Objects of this class serve as prototypes for rows in queries. */
  class League(_tableTag: Tag) extends profile.api.Table[LeagueRow](_tableTag, Some("dbo"), "League") {
    def * = (matchId, leagueid, ticket, banner, tier, name) <> (LeagueRow.tupled, LeagueRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(leagueid), ticket, banner, Rep.Some(tier), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> LeagueRow.tupled((_1.get, _2.get, _3, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column leagueid SqlType(int) */
    val leagueid: Rep[Int] = column[Int]("leagueid")
    /** Database column ticket SqlType(varchar), Length(255,true) */
    val ticket: Rep[Option[String]] = column[Option[String]]("ticket", O.Length(255,varying=true))
    /** Database column banner SqlType(varchar), Length(255,true) */
    val banner: Rep[Option[String]] = column[Option[String]]("banner", O.Length(255,varying=true))
    /** Database column tier SqlType(varchar), Length(255,true) */
    val tier: Rep[String] = column[String]("tier", O.Length(255,varying=true))
    /** Database column name SqlType(varchar), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))

    /** Foreign key referencing Match (database name FK__League__match_id__42A2C333) */
    lazy val matchFk = foreignKey("FK__League__match_id__42A2C333", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table League */
  lazy val League = new TableQuery(tag => new League(tag))
}
