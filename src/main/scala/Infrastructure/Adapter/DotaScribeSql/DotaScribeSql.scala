package Infrastructure.Adapter.OpenDota.MsSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model.ProPlayer
import Infrastructure.Adapter.DotaScribeSql.Table.ProPlayerTable
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

class DotaScribeSql extends DotaScribeRepositoryPort {
    override def SaveProPlayers(proPlayers: List[ProPlayer]): Unit = ???

    def InsertRows(db: H2Profile.backend.Database): Future[Unit] = {
        val proPlayerTable = TableQuery[ProPlayerTable]

        val setup = DBIO.seq(
            proPlayerTable.schema.create,
            proPlayerTable += (1232, "sadfas"),
        )

        return db.run(setup)
    }

}

