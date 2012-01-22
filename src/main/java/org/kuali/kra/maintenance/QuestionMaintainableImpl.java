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
package org.kuali.kra.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.questionnaire.question.QuestionService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class customizes the maintainable class for the question maintenance document.
 */
public class QuestionMaintainableImpl extends KraMaintainableImpl {
    
    private static final long serialVersionUID = 713068582185818373L;
    
    private static final String SEQUENCE_STATUS_ARCHIVED = "A";

    /**
    *
    * @see org.kuali.core.maintenance.Maintainable#prepareForSave()
    */
    @Override
    public void prepareForSave() {
        super.prepareForSave();
       
        if ((businessObject != null) && (businessObject instanceof KraPersistableBusinessObjectBase)) {
            // This is a solution to enable the lookreturn have a proper dropdown list
            if (businessObject instanceof Question) {
                Question question = (Question)businessObject;
                question.setDocumentNumber(getDocumentNumber());
                if (StringUtils.isNotBlank(((Question) businessObject).getLookupClass())) {
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object) question.getLookupClass());
                }
       
                // In order for the questionId to be displayed after a submission of a new question we need to manually create it. 
                if (question.getQuestionId() == null) {
                    Long newQuestionId = KraServiceLocator.getService(SequenceAccessorService.class)
                            .getNextAvailableSequenceNumber("SEQ_QUESTION_ID");
                    question.setQuestionIdFromInteger(newQuestionId.intValue());
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
                if (StringUtils.isBlank((String)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)question.getLookupClass());                    
                }
            }
        }
        
        return new ArrayList<Section>();
    }
    
    /**
     * 
     * @see org.kuali.kra.maintenance.KraMaintainableImpl#handleRouteStatusChange(org.kuali.rice.krad.bo.DocumentHeader)
     */
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
            switch (question.getQuestionTypeId()) {
                case Constants.QUESTION_RESPONSE_TYPE_YES_NO:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setDisplayedAnswers(null);
                    question.setMaxAnswers(null);
                    question.setAnswerMaxLength(null);
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_YES_NO_NA:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setDisplayedAnswers(null);
                    question.setMaxAnswers(null);
                    question.setAnswerMaxLength(null);
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_NUMBER:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_DATE:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    question.setAnswerMaxLength(null);
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_TEXT:
                    question.setLookupClass(null);
                    question.setLookupReturn(null);
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_LOOKUP:
                    question.setDisplayedAnswers(null);
                    question.setAnswerMaxLength(null);
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
        QuestionService questionService = KraServiceLocator.getService(QuestionService.class);
        Question oldQuestion = questionService.getQuestionById(newQuestion.getQuestionId());
        if (oldQuestion != null) {
            oldQuestion.setSequenceStatus(SEQUENCE_STATUS_ARCHIVED);
            getBusinessObjectService().save(oldQuestion);
        }

        super.saveBusinessObject();
    }

}
