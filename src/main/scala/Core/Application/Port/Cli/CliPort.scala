package Core.Application.Port.Cli

trait CliPort {
    def CollectProData()
    def CollectPromatchesFromOpenDota()
    def CollectProPlayersFromOpenDota()
    def CollectMatch(matchId: Long)
    def GetProMatchesFromDb()
    def RegenerateDbMappings()
    def CollectProMatchesInDb()
}
