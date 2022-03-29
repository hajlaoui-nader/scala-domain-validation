lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion := "2.13.6",
  // enablePlugins(GatlingPlugin),
  libraryDependencies ++= List(
    "org.typelevel" %% "cats-effect" % "3.3-162-2022ef9",
    "co.fs2" %% "fs2-core" % "3.1.3",
    "co.fs2" %% "fs2-io" % "3.1.3",
    "tf.tofu" %% "derevo-cats" % "0.12.6",
    "io.estatico" %% "newtype" % "0.4.4",
    "io.circe" %% "circe-core" % "0.13.0",
    "io.circe" %% "circe-generic" % "0.13.0",
    "io.circe" %% "circe-parser" % "0.13.0",
    // gatling
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.7.1",
    "io.gatling" % "gatling-test-framework" % "3.7.1"
  ),
  addCompilerPlugin(
    "org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full
  ),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  scalacOptions ++= Seq(
    "-Ymacro-annotations"
  ),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val onlycats = project
  .in(file("."))
  .settings(
    name := "onlycats",
    version := "0.0.1-SNAPSHOT"
  )
  .settings(baseSettings: _*)

lazy val docs = project // new documentation project
  .in(file("onlycats-docs")) // important: it must not be docs/
  .settings(baseSettings: _*)
  .dependsOn(onlycats)
  .enablePlugins(MdocPlugin)
