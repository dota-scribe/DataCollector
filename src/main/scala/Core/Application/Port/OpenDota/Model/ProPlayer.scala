package Core.Application.Port.OpenDota.Model

case class ProPlayer (
    account_id: Double,
    steamid: String,
    avatar: String,
    avatarmedium: String,
    avatarfull: String,
    profileurl: String,
    personaname: String,
    last_login: Option[String],
    full_history_time: Option[String],
    cheese: Double,
    fh_unavailable: Option[Boolean],
    loccountrycode: Option[String],
    last_match_time: String,
    plus: Option[Boolean],
    name: String,
    country_code: String,
    fantasy_role: Double,
    team_id: Double,
    team_name: Option[String],
    team_tag: Option[String],
    is_locked: Boolean,
    is_pro: Boolean,
    locked_until: Option[String]
)