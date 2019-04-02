package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Teamfightdeathposition
trait TeamfightdeathpositionTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Teamfightdeathposition
   *  @param teamfightPlayerId Database column teamfight_player_id SqlType(bigint)
   *  @param x Database column x SqlType(int)
   *  @param y Database column y SqlType(int)
   *  @param z Database column z SqlType(int) */
  case class TeamfightdeathpositionRow(teamfightPlayerId: Long, x: Int, y: Int, z: Int)
  /** GetResult implicit for fetching TeamfightdeathpositionRow objects using plain SQL queries */
  implicit def GetResultTeamfightdeathpositionRow(implicit e0: GR[Long], e1: GR[Int]): GR[TeamfightdeathpositionRow] = GR{
    prs => import prs._
    TeamfightdeathpositionRow.tupled((<<[Long], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table TeamFightDeathPosition. Objects of this class serve as prototypes for rows in queries. */
  class Teamfightdeathposition(_tableTag: Tag) extends profile.api.Table[TeamfightdeathpositionRow](_tableTag, Some("dbo"), "TeamFightDeathPosition") {
    def * = (teamfightPlayerId, x, y, z) <> (TeamfightdeathpositionRow.tupled, TeamfightdeathpositionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(teamfightPlayerId), Rep.Some(x), Rep.Some(y), Rep.Some(z))).shaped.<>({r=>import r._; _1.map(_=> TeamfightdeathpositionRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column teamfight_player_id SqlType(bigint) */
    val teamfightPlayerId: Rep[Long] = column[Long]("teamfight_player_id")
    /** Database column x SqlType(int) */
    val x: Rep[Int] = column[Int]("x")
    /** Database column y SqlType(int) */
    val y: Rep[Int] = column[Int]("y")
    /** Database column z SqlType(int) */
    val z: Rep[Int] = column[Int]("z")

    /** Foreign key referencing Teamfightplayer (database name FK__TeamFightDeat__z__4A43E4FB) */
    lazy val teamfightplayerFk = foreignKey("FK__TeamFightDeat__z__4A43E4FB", teamfightPlayerId, Teamfightplayer)(r => r.teamfightPlayerId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Teamfightdeathposition */
  lazy val Teamfightdeathposition = new TableQuery(tag => new Teamfightdeathposition(tag))
}
