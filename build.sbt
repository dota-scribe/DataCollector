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

// Slick ORM
libraryDependencies ++= Seq(
    "com.typesafe.slick" %% "slick-extensions" % "3.3.0",
    "com.h2database" % "h2" % "1.4.199"
)