package neo4j.models.log;

import global.Global;
import neo4j.models.AbstractModel;
import neo4j.services.Neo4JServiceProvider;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.NodeEntity;
import play.Logger;
import play.api.mvc.AnyContent;
import play.api.mvc.Request;

import java.util.LinkedList;

@NodeEntity
@TypeAlias("PerformanceLog")
public class PerformanceLog extends AbstractLog {

  public long duration;

  public static void create(long duration, LogCategory logCategory, String text) {
    create(duration, logCategory, null, text);
  }

  public static void create(long duration, LogCategory logCategory, Request<AnyContent> request) {
    create(duration, logCategory, request, null);
  }

  public static void create(long duration, LogCategory logCategory, Request<AnyContent> request, String text) {
    if (Global.isPerformanceLogging() == true) {
      PerformanceLog result = new PerformanceLog();
      result.duration = duration;
      abstractCreate(result, logCategory, LogLevel.INFO, request, new LinkedList<AbstractModel>());
      if (text != null) {
        result.httpUrl += " - " + text;
      }
      Neo4JServiceProvider.get().performanceLogRepository.save(result);
    } else {
      String requestString = logCategory.toString();
      if (request != null) {
        requestString += " " + request.uri();
      }
      if (text != null) {
        requestString += " - " + text;
      }
      Logger.info(requestString + " took " + duration + " ms");
    }
  }
}
