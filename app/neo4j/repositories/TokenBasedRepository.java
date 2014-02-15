package neo4j.repositories;

import neo4j.models.TokenBasedModel;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface TokenBasedRepository<T extends TokenBasedModel> extends GraphRepository<T> {

  T findByToken(String token);
}
