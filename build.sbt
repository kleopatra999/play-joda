import interplay.ScalaVersions._

name := "play-joda"

version := "1.0"

scalaVersion := scala212

crossScalaVersions := Seq(scala211, scala212)

val playVersion = "2.6.0-M1"

lazy val `play-joda` = project.in(file("."))
    .enablePlugins(PlayLibrary)
    .settings(
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play" % playVersion % Provided,
        "com.typesafe.play" %% "play-json" % "2.6.0-M3",
        "joda-time" % "joda-time" % "2.9.7",
        "org.joda" % "joda-convert" % "1.8.1",
        "org.specs2" %% "specs2-core" % "3.8.8" % Test
      )
    )


playBuildRepoName in ThisBuild := "play-joda"