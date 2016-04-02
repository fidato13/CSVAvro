organization := "com.trn.avro"

name := "csv-avro"

version := "0.0.1"

scalaVersion := "2.10.5"


libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.7.7" exclude("org.mortbay.jetty", "servlet-api"),
  "org.apache.avro" % "avro-tools" % "1.7.7",
  "org.apache.avro" % "avro-mapred" % "1.7.7"  % "provided" classifier("hadoop2") exclude("org.mortbay.jetty", "servlet-api"),
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "commons-io" % "commons-io" % "2.4" % "test"
)

