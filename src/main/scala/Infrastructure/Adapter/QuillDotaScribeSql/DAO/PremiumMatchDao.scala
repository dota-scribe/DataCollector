package Infrastructure.Adapter.QuillDotaScribeSql.DAO

case class PremiumMatchDao (
    match_id: Long,
    series_id: Option[Long],
    startDate: Double,
    duration: Double,
    radiantVictory: Boolean
)
