name := "NLJUG-sessies"

lazy val commonSettings = Seq(
  version := "0.1.0", 
  scalaVersion := "2.12.7"
)

// Definition of the root build, aggregating all the modules
lazy val nljugSessiesRoot = project.in(file("."))
  .settings(commonSettings)
  .settings(
    name := "NLJUG-sessies"
  )
