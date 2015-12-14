package survey.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import survey.dto.Survey;
import survey.dto.SurveyJson;
import survey.service.SurveyService;

/**
 * @author felipey.
 */
@Component
public class SurveyFacade {

    @Autowired
    private SurveyService service;

    @RequestMapping("/survey")
    public void saveSurveyFromUser(@RequestBody(required = true) SurveyJson surveyJson) {

    }

    @RequestMapping("/get-survey")
    public List<SurveyJson> getAllSurveys() {
        List<Survey> surveyList = service.findAll();

        return getSurveyJson(surveyList);
    }

    public List<SurveyJson> getSurveyJson(List<Survey> surveyList) {
        if (surveyList.isEmpty()) {
            return Lists.newArrayList();
        }

        return FluentIterable.from(surveyList).transform(new Function<Survey, SurveyJson>() {
            @Override
            public SurveyJson apply(Survey survey) {
                SurveyJson json = new SurveyJson();
                json.setExpectationField(survey.getExpectation());
                json.setNeedField(survey.getNeed());
                json.setPayForField(survey.getPayFor());
                return json;
            }
        }).toList();
    }

}
