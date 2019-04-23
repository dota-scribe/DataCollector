package Mocks

import Infrastructure.Adapter.OpenDota.OpenDotaHttpWorkerTrait
import scala.io.Source

class MockHttpWorker extends OpenDotaHttpWorkerTrait {
    override def GetProPlayers(): String = ???

    override def GetProMatches(): String = ???

    override def GetProMatches(lessThanMatchId: String): String = ???

    override def GetMatch(matchId: Long): String = {
        val filePointer = getClass.getResource("/matchSample" + matchId + ".json")
        Source.fromFile(filePointer.getPath()).mkString
    }
}
