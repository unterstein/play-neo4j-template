package neo4j.repositories;

import neo4j.models.user.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User> {

  public User findByEmail(String eMail);
}
