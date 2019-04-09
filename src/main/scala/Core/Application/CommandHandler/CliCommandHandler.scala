package Core.Application.CommandHandler

import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.Port.Cli.CliPort

class CliCommandHandler(proAppService: ProAppService, dotaScribeDbService: DotaScribeDbService, matchAppService: MatchAppService) extends CliPort {
    override def CollectProData(): Unit = {
        proAppService.CollectProPlayersFromOpenDota()
        proAppService.CollectProMatchesFromOpenDota()
    }

    override def CollectPromatchesFromOpenDota(): Unit = {
        proAppService.CollectProMatchesFromOpenDota()
    }

    override def CollectProPlayersFromOpenDota(): Unit = {
        proAppService.CollectProPlayersFromOpenDota()
    }

    override def RegenerateDbMappings(): Unit = {
        dotaScribeDbService.RegenerateDbMappings()
    }

    override def CollectMatch(matchId: Long): Unit = {
        matchAppService.GetMatch(matchId)
    }

    override def GetProMatchesFromRepository(): Unit = {
        proAppService.GetProMatchesFromRepository()
    }

    override def CollectProMatchesInDb(): Unit = {
        proAppService.CollectProMatchesInDb()
    }

    override def CollectPromatchesFromOpenDota(matchId: Long): Unit = {
        proAppService.CollectProMatchesFromOpenDota(matchId)
    }
}
