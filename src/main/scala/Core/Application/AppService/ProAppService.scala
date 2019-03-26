package Core.Application.AppService

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.OpenDotaPort

class ProAppService(openDotaPort: OpenDotaPort, repository: DotaScribeRepositoryPort) {
    def GetProPlayers(): Unit ={
        val proPlayers = openDotaPort.GetProPlayers()
        repository.SaveProPlayers(proPlayers)
    }
}
