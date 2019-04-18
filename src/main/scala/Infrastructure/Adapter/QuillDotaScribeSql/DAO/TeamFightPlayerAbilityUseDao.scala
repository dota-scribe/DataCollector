package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class TeamFightPlayerAbilityUseDao(
    teamfight_player_id: Long,
    ability: String,
    count: Int
)