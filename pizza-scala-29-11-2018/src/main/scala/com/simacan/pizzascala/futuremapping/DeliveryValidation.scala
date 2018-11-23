package com.simacan.pizzascala.futuremapping

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Delivery(locationId: Int, shipment: String)
case class Location(id: Int, name: String, lat: Double, lon: Double)

case class DeliveryWithLocation(delivery: Delivery, location: Location)

trait DB {

  /**
    * Finds a location by id in a remote database.
    *
    * @param locationId Id of a potential location.
    * @return a future of an Option of a Location. If no Location is found, it returns Future(None).
    *         If no DB connection can be made, the Future fails.
    */
  def findLocation(locationId: Int): Future[Option[Location]]
}

class DeliveryValidation(db: DB) {

  def addLocationToDelivery(delivery: Delivery): Future[Option[DeliveryWithLocation]] = {
    db.findLocation(delivery.locationId)
      .map(
        locationOpt =>
          locationOpt.map(
            location => DeliveryWithLocation(delivery, location)
        ))
  }
}
