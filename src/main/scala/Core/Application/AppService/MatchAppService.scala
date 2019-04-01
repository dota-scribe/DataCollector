package Core.Application.AppService

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.OpenDotaPort

class MatchAppService(openDotaPort: OpenDotaPort, dotaScribeRepositoryPort: DotaScribeRepositoryPort) {
    def GetMatch(matchId: Long): Unit = {
        val matchData = openDotaPort.GetMatch(matchId)
        dotaScribeRepositoryPort.SaveMatch(matchData)
    }
}
