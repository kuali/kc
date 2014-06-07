package org.kuali.coeus.common.api.ynq;


public interface YnqExplanationContract {

    String getQuestionId();

    String getExplanation();

    YnqExplanationTypeContract getYnqExplanationType();
}
