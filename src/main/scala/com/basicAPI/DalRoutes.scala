package com.basicAPI

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.http.scaladsl.unmarshalling.{Unmarshal, Unmarshaller}
import akka.stream.ActorMaterializer
import scala.language.postfixOps
import spray.json._
import com.basicAPI.json.JsonSupport
import com.basicAPI.models.SinglePlanet

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

object DalRoutes extends Directives with JsonSupport {
  implicit val system: ActorSystem = ActorSystem("system")
  implicit val materializer = ActorMaterializer
  implicit val executionContext:ExecutionContext = system.dispatcher

  def getJson(typeClass: String, num: Int):Future[String] = {

    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"https://swapi.co/api/${typeClass}/${num}/"
    )
    val client = Http().singleRequest(request).map(res =>
      Unmarshal(res.entity).to[String]
    )
    Await.result(client, 10 seconds)
  }
}