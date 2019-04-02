package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Teamfight
trait TeamfightTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Teamfight
   *  @param teamfightId Database column teamfight_id SqlType(bigint identity), AutoInc, PrimaryKey
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param start Database column start SqlType(int)
   *  @param end Database column end SqlType(int)
   *  @param lastDeath Database column last_death SqlType(int)
   *  @param deaths Database column deaths SqlType(int) */
  case class TeamfightRow(teamfightId: Long, matchId: Long, start: Int, end: Int, lastDeath: Int, deaths: Int)
  /** GetResult implicit for fetching TeamfightRow objects using plain SQL queries */
  implicit def GetResultTeamfightRow(implicit e0: GR[Long], e1: GR[Int]): GR[TeamfightRow] = GR{
    prs => import prs._
    TeamfightRow.tupled((<<[Long], <<[Long], <<[Int], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table TeamFight. Objects of this class serve as prototypes for rows in queries. */
  class Teamfight(_tableTag: Tag) extends profile.api.Table[TeamfightRow](_tableTag, Some("dbo"), "TeamFight") {
    def * = (teamfightId, matchId, start, end, lastDeath, deaths) <> (TeamfightRow.tupled, TeamfightRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(teamfightId), Rep.Some(matchId), Rep.Some(start), Rep.Some(end), Rep.Some(lastDeath), Rep.Some(deaths))).shaped.<>({r=>import r._; _1.map(_=> TeamfightRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column teamfight_id SqlType(bigint identity), AutoInc, PrimaryKey */
    val teamfightId: Rep[Long] = column[Long]("teamfight_id", O.AutoInc, O.PrimaryKey)
    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column start SqlType(int) */
    val start: Rep[Int] = column[Int]("start")
    /** Database column end SqlType(int) */
    val end: Rep[Int] = column[Int]("end")
    /** Database column last_death SqlType(int) */
    val lastDeath: Rep[Int] = column[Int]("last_death")
    /** Database column deaths SqlType(int) */
    val deaths: Rep[Int] = column[Int]("deaths")

    /** Foreign key referencing Match (database name FK__TeamFight__match__457F2FDE) */
    lazy val matchFk = foreignKey("FK__TeamFight__match__457F2FDE", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Teamfight */
  lazy val Teamfight = new TableQuery(tag => new Teamfight(tag))
}
