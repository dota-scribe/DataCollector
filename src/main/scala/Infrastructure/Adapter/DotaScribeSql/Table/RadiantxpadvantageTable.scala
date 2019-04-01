package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Radiantxpadvantage
trait RadiantxpadvantageTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Radiantxpadvantage
   *  @param matchId Database column match_id SqlType(bigint)
   *  @param goldAdvantage Database column gold_advantage SqlType(int) */
  case class RadiantxpadvantageRow(matchId: Long, goldAdvantage: Int)
  /** GetResult implicit for fetching RadiantxpadvantageRow objects using plain SQL queries */
  implicit def GetResultRadiantxpadvantageRow(implicit e0: GR[Long], e1: GR[Int]): GR[RadiantxpadvantageRow] = GR{
    prs => import prs._
    RadiantxpadvantageRow.tupled((<<[Long], <<[Int]))
  }
  /** Table description of table RadiantXpAdvantage. Objects of this class serve as prototypes for rows in queries. */
  class Radiantxpadvantage(_tableTag: Tag) extends profile.api.Table[RadiantxpadvantageRow](_tableTag, Some("dbo"), "RadiantXpAdvantage") {
    def * = (matchId, goldAdvantage) <> (RadiantxpadvantageRow.tupled, RadiantxpadvantageRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(goldAdvantage))).shaped.<>({r=>import r._; _1.map(_=> RadiantxpadvantageRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint) */
    val matchId: Rep[Long] = column[Long]("match_id")
    /** Database column gold_advantage SqlType(int) */
    val goldAdvantage: Rep[Int] = column[Int]("gold_advantage")

    /** Foreign key referencing Match (database name FK__RadiantXp__match__35DCF99B) */
    lazy val matchFk = foreignKey("FK__RadiantXp__match__35DCF99B", matchId, Match)(r => r.matchId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Radiantxpadvantage */
  lazy val Radiantxpadvantage = new TableQuery(tag => new Radiantxpadvantage(tag))
}
