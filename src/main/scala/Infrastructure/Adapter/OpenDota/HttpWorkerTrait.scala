package Infrastructure.Adapter.OpenDota

trait HttpWorkerTrait {
    def GetProPlayers(): String
    def GetProMatches(): String
    def GetProMatches(lessThanMatchId: String): String
    def GetMatch(matchId: Long): String
}