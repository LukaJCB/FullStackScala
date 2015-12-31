package com.ltj.pizza

import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.jquery.jQuery
import dom.document
import dom.window
import upickle.default._
import com.ltj.models._
import scala.scalajs.js.annotation.JSExport
import org.scalajs.jquery.JQueryAjaxSettings
import scala.scalajs.js
import org.scalajs.jquery.JQuery

object Main extends JSApp {
	
  var order = Order(List())
  var currentPizza = Pizza(List())
  
  @JSExport
  def submitTopping(): Unit = { 
	  val unvalidatedTopping = jQuery("#topping").`val`.toString()
	  val topping = Topping(unvalidatedTopping)
	  currentPizza = currentPizza.addTopping(topping) 
  }
  
  @JSExport
  def submitPizza(): Unit =  {  
	  order =  Order(order.pizzas :+ currentPizza); 
	  currentPizza = Pizza(List())
	  jQuery("#price").html(order.price().toString())
  }
  
  @JSExport
  def submitOrder(): Unit = { 
	  if (order.isValidOrder()){
	 	  
	 	   val jqxhr = jQuery.post("/orders", write(order),(data: Any) => {
    		 	window.alert("Your order has been processed and will be there shortly!")
    	   })
    	   
	  } else {
	 	  window.alert("Minimum value not reached!")
	  }
  }
  
  @JSExport
  def showOrders(): Unit = {
  		jQuery.get(url = "/orders",  success = (data: String) => {
    		val orders = read[Seq[Order]](data)
    		orders.foreach(order => {
    			val list = jQuery("<ul>")
    			order.pizzas.foreach { pizza => list.append(jQuery("<li>").text(pizza.toString())) }
    			jQuery("#orders").append(jQuery("<li>").append(list))
    		})
		})
  }
  
  def main(): Unit = {
	  
		jQuery.ajaxSetup(js.Dynamic.literal(
			contentType = "application/json"
		))
  }
	
  
  
}