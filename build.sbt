name := "classes"

version := "0.1"

scalaVersion := "2.13.3"

lazy val standard =
  project
    .in(file("modules/standard"))
    .settings(name := "standard")
    .settings(
      libraryDependencies ++= Seq(
        "org.typelevel"     %% "cats-effect"              % "2.3.0",
        "org.scalatest"     %% "scalatest-funsuite"       % "3.2.3" % Test,
        "org.scalatest"     %% "scalatest-shouldmatchers" % "3.2.3" % Test,
        "org.scalatestplus" %% "scalacheck-1-14"          % "3.2.2.0" % Test,
        "org.scalacheck"    %% "scalacheck"               % "1.14.1" % Test
      )
    )

lazy val streams =
  project
    .in(file("modules/streams"))
    .settings(name := "streams")
    .dependsOn(standard)
    .settings(libraryDependencies ++= Seq("co.fs2" %% "fs2-core" % "2.4.6"))

lazy val classes =
  project
    .in(file("."))
    .settings(name := "classes")
    .aggregate(standard, streams)
