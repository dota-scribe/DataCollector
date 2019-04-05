package Core.Application.Port.OpenDota.Model

case class Chat (
    time: Double,
    unit: Option[String],
    key: String,
    slot: Int,
    player_slot: Option[Int]
)