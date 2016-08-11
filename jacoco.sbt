import de.johoop.jacoco4sbt.HTMLReport

//----------------------------------------
// Setup Jacoco test coverage.
// Invocation: sbt jacoco:cover
// See: https://github.com/sbt/jacoco4sbt
//----------------------------------------

jacoco.settings

jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

jacoco.reportFormats   in jacoco.Config := Seq(HTMLReport("utf-8"))

jacoco.excludes        in jacoco.Config := Seq("com.knoldus.main.*")