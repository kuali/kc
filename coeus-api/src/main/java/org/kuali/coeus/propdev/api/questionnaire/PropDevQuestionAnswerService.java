package org.kuali.coeus.propdev.api.questionnaire;


import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;

import java.util.List;

public interface PropDevQuestionAnswerService {

    /**
     * Gets a list of Answers from a proposal number, namespace, and form name. If none are found then an empty list is
     * returned.  The proposal number, namespace, and form name cannot be blank.
     *
     * @param proposalNumber the proposal number.  cannot be blank.
     * @param namespace the namespace.  cannot be blank.
     * @param formName the form name.  cannot be blank.
     * @return a list of answers or an empty list
     * @throws java.lang.IllegalArgumentException proposal number, namespace, or form name is blank
     */
    List<? extends AnswerContract> getQuestionnaireAnswers(String proposalNumber, String namespace, String formName);

    /**
     * Gets a list of Answer Headers from a proposal number, namespace, and form name. If none are found then an empty list is
     * returned.  The proposal number, namespace, and form name cannot be blank.
     *
     * @param proposalNumber the proposal number.  cannot be blank.
     * @param namespace the namespace.  cannot be blank.
     * @param formName the form name.  cannot be blank.
     * @return a list of answer headers or an empty list
     * @throws java.lang.IllegalArgumentException proposal number, namespace, or form name is blank
     */
    List<? extends AnswerHeaderContract> getQuestionnaireAnswerHeaders(String proposalNumber, String namespace, String formName);

    /**
     * Gets a list of Answer Headers from a proposal number. If none are found then an empty list is
     * returned.  The proposal number cannot be blank.
     *
     * @param proposalNumber the proposal number.  cannot be blank.
     * @return a list of answer headers or an empty list
     * @throws java.lang.IllegalArgumentException proposal number is blank
     */
    List<? extends AnswerHeaderContract> getQuestionnaireAnswerHeaders(String proposalNumber);
}
