package boot.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import boot.survey.dto.Survey;
import boot.survey.dto.SurveyJson;
import boot.survey.repository.SurveyRepository;

/**
 * @author felipey.
 */
@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    public List<Survey> findAll() {
      return (List<Survey>) repository.findAll();
    }

    public void saveSurvey(SurveyJson json) {
        validateJson(json);
        Survey survey = getSurvey(json);

        repository.save(survey);
    }

    private void validateJson(SurveyJson json) {
        Assert.notNull(json, "boot.survey json should not be null!");
    }

    Survey getSurvey(SurveyJson json) {
        Survey survey = new Survey();
        survey.setExpectation(json.getExpectationField());
        survey.setNeed(json.getNeedField());
        survey.setPayFor(json.getPayForField());

        return survey;
    }

}
