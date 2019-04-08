package Core.Application.CommandHandler

import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.Port.Cli.CliPort

class CliCommandHandler(proAppService: ProAppService, dotaScribeDbService: DotaScribeDbService, matchAppService: MatchAppService) extends CliPort {
    override def CollectProData(): Unit = {
        proAppService.CollectProPlayersFromOpenDota()
        proAppService.GetProMatches()
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

    override def GetProMatchesFromDb(): Unit = {
        proAppService.GetProMatches()
    }

    override def CollectProMatchesInDb(): Unit = {
        proAppService.CollectProMatchesInDb()
    }
}
