/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.kns.web.ui.Section;

/**
 * This class customizes the maintainable class for the question maintenance document.
 */
public class QuestionMaintainableImpl extends KraMaintainableImpl {
    
    private static final long serialVersionUID = 713068582185818373L;

    /**
    *
    * @see org.kuali.core.maintenance.Maintainable#prepareForSave()
    */
   @Override
   public void prepareForSave() {
       super.prepareForSave();
       
       if ((businessObject != null) && (businessObject instanceof KraPersistableBusinessObjectBase)) {
           // This is a solution to enable the lookreturn have a proper dropdown list
           if (businessObject instanceof Question && StringUtils.isNotBlank(((Question)businessObject).getLookupClass())) {
               GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)((Question)businessObject).getLookupClass());
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
        if (businessObject instanceof Question) {
            if (GlobalVariables.getKualiForm() != null && GlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
                Question question = (Question)((MaintenanceDocumentBase)((KualiMaintenanceForm)GlobalVariables.getKualiForm()).getDocument()).getDocumentBusinessObject();
                if (StringUtils.isNotBlank(question.getLookupClass())) {
                    if (StringUtils.isBlank((String)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                        GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)question.getLookupClass());                    
                    }
                }
            }
        }
        
        return new ArrayList<Section>();
    }
    
    /**
     * 
     * @see org.kuali.kra.maintenance.KraMaintainableImpl#handleRouteStatusChange(org.kuali.rice.kns.bo.DocumentHeader)
     */
    @Override
    public void handleRouteStatusChange(DocumentHeader documentHeader) {
        clearUnusedFieldValues();
    }
    
    /**
     * This method sets the unused fields of the question response type to null.
     */
    private void clearUnusedFieldValues() {
        if (businessObject instanceof Question) {
            Question question = (Question) businessObject;
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

}
