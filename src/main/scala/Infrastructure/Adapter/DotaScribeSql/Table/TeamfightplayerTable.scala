package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Teamfightplayer
trait TeamfightplayerTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Teamfightplayer
   *  @param teamfightPlayerId Database column teamfight_player_id SqlType(bigint identity), AutoInc, PrimaryKey
   *  @param teamfightId Database column teamfight_id SqlType(bigint)
   *  @param deaths Database column deaths SqlType(int)
   *  @param buybacks Database column buybacks SqlType(int)
   *  @param damage Database column damage SqlType(int)
   *  @param healing Database column healing SqlType(int)
   *  @param goldDelta Database column gold_delta SqlType(int)
   *  @param xpDelta Database column xp_delta SqlType(int)
   *  @param xpStart Database column xp_start SqlType(int)
   *  @param xpEnd Database column xp_end SqlType(int) */
  case class TeamfightplayerRow(teamfightPlayerId: Long, teamfightId: Long, deaths: Int, buybacks: Int, damage: Int, healing: Int, goldDelta: Int, xpDelta: Int, xpStart: Int, xpEnd: Int)
  /** GetResult implicit for fetching TeamfightplayerRow objects using plain SQL queries */
  implicit def GetResultTeamfightplayerRow(implicit e0: GR[Long], e1: GR[Int]): GR[TeamfightplayerRow] = GR{
    prs => import prs._
    TeamfightplayerRow.tupled((<<[Long], <<[Long], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table TeamFightPlayer. Objects of this class serve as prototypes for rows in queries. */
  class Teamfightplayer(_tableTag: Tag) extends profile.api.Table[TeamfightplayerRow](_tableTag, Some("dbo"), "TeamFightPlayer") {
    def * = (teamfightPlayerId, teamfightId, deaths, buybacks, damage, healing, goldDelta, xpDelta, xpStart, xpEnd) <> (TeamfightplayerRow.tupled, TeamfightplayerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(teamfightPlayerId), Rep.Some(teamfightId), Rep.Some(deaths), Rep.Some(buybacks), Rep.Some(damage), Rep.Some(healing), Rep.Some(goldDelta), Rep.Some(xpDelta), Rep.Some(xpStart), Rep.Some(xpEnd))).shaped.<>({r=>import r._; _1.map(_=> TeamfightplayerRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column teamfight_player_id SqlType(bigint identity), AutoInc, PrimaryKey */
    val teamfightPlayerId: Rep[Long] = column[Long]("teamfight_player_id", O.AutoInc, O.PrimaryKey)
    /** Database column teamfight_id SqlType(bigint) */
    val teamfightId: Rep[Long] = column[Long]("teamfight_id")
    /** Database column deaths SqlType(int) */
    val deaths: Rep[Int] = column[Int]("deaths")
    /** Database column buybacks SqlType(int) */
    val buybacks: Rep[Int] = column[Int]("buybacks")
    /** Database column damage SqlType(int) */
    val damage: Rep[Int] = column[Int]("damage")
    /** Database column healing SqlType(int) */
    val healing: Rep[Int] = column[Int]("healing")
    /** Database column gold_delta SqlType(int) */
    val goldDelta: Rep[Int] = column[Int]("gold_delta")
    /** Database column xp_delta SqlType(int) */
    val xpDelta: Rep[Int] = column[Int]("xp_delta")
    /** Database column xp_start SqlType(int) */
    val xpStart: Rep[Int] = column[Int]("xp_start")
    /** Database column xp_end SqlType(int) */
    val xpEnd: Rep[Int] = column[Int]("xp_end")

    /** Foreign key referencing Teamfight (database name FK__TeamFight__teamf__485B9C89) */
    lazy val teamfightFk = foreignKey("FK__TeamFight__teamf__485B9C89", teamfightId, Teamfight)(r => r.teamfightId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Teamfightplayer */
  lazy val Teamfightplayer = new TableQuery(tag => new Teamfightplayer(tag))
}
