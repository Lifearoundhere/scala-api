package com.basicAPI

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import com.basicAPI.models.Item

import scala.collection.mutable.ListBuffer


object Service {
  private val buffer = ListBuffer.empty[Item]

  buffer += Item(0,"Zero")
  buffer += Item(1,"one")

  implicit val baseTime = System.currentTimeMillis

  def CreateAddItem(name: String):ListBuffer[Item] = buffer.addOne(Item(
    id = 0L,
    name = name
  ))
  def removeItem(id: Long):ListBuffer[Item] =
    findItem(id) match {
      case Some(_) => buffer.subtractOne(findItem(id).get)
      case _ => {
        println(s"Item ${id} not found")
        buffer
      }
    }

  def findItem(id: Long): Option[Item] = buffer.find(Item => Item.id == id)

//  def findItemSeq(item: Item): Int = buffer.search(item).

  def updateItem(item: Item, newName: String): ListBuffer[Item] = {
    removeItem(item.id)
    CreateAddItem(newName)
  }
  def showBuffer: List[Item] = buffer.toList

  def first: Future[Item] = Future {
    showBuffer.head
  }


}
