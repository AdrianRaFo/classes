name := "classes"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.typelevel"     %% "cats-effect"              % "2.3.0",
  "co.fs2"            %% "fs2-core"                 % "2.4.6",
  "org.scalatest"     %% "scalatest-funsuite"       % "3.2.3" % Test,
  "org.scalatest"     %% "scalatest-shouldmatchers" % "3.2.3" % Test,
  "org.scalatestplus" %% "scalacheck-1-14"          % "3.2.2.0" % Test,
  "org.scalacheck"    %% "scalacheck"               % "1.14.1" % Test
)
