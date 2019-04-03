package Infrastructure.Adapter.QuillDotaScribeSql

import Core.Application.Port.DotaScribeRepository.DotaScribeRepositoryPort
import Core.Application.Port.OpenDota.Model._
import Infrastructure.Adapter.QuillDotaScribeSql.DAO._

class QuillDotaScribeSql extends DotaScribeRepositoryPort with DaoSchema {
    override val Context: PostgresContext = new PostgresContext()
    import Context._

    override def SaveProPlayers(proPlayers: List[ProPlayer]): Unit = ???

    override def SaveProMatches(proMatches: List[ProMatch]): Unit = ???

    override def SaveMatch(matchData: Match): Unit = {
        val matchInsert = quote(MatchSchema.insert(lift(MatchDao(
            matchData.match_id,
            matchData.barracks_status_dire,
            matchData.barracks_status_radiant,
            matchData.cluster,
            matchData.dire_score,
            matchData.dire_team_id,
            matchData.duration,
            matchData.engine,
            matchData.first_blood_time,
            matchData.game_mode,
            matchData.human_players,
            matchData.leagueid,
            matchData.lobby_type,
            matchData.match_seq_num,
            matchData.negative_votes,
            matchData.positive_votes,
            matchData.radiant_score,
            matchData.radiant_team_id,
            matchData.skill,
            matchData.start_time,
            matchData.tower_status_dire,
            matchData.tower_status_radiant,
            matchData.version,
            matchData.replay_salt,
            matchData.series_id,
            matchData.series_type,
            matchData.patch,
            matchData.region,
            matchData.comeback,
            matchData.stomp,
            matchData.replay_url
        ))))

        Context.run(matchInsert)
    }

    override def RebuildDbMappings(): Unit = ???

}
