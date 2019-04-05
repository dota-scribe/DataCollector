package Core.Application.Port.OpenDota.Model

case class Objective (
    time: Int,
    `type`: String,
    unit: Option[String],
    slot: Option[Int],
    key: Option[Either[String, Int]],
    player_slot: Option[Int],
    team: Option[Int]
)