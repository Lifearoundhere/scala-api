package com.basicAPI

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.basicAPI.models.{Cart, Item, SinglePlanet}
import spray.json.DefaultJsonProtocol._

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

object Server extends App {

  implicit val system: ActorSystem = ActorSystem("system")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer = ActorMaterializer

  implicit val itemFormat = jsonFormat2(Item)

  val route =
    path("planet") {
      concat(
        get {
          complete(
           DalRoutes.getJson("planets",1)
          )
        },
        post {
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Hello World</h1>")
          )
        }
      )
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(
    s"com.basicAPI.Server online at http://localhost:8080/planet\nPress RETURN to stop..."
  )
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
