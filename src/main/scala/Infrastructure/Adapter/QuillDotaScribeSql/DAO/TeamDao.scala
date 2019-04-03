package Core.Application.Port.OpenDota.Model

case class TeamDao(
    team_id: Int,
    name: String,
    tag: String,
    logo_url: String
)