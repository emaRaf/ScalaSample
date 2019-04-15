name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += "johnreed2 bintray" at "http://dl.bintray.com/content/johnreed2/maven"


scalaVersion := "2.12.8"

val akkaHttp = "10.0.3"
val akka = "2.4.16"
val akkaHttpCirce = "1.20.1"
val logbackClassic = "1.2.3"
val circe = "0.9.3"
val quill = "3.1.0"
val metrics = "4.0.4"
val prometheusSimpleClient = "0.6.0"


crossScalaVersions := Seq("2.11.12", "2.12.7")

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies ++= Seq(
  "com.typesafe.akka"        %% "akka-http"              % akkaHttp,
  "com.typesafe.akka"        %% "akka-stream"            % akka,
  "com.typesafe.akka"        %% "akka-slf4j"             % akka,
  "de.heikoseeberger"        %% "akka-http-circe"        % akkaHttpCirce,
  "io.circe"                 %% "circe-generic"          % circe,
  "ch.qos.logback"           % "logback-classic"         % logbackClassic,
  "io.getquill"              %% "quill-async-postgres"   % quill,
  "nl.grons"                 %% "metrics4-scala"         % metrics,
  "io.prometheus"            % "simpleclient_dropwizard" % prometheusSimpleClient,
  "io.prometheus"            % "simpleclient_common"     % prometheusSimpleClient,
)