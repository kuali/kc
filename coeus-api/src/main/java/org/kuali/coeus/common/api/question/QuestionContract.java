package org.kuali.coeus.common.api.question;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

import java.util.List;

public interface QuestionContract extends IdentifiableNumeric {

    String getDocumentNumber();
    String getQuestionSeqId();
    Integer getSequenceNumber();
    String getSequenceStatus();
    String getQuestion();
    String getStatus();
    String getLookupClass();
    String getLookupReturn();
    Integer getDisplayedAnswers();
    Integer getMaxAnswers();
    Integer getAnswerMaxLength();

    QuestionCategoryContract getQuestionCategory();

    QuestionTypeContract getQuestionType();

    List<? extends QuestionExplanationContract> getQuestionExplanations();
}
