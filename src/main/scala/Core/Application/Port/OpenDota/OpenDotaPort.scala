package Core.Application.Port.OpenDota

import Core.Application.Port.OpenDota.Model.{Match, ProMatch, ProPlayer}

trait OpenDotaPort {
    def GetProPlayers(): List[ProPlayer]
    def GetProMatches(): List[ProMatch]
    def GetProMatches(lessThanMatchId : String): List[ProMatch]
    def GetMatch(matchId: Long): List[Match]
}
