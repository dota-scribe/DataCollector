package Core.Application.CommandHandler

import Core.Application.AppService.{DotaScribeDbService, MatchAppService, ProAppService}
import Core.Application.Port.Cli.CliPort

class CliCommandHandler(proAppService: ProAppService, dotaScribeDbService: DotaScribeDbService, matchAppService: MatchAppService) extends CliPort {
    override def CollectProData(): Unit = {
        proAppService.GetProPlayers()
        proAppService.GetProMatches()
        // Get Pro Matches...
    }

    override def RegenerateDbMappings(): Unit = {
        dotaScribeDbService.RegenerateDbMappings()
    }

    override def GetProMatches(): Unit = {
        proAppService.GetProMatches()
    }

    override def GetProPlayers(): Unit = {
        proAppService.GetProPlayers()
    }

    override def GetMatch(matchId: Long): Unit = {
        matchAppService.GetMatch(matchId)
    }
}
