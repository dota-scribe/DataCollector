package Core.Application.Port.OpenDota.Model

case class Match (
    match_id: Long,
    barracks_status_dire: Int,
    barracks_status_radiant: Int,
    chat: List[Chat],
    cluster: Int,
    cosmetics: Map[String, Int], // TODO
    dire_score: Int,
    dire_team_id: Int,
    draft_timings: List[DraftTiming],
    duration: Int,
    engine: Int,
    first_blood_time: Int,
    game_mode: Int,
    human_players: Int,
    leagueid: Int,
    lobby_type: Int,
    match_seq_num: Long,
    negative_votes: Int,
//    objectives: List[Objective], // TODO
    picks_bans: List[PickBan],
    positive_votes: Int,
    radiant_gold_adv: List[Int],
    radiant_score: Int,
    radiant_team_id: Int,
    radiant_win: Boolean,
    radiant_xp_adv: List[Int],
    skill: Option[Int],
    start_time: Int,
    teamfights: List[TeamFights], // TODO
    tower_status_dire: Int,
    tower_status_radiant: Int,
    version: Int,
    replay_salt: Int,
    series_id: Int,
    series_type: Int,
    league: League,
    radiant_team: Team,
    dire_team: Team,
    players: List[Player], // TODO
    patch: Int,
    region: Int,
//    all_word_counts: Map[String, Int],
//    my_word_counts: Map[String, Int],
    comeback: Int,
    stomp: Int,
    replay_url: String
)