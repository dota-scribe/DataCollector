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

class OpenDotaAdaptor(httpWorker: HttpWorkerTrait) extends OpenDotaPort {
    override def GetProPlayers(): List[ProPlayer] = {
        val response = httpWorker.GetProPlayers()

        implicit val proPlayerDecoder = deriveDecoder[ProPlayer]

        val decodeResult : Either[Error, List[ProPlayer]] = parser.decode[List[ProPlayer]](response)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProPlayers JSON Response.")})
    }

    def GetProMatches() : List[ProMatch] =  {
        val response = httpWorker.GetProMatches()

        implicit val proMatchDecoder = deriveDecoder[ProMatch]

        val decodeResult: Either[Error, List[ProMatch]] = parser.decode[List[ProMatch]](response)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProMatch JSON Response.")})
    }

    def GetProMatches(lessThanMatchId: String) : List[ProMatch] = {
        val response = httpWorker.GetProMatches(lessThanMatchId)

        implicit val proMatchDecoder = deriveDecoder[ProMatch]

        val decodeResult: Either[Error, List[ProMatch]] = parser.decode[List[ProMatch]](response)

        return decodeResult.getOrElse({throw new Exception("Error decoding ProMatch JSON Response.")})
    }

    def GetMatch(matchId: Long) : Match = {
        val response = httpWorker.GetMatch(matchId)

//        implicit val matchDecoder = deriveDecoder[Match]

        // Custom decoder to handle type Either[String, Int]
        implicit def matchDecoder[A,B](implicit a: Decoder[A], b: Decoder[B]): Decoder[Either[A,B]] = {
            val left: Decoder[Either[A,B]]= a.map(Left.apply)
            val right: Decoder[Either[A,B]]= b.map(Right.apply)
            left or right
        }

        val decodeResult = parse(response).flatMap(_.as[Match])

        return decodeResult.getOrElse({throw new Exception("Error decoding Match JSON Response")})
    }
}
