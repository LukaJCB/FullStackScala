enablePlugins(ScalaJSPlugin)

name := "Pizza Order"

scalaVersion := "2.11.5" 

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.3.6"