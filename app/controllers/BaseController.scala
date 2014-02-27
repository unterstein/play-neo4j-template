package controllers

import play.api.mvc._
import neo4j.models.log.PerformanceLog
import neo4j.models.log.LogCategory
import scala.{None, Option}
import scala.Predef._
import neo4j.models.user.User
import views.html
import play.api.i18n.Lang
import play.api.Play.current

trait BaseController extends Controller {
  def LoggingAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action {
      implicit request =>
        val start = System.currentTimeMillis()
        val result = f(request)
        val end = System.currentTimeMillis()
        PerformanceLog.create(end - start, LogCategory.REQUEST, request)
        result
    }
  }
  private def userUsername(request: RequestHeader): Option[String] = {
    val username = request.session.get(PlaySession.AUTH_SESSION)
    if (username == None) {
      None
    } else {
      val checkIfUserExsists = User.checkIfUserExsists(username.get)
      if (checkIfUserExsists == true) {
        username // return value
      } else {
        None // return value
      }
    }
  }
  private def userOnUnauthorized(requestHeader: RequestHeader) = {
    val request = Request.apply(requestHeader, null)
    Results.Ok(html.signin.loginPage(AuthenticationController.loginForm, AuthenticationController.registerForm)(request, calcLang(request)))
  }

  def AuthenticatedLoggingAction(f: => String => Request[AnyContent] => Result) = Security.Authenticated(userUsername, userOnUnauthorized) {
    user => {
      LoggingAction(request => f(user)(request).withSession(PlaySession.AUTH_SESSION -> user))
    }
  }

  def calcLang(request: Request[Any]): Lang = {
    Lang.preferred(request.acceptLanguages)
  }
}