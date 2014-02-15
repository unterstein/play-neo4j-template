package neo4j.models.log;

import java.io.Serializable;

public enum LogCategory implements Serializable {
  // ------------------------ functional
  // sessions
  SESSION,
  // done by admin
  ADMIN,

  // ------------------------ technical
  JOBS, MAIL, SMSINTERNAL_ERROR, REQUEST, DB;
}
