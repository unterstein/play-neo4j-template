name := "play-neo4j-template"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4" // TODO update to: 2.11.1, 2.10.4 is needed for neo4j-plugin

libraryDependencies ++= Seq(
  "com.github.tuxBurner" %% "play-neo4jplugin" % "1.3.6",
  "commons-lang" % "commons-lang" % "2.6",
  "commons-io" % "commons-io" % "2.4",
  "joda-time" % "joda-time" % "2.3",
  "com.google.code.gson" % "gson" % "2.2.4"
)

resolvers ++= Seq(
  "tuxburner.github.io" at "http://tuxburner.github.io/repo",
  "Neo4j" at "http://m2.neo4j.org/content/repositories/releases/",
  "Spring milestones" at "http://repo.spring.io/milestone"
)
