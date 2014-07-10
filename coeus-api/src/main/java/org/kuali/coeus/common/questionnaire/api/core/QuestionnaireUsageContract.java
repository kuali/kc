package org.kuali.coeus.common.questionnaire.api.core;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface QuestionnaireUsageContract extends IdentifiableNumeric{

    public String getModuleItemCode();

    public String getModuleSubItemCode();

    public Long getQuestionnaireId();

    public String getRuleId();

    public String getQuestionnaireLabel();

    public Integer getQuestionnaireSequenceNumber();

    public boolean isMandatory();
}
