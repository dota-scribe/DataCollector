package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Pickban
trait PickbanTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Pickban
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param isPick Database column is_pick SqlType(bit)
   *  @param heroId Database column hero_id SqlType(int)
   *  @param team Database column team SqlType(int)
   *  @param order Database column order SqlType(int)
   *  @param ord Database column ord SqlType(int) */
  case class PickbanRow(matchId: Long, isPick: Boolean, heroId: Int, team: Int, order: Int, ord: Int)
  /** GetResult implicit for fetching PickbanRow objects using plain SQL queries */
  implicit def GetResultPickbanRow(implicit e0: GR[Long], e1: GR[Boolean], e2: GR[Int]): GR[PickbanRow] = GR{
    prs => import prs._
    PickbanRow.tupled((<<[Long], <<[Boolean], <<[Int], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table PickBan. Objects of this class serve as prototypes for rows in queries. */
  class Pickban(_tableTag: Tag) extends profile.api.Table[PickbanRow](_tableTag, Some("dbo"), "PickBan") {
    def * = (matchId, isPick, heroId, team, order, ord) <> (PickbanRow.tupled, PickbanRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(isPick), Rep.Some(heroId), Rep.Some(team), Rep.Some(order), Rep.Some(ord))).shaped.<>({r=>import r._; _1.map(_=> PickbanRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column is_pick SqlType(bit) */
    val isPick: Rep[Boolean] = column[Boolean]("is_pick")
    /** Database column hero_id SqlType(int) */
    val heroId: Rep[Int] = column[Int]("hero_id")
    /** Database column team SqlType(int) */
    val team: Rep[Int] = column[Int]("team")
    /** Database column order SqlType(int) */
    val order: Rep[Int] = column[Int]("order")
    /** Database column ord SqlType(int) */
    val ord: Rep[Int] = column[Int]("ord")

    /** Foreign key referencing Match (database name FK__PickBan__match_i__320C68B7) */
    lazy val matchFk = foreignKey("FK__PickBan__match_i__320C68B7", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Pickban */
  lazy val Pickban = new TableQuery(tag => new Pickban(tag))
}
