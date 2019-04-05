package Core.Application.Port.OpenDota.Model

case class Team(
    team_id: Int,
    name: String,
    tag: String,
    logo_url: Option[String]
)