package Core.Application.Port.OpenDota.Model

case class ProMatchDao(
    match_id: Long,
    duration: Long,
    start_time: Long,
    radiant_team_id: Option[Long],
    radiant_name: Option[String],
    dire_team_id: Option[Long],
    dire_name: Option[String],
    leagueid: Long,
    league_name: String,
    series_id: Long,
    series_type: Long,
    radiant_score: Long,
    dire_score: Long,
    radiant_win: Boolean
)