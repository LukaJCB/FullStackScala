package com.ltj.server

import com.ltj.models.Order
import com.mongodb.DBObject
import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.util.JSON
import org.bson.types.ObjectId

import org.scalatra._
import upickle.default._

class PizzaServlet(db: MongoDB) extends PizzaorderStack {


  post("/orders"){
    val json = request.body
    val order = read[Order](json)
    if (!order.isValidOrder()) halt(400)

    val orders = db("orders")
    orders += JSON.parse(json).asInstanceOf[DBObject]

  }

  get("/orders"){
    val orders = db("orders").find()
    JSON.serialize(orders.toList)
  }

  delete("/orders/:id"){
    val id = new ObjectId(params("id"))
    val orders = db("orders")
    orders.remove(MongoDBObject("_id" ->  id))
  }



}
