resolvers ++= DefaultOptions.resolvers(snapshot = true)
resolvers += Resolver.typesafeRepo("releases")

// Play specific
addSbtPlugin("com.typesafe.play" % "interplay" % sys.props.get("interplay.version").getOrElse("1.3.2"))
//addSbtPlugin("com.typesafe.play" % "play-docs-sbt-plugin" % sys.props.getOrElse("play.version", "2.5.10"))

// Code Quality
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.1.7")
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

// Publishing
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")