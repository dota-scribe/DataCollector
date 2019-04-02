package Core.Application.Port.OpenDota.Model

case class PickBan(
    match_id: Long,
    is_pick: Boolean,
    hero_id: Int,
    team: Int,
    order: Int,
    ord: Int,
)
