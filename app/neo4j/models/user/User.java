package neo4j.models.user;

import global.Global;
import helper.HashHelper;
import neo4j.models.CommentAbleModel;
import neo4j.relations.Relations;
import neo4j.services.Neo4JServiceProvider;
import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.LinkedList;
import java.util.List;

@NodeEntity
@TypeAlias("User")
public class User extends CommentAbleModel {

  @Indexed
  public String email;

  public String name;

  public String password;

  public UserState userState;

  public UserRole userRole;

  public static User create(String email, String password, User creator) {
    User user = new User();
    user.email = email;
    user.password = HashHelper.getInstance().sha(password);
    user.userState = UserState.ACTIVE;
    user.userRole = UserRole.USER;
    user.author = creator;
    save(user);
    return user;
  }

  public static User authenticate(String email, String password) {
    String hashedPw = HashHelper.getInstance().sha(password);
    User user = Neo4JServiceProvider.get().userRepository.findByEmail(email);
    if (user == null) {
      return null;
    }
    if (user.password != null && user.password.equals(hashedPw) && user.userState != UserState.LOCK) {
      return user;
    }
    return null;
  }

  public static boolean checkIfUserExsists(String email) {
    User user = getUserByEMail(email);
    return user != null && user.userState != UserState.LOCK;
  }

  public static User getUserByEMail(String email) {
    if (email == null || email.trim().length() == 0) {
      return null;
    }
    return Neo4JServiceProvider.get().userRepository.findByEmail(email);
  }

  public static void save(User user) {
    Neo4JServiceProvider.get().userRepository.save(user);
  }

  public boolean hasMinRole(UserRole userRole) {
    return this.userRole.getRoleNumber() >= userRole.getRoleNumber();
  }

  @Override
  public String toString() {
    String result = "";
    if (name != null) {
      result += name + " (";
    }
    result += email;
    if (name != null) {
      result += ")";
    }
    return result;
  }
}
