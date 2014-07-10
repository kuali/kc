package org.kuali.coeus.common.questionnaire.api.answer;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

import java.util.List;

public interface AnswerContract extends IdentifiableNumeric {

    Integer getQuestionNumber();

    Integer getAnswerNumber();

    String getAnswer();

    Long getAnswerHeaderId();

    Long getQuestionId();

    Long getQuestionnaireQuestionsId();

    List<? extends AnswerContract> getParentAnswers();
}
