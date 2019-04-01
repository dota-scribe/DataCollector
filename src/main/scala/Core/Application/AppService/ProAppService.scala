package Core.Application.AppService

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.OpenDotaPort

class ProAppService(openDotaPort: OpenDotaPort, repository: DotaScribeRepositoryPort) {
    def GetProPlayers(): Unit ={
        val proPlayers = openDotaPort.GetProPlayers()
        repository.SaveProPlayers(proPlayers)
    }

    def GetProMatches(): Unit = {
        val proMatches = openDotaPort.GetProMatches()
        repository.SaveProMatches(proMatches)
    }

    def GetProMatches(lessThanMatchId: String): Unit = {
        val proMatches = openDotaPort.GetProMatches(lessThanMatchId)
        repository.SaveProMatches(proMatches)
    }
}
