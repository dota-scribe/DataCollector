package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model for table Proplayer
trait ProplayerTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Proplayer
   *  @param accountId Database column account_id SqlType(int)
   *  @param steamid Database column steamid SqlType(varchar), Length(255,true)
   *  @param avatar Database column avatar SqlType(varchar), Length(255,true)
   *  @param avatarmedium Database column avatarmedium SqlType(varchar), Length(255,true)
   *  @param avatarfull Database column avatarfull SqlType(varchar), Length(255,true)
   *  @param profileurl Database column profileurl SqlType(varchar), Length(255,true)
   *  @param personaname Database column personaname SqlType(varchar), Length(255,true)
   *  @param lastLogin Database column last_login SqlType(varchar), Length(255,true)
   *  @param fullHistoryTime Database column full_history_time SqlType(varchar), Length(255,true)
   *  @param cheese Database column cheese SqlType(int)
   *  @param fhUnavailable Database column fh_unavailable SqlType(bit)
   *  @param loccountrycode Database column loccountrycode SqlType(varchar), Length(255,true)
   *  @param lastMatchTime Database column last_match_time SqlType(varchar), Length(255,true)
   *  @param plus Database column plus SqlType(bit)
   *  @param name Database column name SqlType(varchar), Length(255,true)
   *  @param countryCode Database column country_code SqlType(varchar), Length(255,true)
   *  @param fantasyRole Database column fantasy_role SqlType(int)
   *  @param teamId Database column team_id SqlType(int)
   *  @param teamName Database column team_name SqlType(varchar), Length(255,true)
   *  @param teamTag Database column team_tag SqlType(varchar), Length(255,true)
   *  @param isLocked Database column is_locked SqlType(bit)
   *  @param isPro Database column is_pro SqlType(bit)
   *  @param lockedUntil Database column locked_until SqlType(varchar), Length(255,true) */
  case class ProplayerRow(accountId: Int, steamid: String, avatar: String, avatarmedium: String, avatarfull: String, profileurl: String, personaname: String, lastLogin: Option[String], fullHistoryTime: Option[String], cheese: Int, fhUnavailable: Option[Boolean], loccountrycode: Option[String], lastMatchTime: String, plus: Option[Boolean], name: String, countryCode: String, fantasyRole: Int, teamId: Int, teamName: Option[String], teamTag: Option[String], isLocked: Boolean, isPro: Boolean, lockedUntil: Option[String])
  /** GetResult implicit for fetching ProplayerRow objects using plain SQL queries */
  implicit def GetResultProplayerRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Boolean]], e4: GR[Boolean]): GR[ProplayerRow] = GR{
    prs => import prs._
    ProplayerRow(<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<?[String], <<?[String], <<[Int], <<?[Boolean], <<?[String], <<[String], <<?[Boolean], <<[String], <<[String], <<[Int], <<[Int], <<?[String], <<?[String], <<[Boolean], <<[Boolean], <<?[String])
  }
  /** Table description of table ProPlayer. Objects of this class serve as prototypes for rows in queries. */
  class Proplayer(_tableTag: Tag) extends profile.api.Table[ProplayerRow](_tableTag, Some("dbo"), "ProPlayer") {
    def * = (accountId :: steamid :: avatar :: avatarmedium :: avatarfull :: profileurl :: personaname :: lastLogin :: fullHistoryTime :: cheese :: fhUnavailable :: loccountrycode :: lastMatchTime :: plus :: name :: countryCode :: fantasyRole :: teamId :: teamName :: teamTag :: isLocked :: isPro :: lockedUntil :: HNil).mapTo[ProplayerRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(accountId) :: Rep.Some(steamid) :: Rep.Some(avatar) :: Rep.Some(avatarmedium) :: Rep.Some(avatarfull) :: Rep.Some(profileurl) :: Rep.Some(personaname) :: lastLogin :: fullHistoryTime :: Rep.Some(cheese) :: fhUnavailable :: loccountrycode :: Rep.Some(lastMatchTime) :: plus :: Rep.Some(name) :: Rep.Some(countryCode) :: Rep.Some(fantasyRole) :: Rep.Some(teamId) :: teamName :: teamTag :: Rep.Some(isLocked) :: Rep.Some(isPro) :: lockedUntil :: HNil).shaped.<>(r => ProplayerRow(r(0).asInstanceOf[Option[Int]].get, r(1).asInstanceOf[Option[String]].get, r(2).asInstanceOf[Option[String]].get, r(3).asInstanceOf[Option[String]].get, r(4).asInstanceOf[Option[String]].get, r(5).asInstanceOf[Option[String]].get, r(6).asInstanceOf[Option[String]].get, r(7).asInstanceOf[Option[String]], r(8).asInstanceOf[Option[String]], r(9).asInstanceOf[Option[Int]].get, r(10).asInstanceOf[Option[Boolean]], r(11).asInstanceOf[Option[String]], r(12).asInstanceOf[Option[String]].get, r(13).asInstanceOf[Option[Boolean]], r(14).asInstanceOf[Option[String]].get, r(15).asInstanceOf[Option[String]].get, r(16).asInstanceOf[Option[Int]].get, r(17).asInstanceOf[Option[Int]].get, r(18).asInstanceOf[Option[String]], r(19).asInstanceOf[Option[String]], r(20).asInstanceOf[Option[Boolean]].get, r(21).asInstanceOf[Option[Boolean]].get, r(22).asInstanceOf[Option[String]]), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column account_id SqlType(int) */
    val accountId: Rep[Int] = column[Int]("account_id")
    /** Database column steamid SqlType(varchar), Length(255,true) */
    val steamid: Rep[String] = column[String]("steamid", O.Length(255,varying=true))
    /** Database column avatar SqlType(varchar), Length(255,true) */
    val avatar: Rep[String] = column[String]("avatar", O.Length(255,varying=true))
    /** Database column avatarmedium SqlType(varchar), Length(255,true) */
    val avatarmedium: Rep[String] = column[String]("avatarmedium", O.Length(255,varying=true))
    /** Database column avatarfull SqlType(varchar), Length(255,true) */
    val avatarfull: Rep[String] = column[String]("avatarfull", O.Length(255,varying=true))
    /** Database column profileurl SqlType(varchar), Length(255,true) */
    val profileurl: Rep[String] = column[String]("profileurl", O.Length(255,varying=true))
    /** Database column personaname SqlType(varchar), Length(255,true) */
    val personaname: Rep[String] = column[String]("personaname", O.Length(255,varying=true))
    /** Database column last_login SqlType(varchar), Length(255,true) */
    val lastLogin: Rep[Option[String]] = column[Option[String]]("last_login", O.Length(255,varying=true))
    /** Database column full_history_time SqlType(varchar), Length(255,true) */
    val fullHistoryTime: Rep[Option[String]] = column[Option[String]]("full_history_time", O.Length(255,varying=true))
    /** Database column cheese SqlType(int) */
    val cheese: Rep[Int] = column[Int]("cheese")
    /** Database column fh_unavailable SqlType(bit) */
    val fhUnavailable: Rep[Option[Boolean]] = column[Option[Boolean]]("fh_unavailable")
    /** Database column loccountrycode SqlType(varchar), Length(255,true) */
    val loccountrycode: Rep[Option[String]] = column[Option[String]]("loccountrycode", O.Length(255,varying=true))
    /** Database column last_match_time SqlType(varchar), Length(255,true) */
    val lastMatchTime: Rep[String] = column[String]("last_match_time", O.Length(255,varying=true))
    /** Database column plus SqlType(bit) */
    val plus: Rep[Option[Boolean]] = column[Option[Boolean]]("plus")
    /** Database column name SqlType(varchar), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column country_code SqlType(varchar), Length(255,true) */
    val countryCode: Rep[String] = column[String]("country_code", O.Length(255,varying=true))
    /** Database column fantasy_role SqlType(int) */
    val fantasyRole: Rep[Int] = column[Int]("fantasy_role")
    /** Database column team_id SqlType(int) */
    val teamId: Rep[Int] = column[Int]("team_id")
    /** Database column team_name SqlType(varchar), Length(255,true) */
    val teamName: Rep[Option[String]] = column[Option[String]]("team_name", O.Length(255,varying=true))
    /** Database column team_tag SqlType(varchar), Length(255,true) */
    val teamTag: Rep[Option[String]] = column[Option[String]]("team_tag", O.Length(255,varying=true))
    /** Database column is_locked SqlType(bit) */
    val isLocked: Rep[Boolean] = column[Boolean]("is_locked")
    /** Database column is_pro SqlType(bit) */
    val isPro: Rep[Boolean] = column[Boolean]("is_pro")
    /** Database column locked_until SqlType(varchar), Length(255,true) */
    val lockedUntil: Rep[Option[String]] = column[Option[String]]("locked_until", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table Proplayer */
  lazy val Proplayer = new TableQuery(tag => new Proplayer(tag))
}
