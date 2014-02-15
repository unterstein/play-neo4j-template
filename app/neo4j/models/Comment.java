package neo4j.models;

import neo4j.relations.Relations;
import org.springframework.data.neo4j.annotation.RelatedTo;

public class Comment extends AbstractModel {

  public String comment;

  @RelatedTo(type = Relations.COMMENTABLE_COMMENT)
  public CommentAbleModel relatesTo;

}
