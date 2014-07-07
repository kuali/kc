package org.kuali.coeus.common.questionnaire.api.core;

import org.kuali.coeus.sys.api.model.*;

import java.util.List;

public interface QuestionnaireContract extends IdentifiableNumeric, Describable, Inactivatable, Named {

    public String getQuestionnaireSeqId();

    public Integer getSequenceNumber();

    public String getDocumentNumber();

    public String getFileName();

    public byte[] getTemplate();

    public List<? extends QuestionnaireQuestionContract> getQuestionnaireQuestions();

    public List<? extends QuestionnaireUsageContract> getQuestionnaireUsages();
}
