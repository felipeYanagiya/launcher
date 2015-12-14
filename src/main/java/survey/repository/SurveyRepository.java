package survey.repository;

import org.springframework.data.repository.CrudRepository;

import survey.dto.Survey;

/**
 * @author felipey.
 */
public interface SurveyRepository extends CrudRepository<Survey, Long> {

}
