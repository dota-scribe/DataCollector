name := "DataCollector"

version := "0.1"

scalaVersion := "2.12.8"

// Build tools
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0"

// Unit Tests
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

// Http Requests
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.1"

// Json Parsing
libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
).map(_ % "0.10.0")

// Quill
libraryDependencies ++= Seq(
    "com.microsoft.sqlserver" % "mssql-jdbc" % "7.2.1.jre11",
    "com.h2database" % "h2" % "1.4.199" % Test,
    "io.getquill" %% "quill-jdbc" % "3.1.0"
)