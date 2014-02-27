package neo4j.models.user;

import helper.ViewEnumModel;

import java.io.Serializable;

public enum UserState implements Serializable {
  ACTIVE, // everything is fine
  LOCK // user are not allowed to lock in
  ;

  public static ViewEnumModel enumModel() {
    ViewEnumModel result = new ViewEnumModel();
    for (UserState value : values()) {
      result.keys.add(value.name());
      result.values.put(value.name(), value.name());
    }
    return result;
  }
}
