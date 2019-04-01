package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Match
trait MatchTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Match
   *  @param matchId Database column match_id SqlType(bigint), PrimaryKey
   *  @param barracksStatusDire Database column barracks_status_dire SqlType(int)
   *  @param barracksStatusRadiant Database column barracks_status_radiant SqlType(int)
   *  @param cluster Database column cluster SqlType(int)
   *  @param direScore Database column dire_score SqlType(int)
   *  @param direTeamId Database column dire_team_id SqlType(int)
   *  @param duration Database column duration SqlType(int)
   *  @param engine Database column engine SqlType(int)
   *  @param firstBloodTime Database column first_blood_time SqlType(int)
   *  @param gameMode Database column game_mode SqlType(int)
   *  @param humanPlayers Database column human_players SqlType(int)
   *  @param leagueid Database column leagueid SqlType(int)
   *  @param lobbyType Database column lobby_type SqlType(int)
   *  @param matchSeqNum Database column match_seq_num SqlType(bigint)
   *  @param negativeVotes Database column negative_votes SqlType(int)
   *  @param positiveVotes Database column positive_votes SqlType(int)
   *  @param radiantScore Database column radiant_score SqlType(int)
   *  @param radiantTeamId Database column radiant_team_id SqlType(int)
   *  @param skill Database column skill SqlType(int)
   *  @param startTime Database column start_time SqlType(int)
   *  @param towerStatusDire Database column tower_status_dire SqlType(int)
   *  @param towerStatusRadiant Database column tower_status_radiant SqlType(int)
   *  @param version Database column version SqlType(int)
   *  @param replaySalt Database column replay_salt SqlType(int)
   *  @param seriesId Database column series_id SqlType(int)
   *  @param seriesType Database column series_type SqlType(int)
   *  @param patch Database column patch SqlType(int)
   *  @param region Database column region SqlType(int)
   *  @param comeback Database column comeback SqlType(int)
   *  @param stomp Database column stomp SqlType(int)
   *  @param replayUrl Database column replay_url SqlType(varchar), Length(255,true) */
  case class MatchRow(matchId: Long, barracksStatusDire: Int, barracksStatusRadiant: Int, cluster: Int, direScore: Int, direTeamId: Int, duration: Int, engine: Int, firstBloodTime: Int, gameMode: Int, humanPlayers: Int, leagueid: Int, lobbyType: Int, matchSeqNum: Long, negativeVotes: Int, positiveVotes: Int, radiantScore: Int, radiantTeamId: Int, skill: Option[Int], startTime: Int, towerStatusDire: Int, towerStatusRadiant: Int, version: Int, replaySalt: Int, seriesId: Int, seriesType: Int, patch: Int, region: Int, comeback: Int, stomp: Int, replayUrl: String)
  /** GetResult implicit for fetching MatchRow objects using plain SQL queries */
  implicit def GetResultMatchRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Option[Int]], e3: GR[String]): GR[MatchRow] = GR{
    prs => import prs._
    MatchRow(<<[Long], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Long], <<[Int], <<[Int], <<[Int], <<[Int], <<?[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[String])
  }
  /** Table description of table Match. Objects of this class serve as prototypes for rows in queries. */
  class Match(_tableTag: Tag) extends profile.api.Table[MatchRow](_tableTag, Some("dbo"), "Match") {
    def * = (matchId :: barracksStatusDire :: barracksStatusRadiant :: cluster :: direScore :: direTeamId :: duration :: engine :: firstBloodTime :: gameMode :: humanPlayers :: leagueid :: lobbyType :: matchSeqNum :: negativeVotes :: positiveVotes :: radiantScore :: radiantTeamId :: skill :: startTime :: towerStatusDire :: towerStatusRadiant :: version :: replaySalt :: seriesId :: seriesType :: patch :: region :: comeback :: stomp :: replayUrl :: HNil).mapTo[MatchRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(matchId) :: Rep.Some(barracksStatusDire) :: Rep.Some(barracksStatusRadiant) :: Rep.Some(cluster) :: Rep.Some(direScore) :: Rep.Some(direTeamId) :: Rep.Some(duration) :: Rep.Some(engine) :: Rep.Some(firstBloodTime) :: Rep.Some(gameMode) :: Rep.Some(humanPlayers) :: Rep.Some(leagueid) :: Rep.Some(lobbyType) :: Rep.Some(matchSeqNum) :: Rep.Some(negativeVotes) :: Rep.Some(positiveVotes) :: Rep.Some(radiantScore) :: Rep.Some(radiantTeamId) :: skill :: Rep.Some(startTime) :: Rep.Some(towerStatusDire) :: Rep.Some(towerStatusRadiant) :: Rep.Some(version) :: Rep.Some(replaySalt) :: Rep.Some(seriesId) :: Rep.Some(seriesType) :: Rep.Some(patch) :: Rep.Some(region) :: Rep.Some(comeback) :: Rep.Some(stomp) :: Rep.Some(replayUrl) :: HNil).shaped.<>(r => MatchRow(r(0).asInstanceOf[Option[Long]].get, r(1).asInstanceOf[Option[Int]].get, r(2).asInstanceOf[Option[Int]].get, r(3).asInstanceOf[Option[Int]].get, r(4).asInstanceOf[Option[Int]].get, r(5).asInstanceOf[Option[Int]].get, r(6).asInstanceOf[Option[Int]].get, r(7).asInstanceOf[Option[Int]].get, r(8).asInstanceOf[Option[Int]].get, r(9).asInstanceOf[Option[Int]].get, r(10).asInstanceOf[Option[Int]].get, r(11).asInstanceOf[Option[Int]].get, r(12).asInstanceOf[Option[Int]].get, r(13).asInstanceOf[Option[Long]].get, r(14).asInstanceOf[Option[Int]].get, r(15).asInstanceOf[Option[Int]].get, r(16).asInstanceOf[Option[Int]].get, r(17).asInstanceOf[Option[Int]].get, r(18).asInstanceOf[Option[Int]], r(19).asInstanceOf[Option[Int]].get, r(20).asInstanceOf[Option[Int]].get, r(21).asInstanceOf[Option[Int]].get, r(22).asInstanceOf[Option[Int]].get, r(23).asInstanceOf[Option[Int]].get, r(24).asInstanceOf[Option[Int]].get, r(25).asInstanceOf[Option[Int]].get, r(26).asInstanceOf[Option[Int]].get, r(27).asInstanceOf[Option[Int]].get, r(28).asInstanceOf[Option[Int]].get, r(29).asInstanceOf[Option[Int]].get, r(30).asInstanceOf[Option[String]].get), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column match_id SqlType(bigint), PrimaryKey */
    val matchId: Rep[Long] = column[Long]("match_id", O.PrimaryKey)
    /** Database column barracks_status_dire SqlType(int) */
    val barracksStatusDire: Rep[Int] = column[Int]("barracks_status_dire")
    /** Database column barracks_status_radiant SqlType(int) */
    val barracksStatusRadiant: Rep[Int] = column[Int]("barracks_status_radiant")
    /** Database column cluster SqlType(int) */
    val cluster: Rep[Int] = column[Int]("cluster")
    /** Database column dire_score SqlType(int) */
    val direScore: Rep[Int] = column[Int]("dire_score")
    /** Database column dire_team_id SqlType(int) */
    val direTeamId: Rep[Int] = column[Int]("dire_team_id")
    /** Database column duration SqlType(int) */
    val duration: Rep[Int] = column[Int]("duration")
    /** Database column engine SqlType(int) */
    val engine: Rep[Int] = column[Int]("engine")
    /** Database column first_blood_time SqlType(int) */
    val firstBloodTime: Rep[Int] = column[Int]("first_blood_time")
    /** Database column game_mode SqlType(int) */
    val gameMode: Rep[Int] = column[Int]("game_mode")
    /** Database column human_players SqlType(int) */
    val humanPlayers: Rep[Int] = column[Int]("human_players")
    /** Database column leagueid SqlType(int) */
    val leagueid: Rep[Int] = column[Int]("leagueid")
    /** Database column lobby_type SqlType(int) */
    val lobbyType: Rep[Int] = column[Int]("lobby_type")
    /** Database column match_seq_num SqlType(bigint) */
    val matchSeqNum: Rep[Long] = column[Long]("match_seq_num")
    /** Database column negative_votes SqlType(int) */
    val negativeVotes: Rep[Int] = column[Int]("negative_votes")
    /** Database column positive_votes SqlType(int) */
    val positiveVotes: Rep[Int] = column[Int]("positive_votes")
    /** Database column radiant_score SqlType(int) */
    val radiantScore: Rep[Int] = column[Int]("radiant_score")
    /** Database column radiant_team_id SqlType(int) */
    val radiantTeamId: Rep[Int] = column[Int]("radiant_team_id")
    /** Database column skill SqlType(int) */
    val skill: Rep[Option[Int]] = column[Option[Int]]("skill")
    /** Database column start_time SqlType(int) */
    val startTime: Rep[Int] = column[Int]("start_time")
    /** Database column tower_status_dire SqlType(int) */
    val towerStatusDire: Rep[Int] = column[Int]("tower_status_dire")
    /** Database column tower_status_radiant SqlType(int) */
    val towerStatusRadiant: Rep[Int] = column[Int]("tower_status_radiant")
    /** Database column version SqlType(int) */
    val version: Rep[Int] = column[Int]("version")
    /** Database column replay_salt SqlType(int) */
    val replaySalt: Rep[Int] = column[Int]("replay_salt")
    /** Database column series_id SqlType(int) */
    val seriesId: Rep[Int] = column[Int]("series_id")
    /** Database column series_type SqlType(int) */
    val seriesType: Rep[Int] = column[Int]("series_type")
    /** Database column patch SqlType(int) */
    val patch: Rep[Int] = column[Int]("patch")
    /** Database column region SqlType(int) */
    val region: Rep[Int] = column[Int]("region")
    /** Database column comeback SqlType(int) */
    val comeback: Rep[Int] = column[Int]("comeback")
    /** Database column stomp SqlType(int) */
    val stomp: Rep[Int] = column[Int]("stomp")
    /** Database column replay_url SqlType(varchar), Length(255,true) */
    val replayUrl: Rep[String] = column[String]("replay_url", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table Match */
  lazy val Match = new TableQuery(tag => new Match(tag))
}
