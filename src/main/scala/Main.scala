import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Server extends App {
  val host = "0.0.0.0"
  val port = 9000

  println("Hello, World!")

  implicit val system: ActorSystem = ActorSystem("Hello")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Hello World</h1>"))
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)


  println(s"Server online at http://localhost:8080/hello\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
