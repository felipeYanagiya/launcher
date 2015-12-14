package survey.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import survey.dto.Survey;
import survey.dto.SurveyJson;
import survey.repository.SurveyRepository;

@RunWith(MockitoJUnitRunner.class)
public class SurveyTest {

    @InjectMocks
    private SurveyService service;

    @Mock
    private SurveyRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(service);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveWithNullJsonShouldThrowException() {

        service.saveSurvey(null);
        fail();
    }

    @Test
    public void saveShouldAddToRepository() {

        service.saveSurvey(getJson());

        verify(repository, times(1)).save(any(Survey.class));

    }

    @Test
    public void findAll() {
        when(repository.findAll()).thenReturn(Lists.newArrayList(service.getSurvey(getJson())));

        List<Survey> surveyList = service.findAll();

        assertNotNull(surveyList);
        assertFalse(surveyList.isEmpty());
        assertEquals(1, surveyList.size());

        assertEquals("exp", Iterables.getFirst(surveyList, null).getExpectation());

    }

    private SurveyJson getJson() {
        SurveyJson json = new SurveyJson();
        json.setExpectationField("exp");
        json.setNeedField("need");
        json.setPayForField("pay");
        return json;
    }

}
