package Core.Application.CommandHandler

import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.Port.Cli.CliPort

class CliCommandHandler(proAppService: ProAppService, dotaScribeDbService: DotaScribeDbService, matchAppService: MatchAppService) extends CliPort {
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

    override def RegenerateDbMappings(): Unit = {
        dotaScribeDbService.RegenerateDbMappings()
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
}
