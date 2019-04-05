package Infrastructure.Adapter.QuillDotaScribeSql.DAO
import io.getquill._

class SqlServerContext extends SqlServerJdbcContext(PostgresEscape, "ctx")



