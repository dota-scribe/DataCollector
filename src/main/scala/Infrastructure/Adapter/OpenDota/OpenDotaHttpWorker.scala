package Infrastructure.Adapter.OpenDota

import scalaj.http.Http

class OpenDotaHttpWorker extends OpenDotaHttpWorkerTrait {
    override def GetProPlayers(): String = {
        Http("https://api.opendota.com/api/proPlayers")
            .header("Content-Type", "application/json")
            .asString
            .throwError
            .body
    }

    override def GetProMatches(): String = {
        Http("https://api.opendota.com/api/proMatches")
            .header("Content-Type", "application/json")
            .asString
            .throwError
            .body
    }

    override def GetProMatches(lessThanMatchId: String): String = {
        Http("https://api.opendota.com/api/proMatches")
            .header("Content-Type", "application/json")
            .param("less_than_match_id", lessThanMatchId)
            .asString
            .throwError
            .body
    }

    override def GetMatch(matchId: Long): String = {
        Http("https://api.opendota.com/api/matches/" + matchId)
            .header("Content-Type", "application/json")
            .asString
            .throwError
            .body
    }
}
