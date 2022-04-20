val Http4sVersion  = "1.0.0-M32"

lazy val root = (project in file("."))
  .settings(
    organization := "example",
    name := "zio-http4s-example",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.1.2",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl"          % Http4sVersion,
      "dev.zio"    %% "zio"                 % "2.0.0-RC5",
      "dev.zio"    %% "zio-interop-cats"    % "3.3.0-RC5",
      "org.slf4j"  %  "slf4j-simple"        % "1.7.36"
    )
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings"
)
