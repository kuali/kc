package org.kuali.coeus.common.questionnaire.api.question;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface QuestionExplanationContract extends IdentifiableNumeric {

    Long getQuestionId();

    String getExplanationType();

    String getExplanation();
}
