package Infrastructure.Adapter.QuillDotaScribeSql.DAO
import io.getquill._

class PostgresContext extends SqlServerJdbcContext(PostgresEscape, "ctx")



