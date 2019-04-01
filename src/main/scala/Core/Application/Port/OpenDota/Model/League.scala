package Core.Application.Port.OpenDota.Model

case class League(
    leagueid: Int,
    ticket: Option[String],
    banner: Option[String],
    tier: String,
    name: String
)
