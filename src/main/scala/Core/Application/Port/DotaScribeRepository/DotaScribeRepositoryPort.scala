package Core.Application.Port.DotaScribeRepository

import Core.Application.Port.DatDota.Model.DatDotaMatch
import Core.Application.Port.OpenDota.Model.{Match, ProMatch, ProPlayer}

trait DotaScribeRepositoryPort {
    def SavePremiumMatches(premiumMatches: List[DatDotaMatch])
    def SaveProPlayers(proPlayers : List[ProPlayer])
    def GetProMatchesWithoutMatchData(): List[ProMatch]
    def SaveProMatches(proMatches: List[ProMatch])
    def SaveMatch(matchData: Match)
    def GetMinProMatchId(): Option[Long]
    def GetPremiumMatchesWithoutMatchData(): List[DatDotaMatch]
}
