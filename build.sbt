
import Dependencies._
import BuildSettings._

val testDeps = Seq(logbackCore, specs2, specs2matchers, specs2junit, logbackClassic, casbah_specs, casbah_commons)
val utilDeps = Seq(slf4jApi) ++ testDeps
val coreDeps = Seq(casbah, commonsLang) ++ testDeps

lazy val salat = Project(
  id = "salat",
  base = file("."))
    .settings(buildSettings)
    .aggregate(util, core)
    .dependsOn(util, core)

lazy val util = {
  val id = "salat-util"
  val base = file("salat-util")
  val settings = buildSettings ++ Seq(
    libraryDependencies ++= utilDeps,
    libraryDependencies += (scalaVersion("org.scala-lang" % "scalap" % _)).value,
    libraryDependencies ++= (scalaVersion(sv => Seq(Helpers.json4sNative(sv)))).value
  )
  Project(id = id, base = base)
    .settings(settings)
}

lazy val core = Project(
  id = "salat-core",
  base = file("salat-core")
).settings(buildSettings ++ Seq(
    libraryDependencies ++= coreDeps,
    libraryDependencies ++= (scalaVersion(sv => Seq(Helpers.json4sNative(sv)))).value
  )).dependsOn(util)
