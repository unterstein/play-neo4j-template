package neo4j.models;

import java.io.Serializable;

public enum UserState implements Serializable {
  ACTIVE, // everything is fine
  LOCK // user are not allowed to lock in
  ;
}
