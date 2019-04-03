package Core.Application.Port.OpenDota.Model

case class BuyBackLogDao(
    time: Int,
    slot: Int,
    `type`: String,
    player_slot: Int
)
