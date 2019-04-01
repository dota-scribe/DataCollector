package Core.Application.Port.OpenDota.Model

case class Objective (
    time: Int,
    `type`: String,
    Unit: Option[String],
    slot: Option[Int],
    key: Option[String],
    player_slot: Option[Int],
    team: Option[Int]
)