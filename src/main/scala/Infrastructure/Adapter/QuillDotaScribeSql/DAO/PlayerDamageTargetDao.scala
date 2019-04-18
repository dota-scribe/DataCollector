package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class PlayerDamageTargetDao (
    player_id: Long,
    source: String,
    target: String,
    value: Int
)
