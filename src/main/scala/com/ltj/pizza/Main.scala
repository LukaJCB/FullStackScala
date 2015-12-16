package com.ltj.pizza

import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.jquery.jQuery
import dom.document
import dom.window
import upickle.default._
import com.ltj.models._
import scala.scalajs.js.annotation.JSExport

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
	 	   jQuery.post("/orders", write(order),(data: Any) => {
    		 	window.alert("Your order has been processed and will be there shortly!")
    	   })
	  } else {
	 	  window.alert("Minimum value not reached!")
	  }
  }
  
  def main(): Unit = {}
	
  
  
}