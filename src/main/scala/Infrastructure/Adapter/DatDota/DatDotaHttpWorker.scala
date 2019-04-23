package Infrastructure.Adapter.DatDota

import scalaj.http.Http

class DatDotaHttpWorker extends DatDotaHttpWorkerTrait {
    override def GetPremiumMatches(): String = {
        Http("http://datdota.com/api/matches?tier=premium")
          .header("Content-Type", "application/json")
          .asString
          .throwError
          .body
    }
}
