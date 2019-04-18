package Core.Application.Port.OpenDota.Model

// TODO - ancillary tables to contain keys to join back to base player table: match_id, player_slot
// TODO - priority: lh_t, obs_log, purchase_log, runes, sen_log
case class Player(
    match_id: Long,
    player_slot: Int,
    ability_targets: Map[String, Map[String, Int]],
    ability_upgrades_arr: List[Int], // TODO [level(array_index), ability_id(array_value)]
    ability_uses: Map[String, Int], //
    account_id: Long,
    actions: Map[Int, Int],
    //additional_units: Option[String],  TODO [columns=headers, values=data] 1 row per list element
    assists: Int,
    backpack_0: Int,
    backpack_1: Int,
    backpack_2: Int,
    buyback_log: List[BuyBackLog], // TODO [columns=headers, values=data] 1 row per list element
    camps_stacked: Int,

    // connection_log,

    creeps_stacked: Int,
    damage: Map[String, Int],
    damage_inflictor: Map[String, Int],
    damage_inflictor_received: Map[String, Int],
    damage_taken: Map[String, Int],
    damage_targets: Map[String, Map[String, Int]],
    deaths: Int,
    denies: Int,
    dn_t: List[Int],
    firstblood_claimed: Int,
    gold: Int,
    gold_per_min: Int,
    gold_reasons: Map[Int, Double], // TODO [source_id(header), value(data)]
    gold_spent: Int,
    gold_t: List[Int],

    // End of by hand
    hero_damage: Int,
    hero_healing: Int,
    // TODO hero_hits: Map[String, Int], [source(header), hits(data)]
    hero_id: Int,
    item_0: Int,
    item_1: Int,
    item_2: Int,
    item_3: Int,
    item_4: Int,
    item_5: Int,
    // TODO item_uses: Map[String, Int], [item(header), uses(data)]
    // TODO kill_streaks: Map[Int, Int], [length(header), occurences(data)]
    // TODO killed: Map[String, Int], [unit(header), value(data)]
    // TODO killed_by: Map[String, Int], [unit(header), value(data)]
    kills_log: List[PlayerKillLog],
    lane_pos: Map[Int, Map[Int, Int]],
    kills: Int,
    last_hits: Int,
    leaver_status: Int,
    level: Int,
    // TODO lh_t: List[Int], if this could be rolled up by distinct count it would be easier to store
    // TODO multi_kills: [multiple(header), occurences(data)]
    // TODO obs_log List[Map[String...]], [time, x, y]
    // TODO purchase_log: List[Map[String, Int]], [item(key), time(time)]
    obs_placed: Int,
    party_id: Int,
    party_size: Int,
    performance_others: Option[String],
    permanent_buffs: Option[List[Map[String, Int]]],
    pings: Option[Int],
    pred_vict: Boolean,
    randomed: Boolean,
    repicked: Option[Boolean],
    roshans_killed: Int,
    rune_pickups: Int,
    // TODO runes: Map[String, Int], [rune_id(header), count(data)]
    // TODO sen_log List[Map[String...]], [time, x, y]
    sen_placed: Int,
    stuns: BigDecimal,
    teamfight_participation: BigDecimal,
    tower_damage: Int,
    towers_killed: Int,
    xp_per_min: Int,
    // TODO xp_reasons: Map[String, Int], [source_id(header), value(data)]
    personaname: Option[String],
    name: Option[String],
    last_login: Option[String],
    radiant_win: Boolean,
    start_time: Int,
    duration: Int,
    cluster: Int,
    lobby_type: Int,
    game_mode: Int,
    is_contributor: Boolean,
    patch: Int,
    region: Option[Int],
    isRadiant: Boolean,
    win: Int,
    lose: Int,
    total_gold: Int,
    total_xp: Int,
    kda: Int,
    abandons: Int,
    neutral_kills: Int,
    tower_kills: Int,
    courier_kills: Int,
    lane_kills: Int,
    hero_kills: Int,
    observer_kills: Int,
    sentry_kills: Int,
    roshan_kills: Int,
    necronomicon_kills: Int,
    ancient_kills: Int,
    buyback_count: Int,
    observer_uses: Int,
    sentry_uses: Int,
    lane_efficiency: BigDecimal,
    lane_efficiency_pct: Int,
    lane: Int,
    lane_role: Int,
    is_roaming: Boolean,
    purchase_tpscroll: Int,
    actions_per_min: Int,
    life_state_dead: Int,
    rank_tier: Option[Int]
)
