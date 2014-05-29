package org.kuali.coeus.common.api.question;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface QuestionExplanationContract extends IdentifiableNumeric {

    Long getQuestionId();

    String getExplanationType();

    String getExplanation();
}
