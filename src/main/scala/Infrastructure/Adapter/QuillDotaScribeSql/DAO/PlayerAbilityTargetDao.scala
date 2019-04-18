package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class PlayerAbilityTargetDao (
    player_id: Long,
    ability: String,
    target: String,
    count: Int
)

