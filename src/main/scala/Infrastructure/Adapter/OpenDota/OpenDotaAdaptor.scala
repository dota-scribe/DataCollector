package Infrastructure.Adapter.OpenDota

import Core.Application.Port.OpenDota.Model.{Chat, Match, ProMatch, ProPlayer}
import Core.Application.Port.OpenDota.OpenDotaPort
import scalaj.http._
import io.circe.parser
import io.circe.Error
import io.circe.generic.semiauto.deriveDecoder
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._
import io.circe.parser._

import scala.io.Source

class OpenDotaAdaptor extends OpenDotaPort {
    override def GetProPlayers(): List[ProPlayer] = {
        val response = Http("https://api.opendota.com/api/proPlayers")
            .header("Content-Type", "application/json")
            .asString
            .throwError

        implicit val proPlayerDecoder = deriveDecoder[ProPlayer]

        val decodeResult : Either[Error, List[ProPlayer]] = parser.decode[List[ProPlayer]](response.body)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProPlayers JSON Response.")})
    }

    def GetProMatches() : List[ProMatch] =  {
        val response = Http("https://api.opendota.com/api/proMatches")
            .header("Content-Type", "application/json")
            .asString
            .throwError

        implicit val proMatchDecoder = deriveDecoder[ProMatch]

        val decodeResult: Either[Error, List[ProMatch]] = parser.decode[List[ProMatch]](response.body)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProMatch JSON Response.")})
    }

    def GetProMatches(lessThanMatchId: String) : List[ProMatch] = {
        val response = Http("https://api.opendota.com/api/proMatches")
            .header("Content-Type", "application/json")
            .param("less_than_match_id", lessThanMatchId)
            .asString
            .throwError

        implicit val proMatchDecoder = deriveDecoder[ProMatch]

        val decodeResult: Either[Error, List[ProMatch]] = parser.decode[List[ProMatch]](response.body)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProMatch JSON Response.")})
    }

    def GetMatch(matchId: Long) : Match = {
//        val response = Http("https://api.opendota.com/api/matches/" + matchId)
//        .header("Content-Type", "application/json")
//        .asString
//        .throwError

        val filename = "C:\\Workspace\\DotaScribe\\DataCollector\\src\\main\\scala\\matchSample.json"
        val line = Source.fromFile(filename).mkString

        implicit val matchDecoder = deriveDecoder[Match]

        val decodeResult = parse(line).flatMap(_.as[Match])

        return decodeResult.getOrElse({throw new Exception("Error decoding Match JSON Response")})
    }
}
