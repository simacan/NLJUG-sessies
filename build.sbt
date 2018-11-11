lazy val commonSettings = Seq(
  version := "0.1.0", 
  scalaVersion := "2.12.7"
)

// An sbt module
lazy val pizzaScala29_11_2018 = project.in(file("pizza-scala-29-11-2018"))
  .settings(commonSettings)
  .settings(
    name := "pizza-scala-29-11-2018",
  )

// Definition of the root build, aggregating all the modules
lazy val nljugSessiesRoot = project.in(file("."))
  .settings(
    name := "NLJUG-sessies"
  )
  .aggregate(pizzaScala29_11_2018)
