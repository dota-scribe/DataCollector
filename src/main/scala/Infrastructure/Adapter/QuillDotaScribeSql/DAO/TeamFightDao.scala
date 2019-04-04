package Core.Application.Port.OpenDota.Model

case class TeamFightDao(
    teamfight_id: Long,
    match_id: Long,
    start: Int,
    end: Int,
    last_death: Int,
    deaths: Int
)
