/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.questionnaire.framework.answer;

import org.kuali.kra.protocol.questionnaire.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;

import java.util.List;

/**
 * 
 * This class defines the methods for questionnaire answer page.  These methods should
 * be module independent.
 */
public interface QuestionnaireAnswerService {

    public static final String YES = "Y";
    public static final String NO = "N";

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
     * This method update whether a child question is going to be displayed or hidden based on parent's answer and condition. This
     * method is public is purely for 'lookup' question because js 'onchange' is not working when 'lookup' is used.
     * 
     * @param answers
     */
    void setupChildAnswerIndicator(AnswerHeader answerHeader);
    
    /**
     * check if the questionnaire is complete.
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
     * Get the questionnaire usages for a module and submodule.
     * 
     * @param coeusModule the coeus module of the questionnaires you are looking for.
     * @param coeusSubModule the coeus sub-module of the questionnaires you are looking for.
     * @param finalDoc
     * @return
     */
    public List<QuestionnaireUsage> getPublishedQuestionnaire(ModuleQuestionnaireBean moduleQuestionnaireBean);
    
    
    
    /**
     * This method checks if the latest questionnaire instance associated with the given questionnaire ID is active and that it
     * has the given module and sub-module codes in one of its usages.
     */
    boolean checkIfQuestionnaireIsActiveForModule(Integer questionnaireId, String coeusModuleCode, String coeusSubModuleCode);

    boolean checkIfQuestionnaireIsMandatoryForModule(Integer questionnaireId, String coeusModuleCode, String coeusSubModuleCode);

    /**
     * Based on the data in the answer header, rebuild the module specific ModuleQuestionnaireBean. Assumes the document is in a final state.
     * @param answerHeader
     * @return
     */
    ModuleQuestionnaireBean getModuleSpecificBean(AnswerHeader answerHeader);
    
    /**
     * Based on the moduleItemCode and moduleSubItemCode rebuilds the module specific ModuleQuestionnaireBean.
     * @param moduleItemCode
     * @param moduleItemKey
     * @param moduleSubItemCode
     * @param moduleSubItemKey
     * @param finalDoc
     * @return
     */
    ModuleQuestionnaireBean getModuleSpecificBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc);
    

    /**
     * 
     * This method is to get a new version of existing questionnaire answer or set up the associate questionnaire answer for the module keys
     * specified in ModuleQuestionnaireBean.
     * 
     * @param moduleQuestionnaireBean
     * @return
     */
    List<AnswerHeader> getNewVersionOfQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean);

    /**
     * 
     * This method is to get all the questionnaire answers to print for the protocol. 
     * This method is intended to obtain the data that will ultimately be used by the protocol actions, print, questionnaire sub-tab.
     * 
     * @param moduleQuestionnaireBean
     * @param protocolNumber
     * @param questionnaireHelper
     * @return
     */
    List<AnswerHeader> getPrintAnswerHeadersForProtocol(ModuleQuestionnaireBean moduleQuestionnaireBean, String protocolNumber, QuestionnaireHelperBase questionnaireHelper);

}
