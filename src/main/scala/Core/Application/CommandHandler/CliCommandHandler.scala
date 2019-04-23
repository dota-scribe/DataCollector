package Core.Application.CommandHandler

import Core.Application.AppService.{MatchAppService, ProAppService}
import Core.Application.Port.Cli.CliPort

class CliCommandHandler(proAppService: ProAppService, matchAppService: MatchAppService) extends CliPort {
    override def CollectProData(): Unit = {
        proAppService.LoadProPlayerData()
        proAppService.LoadProMatchData()
    }

    override def CollectPromatchesFromOpenDota(): Unit = {
        proAppService.LoadProMatchData()
    }

    override def CollectProPlayersFromOpenDota(): Unit = {
        proAppService.LoadProPlayerData()
    }

    override def CollectMatch(matchId: Long): Unit = {
        matchAppService.GetMatch(matchId)
    }

    override def CollectProMatchesInDb(): Unit = {
        proAppService.CollectProMatchesInDb()
    }

    override def CollectPromatchesFromOpenDota(matchId: Long): Unit = {
        proAppService.LoadProMatchData(matchId)
    }

    override def LoadProMatchBatch(numBatches: Int): Unit = {
        proAppService.LoadProMatchBatch(numBatches)
    }

    override def CollectPremiumMatches(): Unit = {
        proAppService.CollectPremiumMatches()
    }

    override def CollectPremiumMatchesInDb(): Unit = {
        proAppService.CollectPremiumMatchesInDb()
    }
}
