package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Chat
trait ChatTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Chat
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param time Database column time SqlType(float)
   *  @param unit Database column unit SqlType(varchar), Length(255,true)
   *  @param key Database column key SqlType(varchar), Length(255,true)
   *  @param slot Database column slot SqlType(int)
   *  @param playerSlot Database column player_slot SqlType(int) */
  case class ChatRow(matchId: Long, time: Double, unit: Option[String], key: String, slot: Int, playerSlot: Int)
  /** GetResult implicit for fetching ChatRow objects using plain SQL queries */
  implicit def GetResultChatRow(implicit e0: GR[Long], e1: GR[Double], e2: GR[Option[String]], e3: GR[String], e4: GR[Int]): GR[ChatRow] = GR{
    prs => import prs._
    ChatRow.tupled((<<[Long], <<[Double], <<?[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table Chat. Objects of this class serve as prototypes for rows in queries. */
  class Chat(_tableTag: Tag) extends profile.api.Table[ChatRow](_tableTag, Some("dbo"), "Chat") {
    def * = (matchId, time, unit, key, slot, playerSlot) <> (ChatRow.tupled, ChatRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(time), unit, Rep.Some(key), Rep.Some(slot), Rep.Some(playerSlot))).shaped.<>({r=>import r._; _1.map(_=> ChatRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column time SqlType(float) */
    val time: Rep[Double] = column[Double]("time")
    /** Database column unit SqlType(varchar), Length(255,true) */
    val unit: Rep[Option[String]] = column[Option[String]]("unit", O.Length(255,varying=true))
    /** Database column key SqlType(varchar), Length(255,true) */
    val key: Rep[String] = column[String]("key", O.Length(255,varying=true))
    /** Database column slot SqlType(int) */
    val slot: Rep[Int] = column[Int]("slot")
    /** Database column player_slot SqlType(int) */
    val playerSlot: Rep[Int] = column[Int]("player_slot")

    /** Foreign key referencing Match (database name FK__Chat__match_id__3548C815) */
    lazy val matchFk = foreignKey("FK__Chat__match_id__3548C815", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Chat */
  lazy val Chat = new TableQuery(tag => new Chat(tag))
}
