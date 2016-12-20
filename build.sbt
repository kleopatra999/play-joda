resolvers ++= DefaultOptions.resolvers(snapshot = true)

val playVersion = "2.6.0-2016-12-15-503078e-SNAPSHOT"

lazy val `play-joda` = project.in(file("."))
    .enablePlugins(PlayLibrary)
    .settings(scalariformSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play" % playVersion % Provided,
        "joda-time" % "joda-time" % "2.9.6",
        "org.specs2" %% "specs2-core" % "3.8.6" % Test
      )
    )


playBuildRepoName in ThisBuild := "play-joda"

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)