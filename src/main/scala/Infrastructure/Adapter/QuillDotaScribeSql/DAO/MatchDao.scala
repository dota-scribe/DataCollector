package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class MatchDao(
    match_id: Long,
    barracks_status_dire: Int,
    barracks_status_radiant: Int,
    cluster: Int,
    dire_score: Int,
    dire_team_id: Int,
    duration: Int,
    engine: Int,
    first_blood_time: Int,
    game_mode: Int,
    human_players: Int,
    leagueid: Int,
    lobby_type: Int,
    match_seq_num: Long,
    negative_votes: Int,
    positive_votes: Int,
    radiant_score: Int,
    radiant_team_id: Int,
    skill: Option[Int],
    start_time: Int,
    tower_status_dire: Int,
    tower_status_radiant: Int,
    version: Int,
    replay_salt: Int,
    series_id: Int,
    series_type: Int,
    patch: Int,
    region: Int,
    comeback: Int,
    stomp: Int,
    replay_url: String,
)