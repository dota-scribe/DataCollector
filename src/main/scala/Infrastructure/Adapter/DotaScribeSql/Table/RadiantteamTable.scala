package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Radiantteam
trait RadiantteamTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Radiantteam
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param teamId Database column team_id SqlType(int)
   *  @param name Database column name SqlType(varchar), Length(255,true)
   *  @param tag Database column tag SqlType(varchar), Length(255,true)
   *  @param logoUrl Database column logo_url SqlType(varchar), Length(255,true) */
  case class RadiantteamRow(matchId: Long, teamId: Int, name: String, tag: String, logoUrl: String)
  /** GetResult implicit for fetching RadiantteamRow objects using plain SQL queries */
  implicit def GetResultRadiantteamRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[String]): GR[RadiantteamRow] = GR{
    prs => import prs._
    RadiantteamRow.tupled((<<[Long], <<[Int], <<[String], <<[String], <<[String]))
  }
  /** Table description of table RadiantTeam. Objects of this class serve as prototypes for rows in queries. */
  class Radiantteam(_tableTag: Tag) extends profile.api.Table[RadiantteamRow](_tableTag, Some("dbo"), "RadiantTeam") {
    def * = (matchId, teamId, name, tag, logoUrl) <> (RadiantteamRow.tupled, RadiantteamRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(teamId), Rep.Some(name), Rep.Some(tag), Rep.Some(logoUrl))).shaped.<>({r=>import r._; _1.map(_=> RadiantteamRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column team_id SqlType(int) */
    val teamId: Rep[Int] = column[Int]("team_id")
    /** Database column name SqlType(varchar), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column tag SqlType(varchar), Length(255,true) */
    val tag: Rep[String] = column[String]("tag", O.Length(255,varying=true))
    /** Database column logo_url SqlType(varchar), Length(255,true) */
    val logoUrl: Rep[String] = column[String]("logo_url", O.Length(255,varying=true))

    /** Foreign key referencing Match (database name FK__RadiantTe__logo___37C5420D) */
    lazy val matchFk = foreignKey("FK__RadiantTe__logo___37C5420D", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Radiantteam */
  lazy val Radiantteam = new TableQuery(tag => new Radiantteam(tag))
}
