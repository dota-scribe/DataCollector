package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Radiantgoldadvantage
trait RadiantgoldadvantageTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Radiantgoldadvantage
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param goldAdvantage Database column gold_advantage SqlType(int) */
  case class RadiantgoldadvantageRow(matchId: Long, goldAdvantage: Int)
  /** GetResult implicit for fetching RadiantgoldadvantageRow objects using plain SQL queries */
  implicit def GetResultRadiantgoldadvantageRow(implicit e0: GR[Long], e1: GR[Int]): GR[RadiantgoldadvantageRow] = GR{
    prs => import prs._
    RadiantgoldadvantageRow.tupled((<<[Long], <<[Int]))
  }
  /** Table description of table RadiantGoldAdvantage. Objects of this class serve as prototypes for rows in queries. */
  class Radiantgoldadvantage(_tableTag: Tag) extends profile.api.Table[RadiantgoldadvantageRow](_tableTag, Some("dbo"), "RadiantGoldAdvantage") {
    def * = (matchId, goldAdvantage) <> (RadiantgoldadvantageRow.tupled, RadiantgoldadvantageRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(goldAdvantage))).shaped.<>({r=>import r._; _1.map(_=> RadiantgoldadvantageRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column gold_advantage SqlType(int) */
    val goldAdvantage: Rep[Int] = column[Int]("gold_advantage")

    /** Foreign key referencing Match (database name FK__RadiantGo__match__33F4B129) */
    lazy val matchFk = foreignKey("FK__RadiantGo__match__33F4B129", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Radiantgoldadvantage */
  lazy val Radiantgoldadvantage = new TableQuery(tag => new Radiantgoldadvantage(tag))
}
