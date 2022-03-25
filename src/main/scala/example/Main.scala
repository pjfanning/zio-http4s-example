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
      .runtime[ZEnv]
      .flatMap { implicit runtime =>
        BlazeServerBuilder[Task]
          .bindHttp(8080, "localhost")
          .withHttpApp(helloWorldService)
          .resource
          .toManagedZIO
          .useForever
      }

}
