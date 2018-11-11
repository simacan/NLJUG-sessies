package com.simacan.pizzascala.futuremapping

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object MockDB extends DB {
  override def findLocation(locationId: Int): Future[Option[Location]] = {

    locationId match { // Pattern match. Similar to Java 'switch' statement
      case -1 => Future.failed(new RuntimeException("Could not make db connection"))
      case 0 => Future(None)
      case _ => Future(Some(Location(locationId, "Simacan kantoor", 52.1938, 5.4088)))
    }
  }
}

object Main extends App {

  val deliveryValidation = new DeliveryValidation(MockDB)

  // Failure -> Runtime exception
  val fut1 = deliveryValidation.addLocationToDelivery(Delivery(-1, "Bloemen"))
  fut1.onComplete(result => println(s"bloemen result: $result"))

  // Success -> None
  val fut2 = deliveryValidation.addLocationToDelivery(Delivery(0, "Laptop"))
  fut2.onComplete(result => println(s"laptop result: $result"))

  // Failure -> Runtime error
  val fut3 = deliveryValidation.addLocationToDelivery(Delivery(2, "Pizza"))
  fut3.onComplete(result => println(s"pizza result: $result"))

  Await.ready(fut1, 10.seconds)
  Await.ready(fut2, 10.seconds)
  Await.ready(fut3, 10.seconds)
}
