package Core.Application.Port.OpenDota.Model

case class DraftTimingDao(
    match_id: Long,
    order: Int,
    pick: Boolean,
    active_team: Int,
    hero_id: Int,
    player_slot: Option[Int],
    extra_time: Int,
    total_time_taken: Int
)
