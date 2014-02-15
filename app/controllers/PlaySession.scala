package controllers

import neo4j.models.User

object PlaySession extends BaseController {
  def AUTH_SESSION: String = "EMAIL"

  def getUser(implicit request: play.api.mvc.RequestHeader): User = {
    val token = session.get(AUTH_SESSION)
    if (token == None || token.get == null) {
      null
    } else {
      User.getUserByEMail(token.get)
    }
  }

  def isLoggedIn(implicit request: play.api.mvc.RequestHeader): Boolean = {
    getUser != null
  }

}