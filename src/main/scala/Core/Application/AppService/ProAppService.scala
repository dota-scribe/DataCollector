package Core.Application.AppService

import Core.Application.Port.DatDota.DatDotaPort
import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.OpenDotaPort

class ProAppService(openDotaPort: OpenDotaPort, datDotaPort: DatDotaPort, repository: DotaScribeRepositoryPort) {
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

    def CollectProMatchesInDb(): Unit = {
        val proMatches = repository.GetProMatchesWithoutMatchData()

        proMatches.map(proMatch => {
            try {
                println("Loading match data for matchId:" + proMatch.match_id)
                val matchData = openDotaPort.GetMatch(proMatch.match_id)
                repository.SaveMatch(matchData)
            } catch {
                case _ : Throwable => println("Error Decoding Match :" + proMatch.match_id)
            }
        })
    }

    def CollectPremiumMatchesInDb(): Unit = {
        val premiumMatches = repository.GetPremiumMatchesWithoutMatchData()

        premiumMatches.map(preMatch => {
            try {
                println("Loading match data for matchId:" + preMatch.matchId)
                val matchData = openDotaPort.GetMatch(preMatch.matchId)
                repository.SaveMatch(matchData)
            } catch {
                case _ : Throwable => println("Error Decoding Match :" + preMatch.matchId)
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

    def CollectPremiumMatches(): Unit = {
        val premiumMatches = datDotaPort.GetPremiumMatches()
        repository.SavePremiumMatches(premiumMatches)
    }
}
