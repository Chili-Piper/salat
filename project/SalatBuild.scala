/*
 * Copyright (c) 2010 - May 2015 Novus Partners, Inc. (http://www.novus.com)
 * Copyright (c) June 2015 - 2016 Rose Toomey and contributors where noted (http://github.com/salat/salat)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *           Project:  http://github.com/salat/salat
 *              Wiki:  http://github.com/salat/salat/wiki
 *      Mailing list:  http://groups.google.com/group/scala-salat
 *     StackOverflow:  http://stackoverflow.com/questions/tagged/salat
 */

import sbt._
import Keys._
import bintray.BintrayPlugin.autoImport._

object BuildSettings {

  import Repos._

  val buildOrganization = "com.chilipiper"
  val buildScalaVersion = "2.13.2"

  val casbahResolver = Resolver.bintrayRepo("chili-piper","casbah")

  val buildSettings = Defaults.coreDefaultSettings ++ Seq(
    organization := buildOrganization,
    scalaVersion := buildScalaVersion,
    parallelExecution in Test := false,
    testFrameworks += TestFrameworks.Specs2,
    resolvers ++= Seq(typeSafeRepo, typeSafeSnapsRepo, oss, ossSnaps, scalazBintrayRepo, casbahResolver),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:_"),
    crossScalaVersions ++= Seq("2.13.2"),

    bintrayRepository := "salat",
    bintrayOrganization := Some("chili-piper"),
    bintrayReleaseOnPublish := true,

    licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))
  )
}

object Dependencies {

  private val LogbackVersion = "1.1.8"
  private val CasbahVersion = "4.0.0-RC2"

  private val Specs2Version = "4.9.4"
  val specs2 = "org.specs2" %% "specs2-core" % Specs2Version % "test"
  val specs2junit = "org.specs2" %% "specs2-junit" % Specs2Version % "test"
  val specs2matchers = "org.specs2" %% "specs2-matcher-extra" % Specs2Version % "test"

  val commonsLang = "commons-lang" % "commons-lang" % "2.6" % "test"
  val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.21"
  val logbackCore = "ch.qos.logback" % "logback-core" % LogbackVersion % "test"
  val logbackClassic = "ch.qos.logback" % "logback-classic" % LogbackVersion % "test"
  val casbah = "com.chilipiper" %% "casbah-core" % CasbahVersion
  val casbah_commons = "com.chilipiper" %% "casbah-commons" % CasbahVersion % "test"
  val casbah_specs = "com.chilipiper" %% "casbah-commons" % CasbahVersion % "test" classifier "tests"
}

object Repos {
  val scalazBintrayRepo = "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
  val typeSafeRepo = "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  val typeSafeSnapsRepo = "Typesafe Snaps Repo" at "http://repo.typesafe.com/typesafe/snapshots/"
  val oss = "OSS Sonatype" at "http://oss.sonatype.org/content/repositories/releases/"
  val ossSnaps = "OSS Sonatype Snaps" at "http://oss.sonatype.org/content/repositories/snapshots/"
}

object Helpers {
  def json4sNative(scalaVersion: String) =
    scalaVersion match {
      case "2.13.2" => "org.json4s" %% "json4s-native" % "3.6.7"
      case _ => "org.json4s" %% "json4s-native" % "3.2.9"
    }
}
