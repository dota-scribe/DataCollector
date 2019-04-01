package Core.Application.Port.OpenDota.Model

case class BuyBackLog(
    time: Int,
    slot: Int,
    `type`: String,
    player_slot: Int
)
