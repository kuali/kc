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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionExplanation;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionMultiChoice;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class customizes the maintainable class for the question maintenance document.
 */
public class QuestionMaintainableImpl extends KraMaintainableImpl {
    
    private static final long serialVersionUID = 713068582185818373L;
    
    private static final String SEQUENCE_STATUS_ARCHIVED = "A";

    private transient BusinessObjectService businessObjectService;

    @Override
    public void prepareForSave() {
        super.prepareForSave();
       
        if ((businessObject != null) && (businessObject instanceof KcPersistableBusinessObjectBase)) {
            // This is a solution to enable the lookreturn have a proper dropdown list
            if (businessObject instanceof Question) {
                Question question = (Question)businessObject;
                question.setDocumentNumber(getDocumentNumber());
                if (StringUtils.isNotBlank(((Question) businessObject).getLookupClass())) {
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object) question.getLookupClass());
                }
       
                // In order for the questionId to be displayed after a submission of a new question we need to manually create it. 
                if (question.getQuestionSeqId() == null) {
                    Long newQuestionId = KcServiceLocator.getService(SequenceAccessorService.class)
                            .getNextAvailableSequenceNumber(QuestionnaireConstants.DB_SEQUENCE_NAME_QUESTION_SEQ_ID, question.getClass());
                    question.setQuestionSeqId(newQuestionId.intValue());
                }
            }
        }
    }

    /**
     * Normally the parent method builds the maintenance sections from the data dictionary.  But since
     * we want full control over the screen layout we disable the automatic build by overriding the
     * method and returning an empty list of sections.
     *
     * @see org.kuali.kra.maintenance.KraMaintainableImpl#getSections(org.kuali.rice.kns.document.MaintenanceDocument, org.kuali.rice.kns.maintenance.Maintainable)
     */
    @Override
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {

        // This is a solution to enable the lookreturn have a proper dropdown list
        if (KNSGlobalVariables.getKualiForm() != null && KNSGlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
            Question question = (Question)((MaintenanceDocumentBase)((KualiMaintenanceForm)KNSGlobalVariables.getKualiForm()).getDocument()).getDocumentBusinessObject();
            if (StringUtils.isNotBlank(question.getLookupClass())) {
                if (StringUtils.isBlank((String) GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object) question.getLookupClass());
                }
            }
        }
        
        return new ArrayList<Section>();
    }
    
    @Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {
        clearUnusedFieldValues();
    }
    
    /**
     * This method sets the unused fields of the question response type to null.
     */
    private void clearUnusedFieldValues() {
        Question question = (Question) businessObject;
        if (question.getQuestionType() != null) {
            switch (question.getQuestionTypeId().intValue()) {
                case (int) Constants.QUESTION_RESPONSE_TYPE_YES_NO:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setDisplayedAnswers(null);
                    question.setMaxAnswers(null);
                    question.setAnswerMaxLength(null);
                    question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_YES_NO_NA:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setDisplayedAnswers(null);
                    question.setMaxAnswers(null);
                    question.setAnswerMaxLength(null);
                    question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_NUMBER:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_DATE:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setAnswerMaxLength(null);
                    question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_TEXT:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_LOOKUP:
                    question.setDisplayedAnswers(null);
                    question.setAnswerMaxLength(null);
                    question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE:
                    question.setDisplayedAnswers(question.getQuestionMultiChoices().size());
                    break;
            }
        }
    }

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.KualiMaintainableImpl#saveBusinessObject()
     * 
     * This method will set the sequence status of the original question from current to
     * archived before the revision for the question is saved.
     */
    public void saveBusinessObject() {
        Question newQuestion = (Question) businessObject;
        QuestionService questionService = KcServiceLocator.getService(QuestionService.class);
        Question oldQuestion = questionService.getQuestionByQuestionSequenceId(newQuestion.getQuestionSeqId());
        for (QuestionMultiChoice choice : newQuestion.getQuestionMultiChoices()) {
            choice.setQuestionId(newQuestion.getId());
        }

        if (oldQuestion != null && !oldQuestion.getId().equals(newQuestion.getId())) {
            oldQuestion.setSequenceStatus(SEQUENCE_STATUS_ARCHIVED);
            KNSServiceLocator.getBusinessObjectService().save(oldQuestion);
        }

        super.saveBusinessObject();
    }

    @Override
    public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parameters) {
        super.processAfterCopy(document, parameters);
        final Question newQuestion = (Question) document.getNewMaintainableObject().getBusinessObject();
        newQuestion.setQuestionSeqId(null);
        newQuestion.setSequenceNumber(1);

        for (QuestionMultiChoice choice : newQuestion.getQuestionMultiChoices()) {
            choice.setQuestionId(null);
            choice.setId(null);
        }

        for (QuestionExplanation questionExplanation : newQuestion.getQuestionExplanations()) {
            questionExplanation.setQuestionId(null);
            questionExplanation.setId(null);
        }
    }

    @Override
    public void deleteBusinessObject() {
        if (businessObject == null) {
            return;
        }

        handleRelationshipsForDelete((Question) businessObject);

        super.deleteBusinessObject();
    }

    //this is required because deleteBusinessObject is never called by rice even though Question is not a DataObject
    @Override
    public void deleteDataObject() {
        final Object dataObject = getDataObject();

        if (dataObject == null) {
            return;
        }

        handleRelationshipsForDelete((Question) dataObject);

        super.deleteDataObject();
    }

    private void handleRelationshipsForDelete(Question question) {
        if (question.getQuestionMultiChoices() != null && !question.getQuestionMultiChoices().isEmpty()) {
            getBusinessObjectService().delete(question.getQuestionMultiChoices());
        }

        if (question.getQuestionExplanations() != null && !question.getQuestionExplanations().isEmpty()) {
            getBusinessObjectService().delete(question.getQuestionExplanations());
        }
    }


    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
