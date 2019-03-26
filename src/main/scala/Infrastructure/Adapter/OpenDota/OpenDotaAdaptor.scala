package Infrastructure.Adapter.OpenDota

import Core.Application.Port.OpenDota.Model.ProPlayer
import Core.Application.Port.OpenDota.OpenDotaPort
import scalaj.http._
import io.circe.parser
import io.circe.Error
import io.circe.generic.semiauto.deriveDecoder

class OpenDotaAdaptor extends OpenDotaPort {
    override def GetProPlayers(): List[ProPlayer] = {
        val response: HttpResponse[String] = Http("https://api.opendota.com/api/proPlayers")
            .header("Content-Type", "application/json")
            .asString
            .throwError

        // TODO: Use DTO Object
        implicit val proPlayerDecoder = deriveDecoder[ProPlayer]

        val decodeResult : Either[Error, List[ProPlayer]] = parser.decode[List[ProPlayer]](response.body)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProPlayers JSON Response.")})
    }

    def GetProMatches() : String =  {
        val response: HttpResponse[String] = Http("https://api.opendota.com/api/proMatches")
            .header("Content-Type", "application/json")
            .asString
            .throwError

        implicit val studentDecoder = deriveDecoder[ProPlayer]

        val decodeResult: Either[Error, List[ProPlayer]] = parser.decode[List[ProPlayer]](response.body)

        return response.body
    }

    def GetProMatches(lessThanMatchId: String ) : String = {
        return Http("https://api.opendota.com/api/proMatches")
            .header("Content-Type", "application/json")
            .param("less_than_match_id", lessThanMatchId)
            .asString
            .throwError
            .body
    }
}
