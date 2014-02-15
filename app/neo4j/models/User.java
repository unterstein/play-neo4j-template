package neo4j.models;

import helper.HashHelper;
import neo4j.services.Neo4JServiceProvider;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
@TypeAlias("User")
public class User extends AbstractModel {

  @Indexed
  public String email;

  public String password;

  public UserState userState;

  public static User create(String email, String password) {
    User user = new User();
    user.email = email;
    user.password = HashHelper.getInstance().sha(password);
    user.userState = UserState.ACTIVE;
    save(user);
    return user;
  }

  public static User authenticate(String email, String password) {
    String hashedPw = HashHelper.getInstance().sha(password);
    User user = Neo4JServiceProvider.get().userRepository.findByEmail(email);
    if (user == null) {
      return null;
    }
    if (user.password != null && user.password.equals(hashedPw) && user.userState != UserState.HARD_LOCK) {
      return user;
    }
    return null;
  }

  public static boolean checkIfUserExsists(String email) {
    User user = getUserByEMail(email);
    return user != null && user.userState != UserState.HARD_LOCK;
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
}
