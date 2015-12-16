package boot.survey.repository;

import org.springframework.data.repository.CrudRepository;

import boot.survey.dto.Survey;

/**
 * @author felipey.
 */
public interface SurveyRepository extends CrudRepository<Survey, Long> {

}
