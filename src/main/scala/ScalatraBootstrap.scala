import com.ltj.server._
import org.scalatra._
import javax.servlet.ServletContext
import com.mongodb.casbah.Imports._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    val mongoClient =  MongoClient()
    val mongo = mongoClient("pizza")
    context.mount(new PizzaServlet(mongo), "/*")
  }
}
