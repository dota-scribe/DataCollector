package Core.Application.Port.OpenDota.Model

case class Player (
    match_id: Long,
    player_slot: Int,
    ability_targets: Map[String, Map[String, Int]],
    ability_upgrades_arr: List[Int],
    ability_uses: Map[String, Int],
    account_id: Long,
    actions: Map[Int, Int],
    additional_units: Option[String],
    assists: Int,
    backpack_0: Int,
    backpack_1: Int,
    backpack_2: Int,
    buyback_log: List[BuyBackLog],
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
    gold_reasons: Map[Int, Double],
    gold_spent: Int,
    gold_t: List[Int]
)
