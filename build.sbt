name := "play-joda"

version := "1.0"

scalaVersion := "2.11.7"

val playVersion = "2.6.0-SNAPSHOT"

lazy val `play-joda` = project.in(file("."))
    .enablePlugins(PlayLibrary)
    .settings(scalariformSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play" % playVersion % Provided,
        "joda-time" % "joda-time" % "2.9.4",
        "org.specs2" % "specs2-core_2.11" % "3.8.4" % Test
      )
    )


playBuildRepoName in ThisBuild := "play-joda"