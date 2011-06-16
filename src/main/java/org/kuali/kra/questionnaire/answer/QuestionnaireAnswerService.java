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
import org.kuali.kra.questionnaire.QuestionnaireUsage;

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
    
    /**
     * check if the questionnaire is complete.
     * This method...
     * @param answers
     * @return
     */
    boolean isQuestionnaireAnswerComplete(List<Answer> answers);

    /**
     * Copy all of the answer headers associated with a source ModuleQuestionnaireBean and associate them with a destination ModuleQuestionnaire Bean.
     * This method persists the new AnswerHeader objects it creates. 
     * 
     * @param srcModuleQuestionnaireBean the ModulQuestionnaireBean containing the data pointing to the source questionnaires.
     * @param newModuleQuestionnaireBean the ModuleQuestionnaireBean you would like to copy the AnswerHeader objects to.
     * 
     * @return a list of AnswerHeader objects.
     */
    public List<AnswerHeader> copyAnswerHeaders(ModuleQuestionnaireBean srcModuleQuestionnaireBean, ModuleQuestionnaireBean newModuleQuestionnaireBean);

    /**
     * 
     * This method is to get all the questionnaire answer for the protocol.
     * Questionnaire answer may contain submit questionnaire, request submission questionnaire, amendment/renewal questionnaire.
     * @param protocolNumber
     * @return
     */
    List<AnswerHeader> getAnswerHeadersForProtocol(String protocolNumber);
    
    
    /**
     * Get the questionnaire usages for a module and submodule.
     * 
     * @param coeusModule the coeus module of the questionnaires you are looking for.
     * @param coeusSubModule the coeus sub-module of the questionnaires you are looking for.
     * @param finalDoc
     * @return
     */
    public List<QuestionnaireUsage> getPublishedQuestionnaire(String coeusModule, String coeusSubModule, boolean finalDoc);
    
    
    
    /**
     * This method checks if the latest questionnaire instance associated with the given questionnaire ID is active, and also
     * has the given module and sub-module codes in one of its usages.
     * 
     * @param questionnaireId
     * @param coeusModule
     * @param coeusSubModule
     * @return
     */
    public boolean checkIfQuestionnaireIsActiveForModule(Integer questionnaireId, String coeusModuleCode, String coeusSubModuleCode);
}
