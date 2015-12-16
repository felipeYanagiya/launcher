package boot.survey.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import boot.survey.dto.Survey;
import boot.survey.dto.SurveyJson;
import boot.survey.service.SurveyService;

/**
 * @author felipey.
 */
@RestController
@RequestMapping("/survey")
public class SurveyFacade {

    @Autowired
    private SurveyService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void saveSurveyFromUser(@RequestBody(required = true) SurveyJson surveyJson) {
        Assert.notNull(surveyJson, "json should not be null!");

        service.saveSurvey(surveyJson);
    }

    @RequestMapping(value = "/get-survey", method = RequestMethod.GET)
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
