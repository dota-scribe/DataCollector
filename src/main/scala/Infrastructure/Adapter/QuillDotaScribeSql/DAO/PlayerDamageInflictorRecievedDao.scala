package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class PlayerDamageInflictorRecievedDao (
    player_id: Long,
    source_ability: String,
    value: Int
)
