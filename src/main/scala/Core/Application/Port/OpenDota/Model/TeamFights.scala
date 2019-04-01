package Core.Application.Port.OpenDota.Model

case class TeamFights(
    start: Int,
    end: Int,
    last_death: Int,
    deaths: Int,
    players: List[TeamFightPlayer]
)
