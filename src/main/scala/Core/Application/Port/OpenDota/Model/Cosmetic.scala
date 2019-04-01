package Core.Application.Port.OpenDota.Model

case class Cosmetic (
    item_id: Long,
    name: String,
    prefab: String,
    creation_date: String,
    image_inventory: String,
    image_path: String,
    item_description: String,
    item_name: String,
    item_rarity: String,
    item_type_name: String,
    used_by_heroes: String
)
