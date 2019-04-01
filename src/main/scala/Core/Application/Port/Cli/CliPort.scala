package Core.Application.Port.Cli

trait CliPort {
    def CollectProData()
    def GetProMatches()
    def GetProPlayers()
    def GetMatch(matchId: Long)
    def RegenerateDbMappings()
}
