package com.basicAPI

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.basicAPI.models.{Cart, Item}
import spray.json.DefaultJsonProtocol._


import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

object Server extends App {
//  val host = "0.0.0.0"
//  val port = 9000

  implicit val system: ActorSystem = ActorSystem("system")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer = ActorMaterializer()

  implicit val itemFormat = jsonFormat2(Item)

//
//  var cart: List[Item] = Nil
//
//  def fetchItem(itemId: Long): Future[Option[Item]] = Future {
//    cart.find(o => o.id == itemId)
//  }

  val route =
    path("item") {
      concat(
        get {
          complete(
            Service.first.map(_.toString)
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
    s"com.basicAPI.Server online at http://localhost:8080/item\nPress RETURN to stop..."
  )
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
