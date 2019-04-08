package Core.Application.Port.OpenDota.Model

case class TeamDao(
    match_id: Long,
    team_id: Long,
    name: String,
    tag: String,
    logo_url: Option[String]
)