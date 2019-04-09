package Core.Application.AppService

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.OpenDotaPort

class ProAppService(openDotaPort: OpenDotaPort, repository: DotaScribeRepositoryPort) {
    def LoadProPlayerData(): Unit ={
        val proPlayers = openDotaPort.GetProPlayers()
        repository.SaveProPlayers(proPlayers)
    }

    def LoadProMatchData(): Unit = {
        val proMatches = openDotaPort.GetProMatches()
        repository.SaveProMatches(proMatches)
    }

    def LoadProMatchData(matchId: Long): Unit = {
        val proMatches = openDotaPort.GetProMatches(matchId)
        repository.SaveProMatches(proMatches)
    }

    def SyncProMatchDataWithMatches(): Unit = {
        val proMatches = repository.GetProMatchesWithoutMatchData()

        proMatches.map(proMatch => {
            try {
                val matchData = openDotaPort.GetMatch(proMatch.match_id)
                repository.SaveMatch(matchData)
            } catch {
                case _ : Throwable => println("Error Decoding Match :" + proMatch.match_id)
            }
        })
    }

    def LoadProMatchBatch(numBatches: Int) {
        for( i <- 0 until numBatches){
            val minMatchId = repository.GetMinProMatchId()

            val proMatches = if (minMatchId.isEmpty) openDotaPort.GetProMatches() else openDotaPort.GetProMatches(minMatchId.getOrElse(0))

            try {
                repository.SaveProMatches(proMatches)
            } catch {
                case _ : Throwable => println("error saving batch")
            }
        }
    }
}
