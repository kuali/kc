/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.questionnaire.answer;

import java.util.List;

import org.kuali.kra.questionnaire.Questionnaire;

/**
 * 
 * This class defines the methods for questionnaire answer page.  These methods should
 * be module independent.
 */
public interface QuestionnaireAnswerService {


    /**
     * 
     * This method is to get existing questionnaire answer or set up the associate questionnaire answer for the module keys
     * specified in ModuleQuestionnaireBean.
     * 
     * @param moduleQuestionnaireBean
     * @return
     */
    List<AnswerHeader> getQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean);

    /**
     * 
     * This method to create new questionnaire answer header for the new version questionnaire if user selected not to copy answers
     * from old version.
     * 
     * @param moduleQuestionnaireBean
     * @param questionnaire
     * @return
     */
    AnswerHeader getNewVersionAnswerHeader(ModuleQuestionnaireBean moduleQuestionnaireBean, Questionnaire questionnaire);

    /**
     * 
     * This method is to update questionnaire answers to new version and also copy the answers from the olld version if question is
     * in not updated to new version.
     * 
     * @param oldAnswerHeader
     * @param newAnswerHeader
     */
    void copyAnswerToNewVersion(AnswerHeader oldAnswerHeader, AnswerHeader newAnswerHeader);

    /**
     * 
     * This method is to copy questionnaire from old protocol to the new protocol version.
     * 
     * @param moduleQuestionnaireBean
     * @return
     */
    List<AnswerHeader> versioningQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean, Integer sequenceNumber);

    /**
     * 
     * This method is move answer for multiple answer question if needed and also update 'completed' flag.
     * 
     * @param answerHeaders
     */
    void preSave(List<AnswerHeader> answerHeaders);

    /**
     * 
     * This method update whether a child question is going to be displayed or hidden based on parent's answer and condision. This
     * method is public is purely for 'lookup' question because js 'onchange' is not working when 'lookup' is used.
     * 
     * @param answers
     */
    void setupChildAnswerIndicator(List<Answer> answers);


}
