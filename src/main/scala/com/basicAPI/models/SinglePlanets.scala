package com.basicAPI.models

import java.time.LocalDateTime
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._

case class SinglePlanet(
    name: String,
    rotation_period: String,
    orbital_period: String,
    diameter: String,
    climate: String,
    gravity: String,
    terrain: List[String],
    surface_water: Int,
    population: Int,
    residents: List[String],
    films: List[String],
    created: String,
    edited: String,
    url: String
)
//object SinglePlanet {
//
//  implicit val userJsonFormat: RootJsonFormat[SinglePlanet] =
//    jsonFormat14(SinglePlanet.apply)
//}