package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class TeamFightPlayerAbilityTargetDao(
    teamfight_player_id: Long,
    ability: String,
    target: String,
    count: Int
)
