package Infrastructure.Adapter.DotaScribeSql.Table

import slick.jdbc.H2Profile.api._

class ProPlayerTable(tag: Tag) extends Table[(
        Long,
        String,
        String,
        String,
        String,
        String,
        String,
        Option[String],
        Option[String],
        Int,
        Option[String],
        String,
        Option[Boolean],
        String,
        String,
        Int,
        Int,
        Option[String],
        Option[String],
        Boolean,
        Boolean,
        Option[String]
    )](tag, "ProPlayer") {

    def account_id = column[Long]("AccountId", O.PrimaryKey)

    def steamid = column[String]("SteamId")

    def avatar = column[String]("avatar")

    def avatarmedium = column[String]("AvatarMedium")

    def avatarfull = column[String]("AvatarFull")

    def profileurl = column[String]("ProfileUrl")

    def personaname = column[String]("PersonaName")

    def last_login = column[Option[String]]("LastLogin")

    def full_history_time = column[Option[String]]("FullHistoryTime")

    def cheese = column[Int]("Cheese")

    def loccountrycode = column[Option[String]]("LocCountryCode")

    def last_match_time = column[String]("LastMatchTime")

    def plus = column[Option[Boolean]]("Plus")

    def name = column[String]("Name")

    def country_code = column[String]("CountryCode")

    def fantasy_role = column[Int]("FantasyRole")

    def team_id = column[Int]("TeamId")

    def team_name = column[Option[String]]("TeamName")

    def team_tag = column[Option[String]]("TeamTag")

    def is_locked = column[Boolean]("IsLocked")

    def is_pro = column[Boolean]("IsPro")

    def locked_until = column[Option[String]]("LockedUntil")

    def * = (
        account_id,
        steamid,
        avatar,
        avatarmedium,
        avatarfull,
        profileurl,
        personaname,
        last_login,
        full_history_time,
        cheese,
        loccountrycode,
        last_match_time,
        plus,
        name,
        country_code,
        fantasy_role,
        team_id,
        team_name,
        team_tag,
        is_locked,
        is_pro,
        locked_until
    )
}