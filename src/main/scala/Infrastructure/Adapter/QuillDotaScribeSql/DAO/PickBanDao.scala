package Core.Application.Port.OpenDota.Model

case class PickBanDao(
    match_id: Long,
    is_pick: Boolean,
    hero_id: Int,
    team: Int,
    order: Int,
    ord: Int,
)
