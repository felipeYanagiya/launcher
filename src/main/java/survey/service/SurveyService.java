package survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import survey.dto.Survey;
import survey.dto.SurveyJson;
import survey.repository.SurveyRepository;

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
        Assert.notNull(json, "survey json should not be null!");
    }

    Survey getSurvey(SurveyJson json) {
        Survey survey = new Survey();
        survey.setExpectation(json.getExpectationField());
        survey.setNeed(json.getNeedField());
        survey.setPayFor(json.getPayForField());

        return survey;
    }

}
