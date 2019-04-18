package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class PlayerDamageTakenDao (
    player_id: Long,
    source_unit: String,
    value: Int
)