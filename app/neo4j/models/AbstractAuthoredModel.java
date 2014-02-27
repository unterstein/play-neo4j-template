package neo4j.models;

import neo4j.models.user.User;
import neo4j.relations.Relations;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class AbstractAuthoredModel extends AbstractModel {

  @RelatedTo(type = Relations.MODEL_AUTHOR)
  public User author;
}
