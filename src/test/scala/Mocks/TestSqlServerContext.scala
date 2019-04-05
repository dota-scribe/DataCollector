package Mocks

import io.getquill._

class TestSqlServerContext extends SqlServerJdbcContext(PostgresEscape, "ctx")



