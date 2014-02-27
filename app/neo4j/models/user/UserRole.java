package neo4j.models.user;

import helper.ViewEnumModel;

import java.io.Serializable;

public enum UserRole implements Serializable {
  ADMIN(40), //
  USER(20), //
  ;

  private int roleNumber;

  private UserRole(int roleNumber) {
    this.roleNumber = roleNumber;
  }

  public static ViewEnumModel enumModel() {
    ViewEnumModel result = new ViewEnumModel();
    for (UserRole value : values()) {
      result.keys.add(value.name());
      result.values.put(value.name(), value.name());
    }
    return result;
  }

  public int getRoleNumber() {
    return roleNumber;
  }
}
