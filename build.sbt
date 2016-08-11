name := "akkahttp-java8-template"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-http-testkit_2.11" % "2.4.8" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test" exclude("junit", "junit")
)

javacOptions in doc ++= Seq("-encoding", "UTF-8", "-source", "1.8")

javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint")

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

mainClass in (Compile, run) := Some("com.knoldus.main.PingPongApiLauncher")