package controllers


object DashboardController extends BaseController {

  def dashboard = AuthenticatedLoggingAction {
    implicit user =>
      implicit request =>
        Ok(views.html.dashboard.dashboardPage())
  }

}