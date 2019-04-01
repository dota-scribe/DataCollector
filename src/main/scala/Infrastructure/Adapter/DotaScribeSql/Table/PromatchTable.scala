package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Promatch
trait PromatchTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Promatch
   *  @param matchId Database column match_id SqlType(bigint), PrimaryKey
   *  @param duration Database column duration SqlType(bigint)
   *  @param startTime Database column start_time SqlType(bigint)
   *  @param radiantTeamId Database column radiant_team_id SqlType(bigint)
   *  @param radiantName Database column radiant_name SqlType(varchar), Length(255,true)
   *  @param direTeamId Database column dire_team_id SqlType(bigint)
   *  @param direName Database column dire_name SqlType(varchar), Length(255,true)
   *  @param leagueid Database column leagueid SqlType(bigint)
   *  @param leagueName Database column league_name SqlType(varchar), Length(255,true)
   *  @param seriesId Database column series_id SqlType(bigint)
   *  @param seriesType Database column series_type SqlType(bigint)
   *  @param radiantScore Database column radiant_score SqlType(bigint)
   *  @param direScore Database column dire_score SqlType(bigint)
   *  @param radiantWin Database column radiant_win SqlType(bit) */
  case class PromatchRow(matchId: Long, duration: Long, startTime: Long, radiantTeamId: Option[Long], radiantName: Option[String], direTeamId: Option[Long], direName: Option[String], leagueid: Long, leagueName: String, seriesId: Long, seriesType: Long, radiantScore: Long, direScore: Long, radiantWin: Boolean)
  /** GetResult implicit for fetching PromatchRow objects using plain SQL queries */
  implicit def GetResultPromatchRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[String], e4: GR[Boolean]): GR[PromatchRow] = GR{
    prs => import prs._
    PromatchRow.tupled((<<[Long], <<[Long], <<[Long], <<?[Long], <<?[String], <<?[Long], <<?[String], <<[Long], <<[String], <<[Long], <<[Long], <<[Long], <<[Long], <<[Boolean]))
  }
  /** Table description of table ProMatch. Objects of this class serve as prototypes for rows in queries. */
  class Promatch(_tableTag: Tag) extends profile.api.Table[PromatchRow](_tableTag, Some("dbo"), "ProMatch") {
    def * = (matchId, duration, startTime, radiantTeamId, radiantName, direTeamId, direName, leagueid, leagueName, seriesId, seriesType, radiantScore, direScore, radiantWin) <> (PromatchRow.tupled, PromatchRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(matchId), Rep.Some(duration), Rep.Some(startTime), radiantTeamId, radiantName, direTeamId, direName, Rep.Some(leagueid), Rep.Some(leagueName), Rep.Some(seriesId), Rep.Some(seriesType), Rep.Some(radiantScore), Rep.Some(direScore), Rep.Some(radiantWin))).shaped.<>({r=>import r._; _1.map(_=> PromatchRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint), PrimaryKey */
    val matchId: Rep[Long] = column[Long]("match_id", O.PrimaryKey)
    /** Database column duration SqlType(bigint) */
    val duration: Rep[Long] = column[Long]("duration")
    /** Database column start_time SqlType(bigint) */
    val startTime: Rep[Long] = column[Long]("start_time")
    /** Database column radiant_team_id SqlType(bigint) */
    val radiantTeamId: Rep[Option[Long]] = column[Option[Long]]("radiant_team_id")
    /** Database column radiant_name SqlType(varchar), Length(255,true) */
    val radiantName: Rep[Option[String]] = column[Option[String]]("radiant_name", O.Length(255,varying=true))
    /** Database column dire_team_id SqlType(bigint) */
    val direTeamId: Rep[Option[Long]] = column[Option[Long]]("dire_team_id")
    /** Database column dire_name SqlType(varchar), Length(255,true) */
    val direName: Rep[Option[String]] = column[Option[String]]("dire_name", O.Length(255,varying=true))
    /** Database column leagueid SqlType(bigint) */
    val leagueid: Rep[Long] = column[Long]("leagueid")
    /** Database column league_name SqlType(varchar), Length(255,true) */
    val leagueName: Rep[String] = column[String]("league_name", O.Length(255,varying=true))
    /** Database column series_id SqlType(bigint) */
    val seriesId: Rep[Long] = column[Long]("series_id")
    /** Database column series_type SqlType(bigint) */
    val seriesType: Rep[Long] = column[Long]("series_type")
    /** Database column radiant_score SqlType(bigint) */
    val radiantScore: Rep[Long] = column[Long]("radiant_score")
    /** Database column dire_score SqlType(bigint) */
    val direScore: Rep[Long] = column[Long]("dire_score")
    /** Database column radiant_win SqlType(bit) */
    val radiantWin: Rep[Boolean] = column[Boolean]("radiant_win")
  }
  /** Collection-like TableQuery object for table Promatch */
  lazy val Promatch = new TableQuery(tag => new Promatch(tag))
}
