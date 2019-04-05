package Core.Application.Port.OpenDota.Model

case class TeamFightPlayer(
    deaths_pos: Map[Int, Map[Int, Int]],
    ability_uses: Map[String, Int],
    ability_targets: Map[String, Map[String, Int]],
    item_uses: Map[String, Int],
    killed: Map[String, Int],
    deaths: Int,
    buybacks: Int,
    damage: Int,
    healing: Int,
    gold_delta: Int,
    xp_delta: Int,
    xp_start: Int,
    xp_end: Int
)