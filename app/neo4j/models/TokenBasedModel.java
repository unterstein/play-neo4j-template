package neo4j.models;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public abstract class TokenBasedModel extends AbstractModel {

  @Indexed
  public String token;
}
