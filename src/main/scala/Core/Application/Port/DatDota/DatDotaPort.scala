package Core.Application.Port.DatDota

import Core.Application.Port.DatDota.Model.DatDotaMatch

trait DatDotaPort {
    def GetPremiumMatches() : List[DatDotaMatch]
}
