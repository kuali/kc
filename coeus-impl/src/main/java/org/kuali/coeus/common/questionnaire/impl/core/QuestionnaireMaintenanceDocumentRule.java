/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is to provide rule validation when questionnaire is to be approved.
 */
public class QuestionnaireMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    public static final String ALREADY_EDITED_ERROR = "error.questionnaire.alreadyEdited";


    public QuestionnaireMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
            return validateQuestionnaire(document);
    }
    
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
            return validateQuestionnaire(document);
    }

    /**
     * 
     * This method to check whether if the required fields are ok and whether name exists.
     * @param maintenanceDocument
     * @return
     */
    private boolean validateQuestionnaire(MaintenanceDocument maintenanceDocument) {


        boolean valid = true;
        Questionnaire newQuestionnaire = (Questionnaire)maintenanceDocument.getNewMaintainableObject().getDataObject();
        MessageMap errorMap = GlobalVariables.getMessageMap();
        if (StringUtils.isBlank(newQuestionnaire.getName())) {
            errorMap.putError("document.newMaintainableObject.businessObject.name", RiceKeyConstants.ERROR_REQUIRED, "Questionnaire Name");
            valid = false;
        }
        if (StringUtils.isBlank(newQuestionnaire.getDescription())) {
            errorMap.putError("document.newMaintainableObject.businessObject.description", RiceKeyConstants.ERROR_REQUIRED, "Questionnaire Description");
            valid = false;
        }

        if (getQuestionnaireService().isQuestionnaireNameExist(newQuestionnaire.getQuestionnaireSeqId(), newQuestionnaire.getName())) {
            errorMap.putError("document.newMaintainableObject.businessObject.name", KeyConstants.ERROR_QUESTIONNAIRE_NAME_EXIST);
            valid = false;

        }
        
        if (StringUtils.isNotBlank(newQuestionnaire.getFileName()) && 
            !StringUtils.endsWithIgnoreCase(newQuestionnaire.getFileName(), ".xsl")) {
            errorMap.putError("document.newMaintainableObject.businessObject.fileName", KeyConstants.ERROR_QUESTIONNAIRE_FILENAME_INVALID);
            valid = false;
        }
        
        for (QuestionnaireQuestion questionnaireQuestion : newQuestionnaire.getQuestionnaireQuestions()) {
            if ((questionnaireQuestion.getQuestion() != null) && ("I".equals(questionnaireQuestion.getQuestion().getStatus()))) {
                errorMap.putError("document.newMaintainableObject.businessObject.question" 
                        + newQuestionnaire.getQuestionnaireQuestions().indexOf(questionnaireQuestion)
                        , KeyConstants.ERROR_QUESTIONNAIRE_QUESTION_INACTIVE, questionnaireQuestion.getQuestion().getQuestion());
                valid = false;
            }
        }
        
        if (newQuestionnaire.getObjectId() != null 
                && checkForLatestQuuestionnaireSequenceNumber(newQuestionnaire.getId(), newQuestionnaire.getSequenceNumber())) {
            errorMap.putError("document.newMaintainableObject.businessObject.name", ALREADY_EDITED_ERROR, "");
            valid = false;
        }
        
        for (QuestionnaireUsage usage : newQuestionnaire.getQuestionnaireUsages()) {
            if (usage.getCoeusSubModule() != null 
                    && usage.getCoeusSubModule().isRequireUniqueQuestionnareUsage()
                    && !getQuestionnaireService().isUniqueUsage(newQuestionnaire, usage)) {
                errorMap.putError("document.newMaintainableObject.businessObject.questionnaireUsages[" + 
                                   newQuestionnaire.getQuestionnaireUsages().indexOf(usage) + "].moduleSubItemCode", 
                        KeyConstants.ERROR_QUESTIONNAIRE_DUPLICATE_USAGE);
            }
        }
        
        
        return valid;

    }
    
    /**
     * 
     * This method determines if the passed in sequence number is the latest sequence for the passed in questionnaireId.
     * @param questionnnaireId
     * @param sequenceNumber
     * @return
     */
    protected boolean checkForLatestQuuestionnaireSequenceNumber(Long id, Integer sequenceNumber) {
        Map fieldValues = new HashMap();
        fieldValues.put(QuestionnaireConstants.QUESTIONNAIRE_ID_PARAMETER_NAME, id);
        boolean sortAscending = true;
        Collection<Questionnaire> questionnaires = getBusinessObjectService().findMatchingOrderBy(Questionnaire.class, fieldValues, "SEQUENCE_NUMBER", sortAscending);
        boolean foundCurrentSequence = false;
        boolean foundLargerSequence = false;
        for (Questionnaire questionnarire : questionnaires) {
            if (questionnarire.getSequenceNumber().equals(sequenceNumber)) {
                foundCurrentSequence = true;
            }
            if (questionnarire.getSequenceNumber() > sequenceNumber) {
                foundLargerSequence = true;
            }
        }
        return foundCurrentSequence && !foundLargerSequence;
    }
    
    private QuestionnaireService getQuestionnaireService() {
        return KcServiceLocator.getService(QuestionnaireService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

}
