package Core.Application.Port.DotaScribeRepository

import Core.Application.Port.OpenDota.Model.{ProMatch, ProPlayer}

trait DotaScribeRepositoryPort {
    def SaveProPlayers(proPlayers : List[ProPlayer])
    def SaveProMatches(proMatches: List[ProMatch])
    def RebuildDbMappings()
}
