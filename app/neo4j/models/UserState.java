package neo4j.models;

import java.io.Serializable;

public enum UserState implements Serializable {
  ACTIVE, // everything is fine
  SOFT_LOCK, // user can not see results and so on
  HARD_LOCK // user are not allowed to lock in
  ;
}
