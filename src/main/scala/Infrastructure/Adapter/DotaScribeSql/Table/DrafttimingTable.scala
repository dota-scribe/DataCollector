package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Drafttiming
trait DrafttimingTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Drafttiming
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param order Database column order SqlType(int)
   *  @param pick Database column pick SqlType(bit)
   *  @param activeTeam Database column active_team SqlType(int)
   *  @param heroId Database column hero_id SqlType(int)
   *  @param playerSlot Database column player_slot SqlType(int)
   *  @param extraTime Database column extra_time SqlType(int)
   *  @param totalTimeTaken Database column total_time_taken SqlType(int) */
  case class DrafttimingRow(matchId: Long, order: Int, pick: Boolean, activeTeam: Int, heroId: Int, playerSlot: Option[Int], extraTime: Int, totalTimeTaken: Int)
  /** GetResult implicit for fetching DrafttimingRow objects using plain SQL queries */
  implicit def GetResultDrafttimingRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Boolean], e3: GR[Option[Int]]): GR[DrafttimingRow] = GR{
    prs => import prs._
    DrafttimingRow.tupled((<<[Long], <<[Int], <<[Boolean], <<[Int], <<[Int], <<?[Int], <<[Int], <<[Int]))
  }
  /** Table description of table DraftTiming. Objects of this class serve as prototypes for rows in queries. */
  class Drafttiming(_tableTag: Tag) extends profile.api.Table[DrafttimingRow](_tableTag, Some("dbo"), "DraftTiming") {
    def * = (matchId, order, pick, activeTeam, heroId, playerSlot, extraTime, totalTimeTaken) <> (DrafttimingRow.tupled, DrafttimingRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(order), Rep.Some(pick), Rep.Some(activeTeam), Rep.Some(heroId), playerSlot, Rep.Some(extraTime), Rep.Some(totalTimeTaken))).shaped.<>({r=>import r._; _1.map(_=> DrafttimingRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column order SqlType(int) */
    val order: Rep[Int] = column[Int]("order")
    /** Database column pick SqlType(bit) */
    val pick: Rep[Boolean] = column[Boolean]("pick")
    /** Database column active_team SqlType(int) */
    val activeTeam: Rep[Int] = column[Int]("active_team")
    /** Database column hero_id SqlType(int) */
    val heroId: Rep[Int] = column[Int]("hero_id")
    /** Database column player_slot SqlType(int) */
    val playerSlot: Rep[Option[Int]] = column[Option[Int]]("player_slot")
    /** Database column extra_time SqlType(int) */
    val extraTime: Rep[Int] = column[Int]("extra_time")
    /** Database column total_time_taken SqlType(int) */
    val totalTimeTaken: Rep[Int] = column[Int]("total_time_taken")

    /** Foreign key referencing Match (database name FK__DraftTimi__total__37311087) */
    lazy val matchFk = foreignKey("FK__DraftTimi__total__37311087", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Drafttiming */
  lazy val Drafttiming = new TableQuery(tag => new Drafttiming(tag))
}
