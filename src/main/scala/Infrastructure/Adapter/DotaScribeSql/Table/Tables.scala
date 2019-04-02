package Infrastructure.Adapter.DotaScribeSql.Table
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.SQLServerProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.)
    Each generated XXXXTable trait is mixed in this trait hence allowing access to all the TableQuery lazy vals.
  */
trait Tables extends ProplayerTable with DrafttimingTable with TeamfightplayerTable with MatchTable with RadiantgoldadvantageTable with PickbanTable with TeamfightTable with RadiantteamTable with ChatTable with PromatchTable with RadiantxpadvantageTable with TeamfightdeathpositionTable with DireteamTable with LeagueTable {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Chat.schema, Direteam.schema, Drafttiming.schema, League.schema, Match.schema, Pickban.schema, Promatch.schema, Proplayer.schema, Radiantgoldadvantage.schema, Radiantteam.schema, Radiantxpadvantage.schema, Teamfight.schema, Teamfightdeathposition.schema, Teamfightplayer.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

}
