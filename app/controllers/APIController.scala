package controllers

import play.api._
import play.api.mvc._
import play.api.libs.Jsonp
import play.api.libs.json._
import play.api.libs.functional.syntax._

object APIController extends Controller {

  def user(callback: String) = Action {
    val json = Json.toJson(testUser)
    Ok(Jsonp(callback, json))
  }

  case class User(username: String, firstname: String, lastname: String)
  val testUser = User("test", "f", "l")
  
  implicit val userWrites: Writes[User] = (
    (JsPath \ "username").write[String] and
    (JsPath \ "firstname").write[String] and
    (JsPath \ "lastname").write[String]
  )(unlift(User.unapply))
}