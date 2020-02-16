package com.basicAPI.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.basicAPI.models.SinglePlanet
import spray.json._

trait  JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val singlePlanet = jsonFormat14(SinglePlanet)

  def write(obj: SinglePlanet) = obj.toJson

//  def write(v: SinglePlanet) = JsObject(
//    "name" -> JsString(v.name),
//    "rotation_period" -> JsString(v.name),
//    "orbital_period" -> JsString(v.name),
//    "diameter" -> JsString(v.name),
//    "climate" -> JsString(v.name),
//    "gravity" -> JsString(v.name),
//    "terrain" -> JsArray(v.residents: Vector[String])),
//    "surface_water" -> JsNumber(v.name),
//    "population" -> JsNumber(v.name),
//    "residents" -> JsArray(Vector(v.residents)),
//    "films" -> JsArray(Vector(v.residents)),
//    "created" -> JsString(v.name),
//    "edited" -> JsString(v.name),
//    "url" -> JsString(v.name)
//  }
}
