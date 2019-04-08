package Core.Application.AppService

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.OpenDotaPort

class ProAppService(openDotaPort: OpenDotaPort, repository: DotaScribeRepositoryPort) {
    def CollectProPlayersFromOpenDota(): Unit ={
        val proPlayers = openDotaPort.GetProPlayers()
        repository.SaveProPlayers(proPlayers)
    }

    def CollectProMatchesFromOpenDota(): Unit = {
        val proMatches = openDotaPort.GetProMatches()
        repository.SaveProMatches(proMatches)
    }

    def GetProMatches(lessThanMatchId: String): Unit = {
        val proMatches = openDotaPort.GetProMatches(lessThanMatchId)
        repository.SaveProMatches(proMatches)
    }

    def GetProMatches(): Unit = {
        repository.GetProMatches()
    }

    def CollectProMatchesInDb(): Unit = {
        val proMatches = repository.GetProMatches()

        proMatches.map(proMatch => {
            val matchData = openDotaPort.GetMatch(proMatch.match_id)
            repository.SaveMatch(matchData)
        })
    }
}
