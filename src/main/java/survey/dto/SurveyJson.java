package survey.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author felipey.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyJson {

    private String expectationField;
    private String payForField;
    private String needField;

    public String getExpectationField() {
        return expectationField;
    }

    public void setExpectationField(String expectationField) {
        this.expectationField = expectationField;
    }

    public String getPayForField() {
        return payForField;
    }

    public void setPayForField(String payForField) {
        this.payForField = payForField;
    }

    public String getNeedField() {
        return needField;
    }

    public void setNeedField(String needField) {
        this.needField = needField;
    }
}
