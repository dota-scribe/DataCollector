package Core.Application.AppService

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort


class DotaScribeDbService(dotaScribeRepositoryPort: DotaScribeRepositoryPort) {
    def RegenerateDbMappings(): Unit = {
        dotaScribeRepositoryPort.RebuildDbMappings()
    }
}
