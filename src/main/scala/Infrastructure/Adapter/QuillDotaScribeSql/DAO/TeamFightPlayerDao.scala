package Core.Application.Port.OpenDota.Model

case class TeamFightPlayerDao(
    teamfight_player_id: Long,
    teamfight_id: Long,
    deaths: Int,
    buybacks: Int,
    damage: Int,
    healing: Int,
    gold_delta: Int,
    xp_delta: Int,
    xp_start: Int,
    xp_end: Int
)