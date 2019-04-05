package Core.Application.Port.OpenDota.Model

case class LeagueDao(
    match_id: Long,
    leagueid: Int,
    ticket: Option[String],
    banner: Option[String],
    tier: String,
    name: String
)
