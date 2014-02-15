package neo4j.models.log;

import java.io.Serializable;

public enum LogLevel implements Serializable {
  INFO, IMPORTANT, WARN, ERROR;
}
