package Infrastructure.Adapter.DatDota

import Core.Application.Port.DatDota.DatDotaPort
import Core.Application.Port.DatDota.Model.{DatDotaMatch, DatDotaMatchRoot}
import io.circe.parser
import io.circe.Error
import io.circe.generic.semiauto.deriveDecoder
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._


class DatDotaAdaptor(httpWorker: DatDotaHttpWorker) extends DatDotaPort {
    def GetPremiumMatches(): List[DatDotaMatch] = {
        val response = httpWorker.GetPremiumMatches()

        implicit val proPlayerDecoder = deriveDecoder[DatDotaMatchRoot]

        val decodeResult : Either[Error, DatDotaMatchRoot] = parser.decode[DatDotaMatchRoot](response)

        return decodeResult.getOrElse({throw new Exception("Error decoding DatDota Premier match JSON Response.")}).data
    }
}