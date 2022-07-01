# ZIO http4s Simple Example

Here's how to put together almost the simplest [http4s](https://http4s.org/) server with [ZIO](https://zio.dev/).

* See the `zio1` branch for a version that works with ZIO v1.

### Run:

`sbt run`

### Try:

`curl localhost:8080/hello`

### Dependencies:

```scala
"org.http4s" %% "http4s-blaze-server" % "1.0.0-M33",
"org.http4s" %% "http4s-dsl"          % "1.0.0-M33",
"dev.zio"    %% "zio"                 % "2.0.0",
"dev.zio"    %% "zio-interop-cats"    % "3.3.0"
```

### Code:

```scala
package example

import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.Http4sDsl
import zio.interop.catz._
import zio.{Task, ZEnv, ZIO, ZIOAppDefault}

object Main extends ZIOAppDefault {

  private val dsl = Http4sDsl[Task]

  import dsl._

  private val helloWorldService = HttpRoutes
    .of[Task] {
      case GET -> Root / "hello" => Ok("Hello, Joe")
    }
    .orNotFound

  def run =
    ZIO
      .runtime
      .flatMap { implicit runtime =>
        BlazeServerBuilder[Task]
          .bindHttp(8080, "localhost")
          .withHttpApp(helloWorldService)
          .resource
          .useForever
      }

}
```

### random keywords:
zio, instead of cats, cats-effect, final tagless, http4s, http server, managed, resource, effect tracking, typesafe,
strongly typed, functional, monad
