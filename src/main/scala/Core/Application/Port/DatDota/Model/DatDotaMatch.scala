package Core.Application.Port.DatDota.Model

case class DatDotaMatchRoot (
    data: List[DatDotaMatch]
)

case class DatDotaMatch (
    matchId: Long,
    seriesId: Option[Long],
//    league: League,
//    radiant: Radiant,
//    dire: Radiant,
//    radiantPlayers: List[RadiantPlayers],
//    direPlayers: List[RadiantPlayers],
    startDate: Double,
    duration: Double,
    radiantVictory: Boolean
)
