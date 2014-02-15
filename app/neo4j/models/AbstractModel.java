package neo4j.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Date;

@NodeEntity
public abstract class AbstractModel {

  @GraphId
  public Long id;

  @CreatedDate
  @GraphProperty(propertyType = Long.class)
  public Date created;

  @LastModifiedDate
  @GraphProperty(propertyType = Long.class)
  private Date lastModifiedDate;

}
