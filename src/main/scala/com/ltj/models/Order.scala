package com.ltj.models


case class Order(pizzas: List[Pizza]) {
  
  val minimumOrderValue = 12.50
  
  def isValidOrder(): Boolean = {
	  price() > minimumOrderValue
  }
  
  def price(): Float = {
	  val toppingPrices = for {
	 	  pizza <- pizzas
	 	  topping <- pizza.toppings
	  } yield topping.price
	  toppingPrices.foldLeft(Pizza.BasePrice* pizzas.length)(_ + _)
  }
  
  
}


