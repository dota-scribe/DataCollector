package Core.Application.CommandHandler

import Core.Application.AppService.ProAppService
import Core.Application.Port.Cli.CliPort

// TODO: Inject ProAppService
class CliCommandHandler(proAppService: ProAppService) extends CliPort {
    override def CollectProData(): Unit = {
        proAppService.GetProPlayers()
        // Get Pro Matches...
    }
}
