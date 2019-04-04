package Core.Application.Port.OpenDota.Model

case class ChatDao (
    match_id: Long,
    time: Double,
    unit: Option[String],
    key: String,
    slot: Int,
    player_slot: Int
)