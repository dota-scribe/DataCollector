package Core.Application.Port.OpenDota

import Core.Application.Port.OpenDota.Model.ProPlayer

trait OpenDotaPort {
    def GetProPlayers(): List[ProPlayer]
}
