name := """sbt-concat-test"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

resolvers += Resolver.sonatypeRepo("releases")

pipelineStages := Seq(concat)

Concat.parentDir := "concatenated"

Concat.groups := Seq(
  "style-group1.css" -> group(sourceDirectory.value  ** "*.css"),
  "style-group2.css" -> group((sourceDirectory in Assets).value  ** "*.css"),
  "style-group3.css" -> group(target.value  ** "*.css"),
  "style-group4.css" -> group(Seq("core.css", "styles/core.css", "assets/styles/core.css", "app/assets/styles/core.css"))
)