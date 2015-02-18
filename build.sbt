import com.typesafe.sbt.web.Import.WebKeys._
import com.typesafe.sbt.web.pipeline.Pipeline

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

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

val myPipelineTask = taskKey[Pipeline.Stage]("Some pipeline task")

myPipelineTask := { mappings => println(mappings); mappings }

pipelineStages := Seq(myPipelineTask, concat)

Concat.groups := Seq(
  "style-group1.css" -> group(sourceDirectory.value  ** "*.css"),
  "style-group2.css" -> group(baseDirectory.value  ** "*.css"),
  "style-group3.css" -> group((sourceDirectory in Assets).value  ** "*.css"),
  "style-group4.css" -> group(target.value  ** "*.css"),
  "style-group5.css" -> group(Seq("core.css", "styles/core.css", "assets/styles/core.css", "app/assets/styles/core.css")),
  "style-group6.css" -> group(Seq("lessStyle.css", "ui/lessStyle.css", "styles/ui/lessStyle.css", "assets/styles/ui/lessStyle.css", "app/assets/styles/ui/lessStyle.css")),
  "style-group7.css" -> group(Seq("sassStyle.css", "ui/sassStyle.css", "styles/ui/sassStyle.css", "assets/styles/ui/sassStyle.css", "app/assets/styles/ui/sassStyle.css")),
  "style-group8.css" -> group(Seq("**/*.css"))
)
