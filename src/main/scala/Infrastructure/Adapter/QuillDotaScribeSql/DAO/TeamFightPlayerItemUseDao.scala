package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class TeamFightPlayerItemUseDao(
    teamfight_player_id: Long,
    item: String,
    count: Int
)