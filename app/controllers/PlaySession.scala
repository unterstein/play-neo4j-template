package controllers

import neo4j.models.user.{UserRole, User}
import scala.Boolean

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

  def isAdmin(implicit request: play.api.mvc.RequestHeader): Boolean = hasMinRole(UserRole.ADMIN)

  def hasMinRole(role: UserRole)(implicit request: play.api.mvc.RequestHeader): Boolean = getUser.hasMinRole(role)

}