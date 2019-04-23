package Core.Application.Port.Cli

trait CliPort {
    def CollectPromatchesFromOpenDota(matchId: Long)
    def CollectProData()
    def CollectPromatchesFromOpenDota()
    def CollectProPlayersFromOpenDota()
    def CollectMatch(matchId: Long)
    def CollectProMatchesInDb()
    def LoadProMatchBatch(numBatches: Int)
    def CollectPremiumMatches()
    def CollectPremiumMatchesInDb()
}
