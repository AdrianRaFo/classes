name := "classes"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.scalatest"  %% "scalatest-funsuite"          % "3.2.3"  % Test,
  "org.scalatest"  %% "scalatest-shouldmatchers"    % "3.2.3"  % Test,
  "org.scalacheck" %% "scalacheck"                  % "1.14.1" % Test,
  "com.47deg"      %% "scalacheck-toolbox-datetime" % "0.4.0"  % Test
)
