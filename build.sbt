name := "akkahttp-java8-template"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http-core_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-http-testkit_2.11" % "2.4.8" % "test"
)