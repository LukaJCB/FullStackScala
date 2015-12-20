package com.ltj.server

import com.ltj.models.Order
import org.scalatra._
import upickle.default._

class PizzaServlet extends PizzaorderStack {

  get("/") {
    <html>
      <body>
        <h1>Order your Pizza!</h1>
        <form>
          <select id="topping">
            <option value="Margherita">Margherita</option>
            <option value="Onion">Onion</option>
            <option value="Olives">Olives</option>
            <option value="Peppers">Peppers</option>
            <option value="Ham">Ham</option>
            <option value="Pepperoni">Pepperoni</option>
          </select>
          <input type="button" onclick="com.ltj.pizza.Main().submitTopping()" value="Add Topping to Pizza"/><br/>
          <input type="button" onclick="com.ltj.pizza.Main().submitPizza()" value="Add Pizza to Order"/><br/><br/>
          Your current total: <span id="price">0.00</span>$<br/>
          Please select at least 12.50$ before sending your order.
          <input type="button" onclick="com.ltj.pizza.Main().submitOrder()" value="Send Order!"/>
        </form>
        <script src="lib/jquery-1.9.1.min.js"></script>
        <script src="js/scala-2.11/pizza-order-fastopt.js"></script>
      </body>
    </html>
  }

  post("/orders"){
    val order = read[Order](request.body)
    if (!order.isValidOrder()) halt(400)
  }

}
