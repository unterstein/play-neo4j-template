package neo4j.models.log;

import controllers.PlaySession;
import neo4j.models.AbstractModel;
import neo4j.models.User;
import play.api.mvc.RequestHeader;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbstractLog extends AbstractModel {

  public LogLevel logLevel;

  public LogCategory logCategory;

  public String httpUrl;

  public String httpMethod;

  public String httpHeader;

  public String remoteAddress;

  public String userIdentifier;

  protected static void abstractCreate(AbstractLog log, LogCategory logCategory, LogLevel logLevel, RequestHeader request, List<AbstractModel> relatedTo) {
    Set<AbstractModel> relatedToSet = new HashSet<AbstractModel>();
    log.logCategory = logCategory;
    log.logLevel = logLevel;

    if (request != null) {
      log.httpUrl = "" + request.uri();
      log.httpMethod = "" + request.method();
      log.remoteAddress = "" + request.remoteAddress();
      log.httpHeader = request.headers().toString();

      if (log instanceof PlayLog) {
        PlayLog votilloLog = (PlayLog) log;
        votilloLog.httpQueryParams = "" + request.rawQueryString();
      }
    } else {
      // performance logs do not have requests set for sms or mail ;-)
      log.httpUrl = logCategory.name();
    }

    if (log instanceof PlayLog) {
      PlayLog playLog = (PlayLog) log;
      playLog.relatedTo = relatedToSet;
      for (AbstractModel abstractModel : relatedTo) {
        if (abstractModel != null) {
          relatedToSet.add(abstractModel);
        }
      }
    }

    log.userIdentifier = calcUsers(log, request, relatedToSet);
  }

  private static String calcUsers(AbstractLog log, RequestHeader request, Set<AbstractModel> relatedToSet) {
    String result = "";
    User user = null;
    try {
      user = PlaySession.getUser(request);
    } catch (Exception e) {
      // dont care about the exception
    }
    if (user != null) {
      relatedToSet.add(user);
      result = user.email;
      result += " (user)";
    }
    return result;
  }

  public String formattedDate(String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(created);
  }
}
