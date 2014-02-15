package neo4j.repositories;

import neo4j.models.log.LogCategory;
import neo4j.models.log.LogLevel;
import neo4j.models.log.PlayLog;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PlayLogRepository extends GraphRepository<PlayLog> {

  EndResult<PlayLog> findByLogCategory(LogCategory logCategory);

  EndResult<PlayLog> findByLogLevel(LogLevel logLevel);
}
