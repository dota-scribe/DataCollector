package Core.Application.Port.DotaScribeRepository

import Core.Application.Port.OpenDota.Model.ProPlayer

trait DotaScribeRepositoryPort {
    def SaveProPlayers(proPlayers : List[ProPlayer])
}
