package Core.Application.Port.OpenDota.Model

case class PickBans(
     is_pick: Boolean,
     hero_id: Int,
     team: Int,
     order: Int,
     ord: Int,
     match_id: Long
)
