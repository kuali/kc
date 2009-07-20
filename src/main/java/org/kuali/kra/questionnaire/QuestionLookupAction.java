/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class QuestionLookupAction extends KualiAction {

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.refresh(mapping, form, request, response);
        QuestionLookupForm questionLookupForm = (QuestionLookupForm) form;
        String questions = Constants.EMPTY_STRING; 
        if (questionLookupForm.getLookupResultsBOClassName() != null && questionLookupForm.getLookupResultsSequenceNumber() != null) {
            String lookupResultsSequenceNumber = questionLookupForm.getLookupResultsSequenceNumber();
            Class<?> lookupResultsBOClass = Class.forName(questionLookupForm.getLookupResultsBOClassName());
            
            Collection<PersistableBusinessObject> rawValues = KraServiceLocator.getService(LookupResultsService.class)
                .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                        new UniversalUser(GlobalVariables.getUserSession().getPerson()).getPersonUserIdentifier());
            int idx = 0;
            String idxString = StringUtils.substringBetween(questionLookupForm.getLookedUpCollectionName(),"[","]");
            if (StringUtils.isNotBlank(idxString)) {
                idx = Integer.parseInt(idxString);
            }
            questionLookupForm.setSelectedQuestions(Constants.EMPTY_STRING);
            for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Question question = (Question) iter.next();
                    String desc = question.getQuestion();
                    // TODO : need to deal with '"' in questio's description 
                    // This '"' caused trouble for document.getElementById("selectedQuestions").value;
                    // It only getvalue up to '"', so not the whole string is returned
                    // <input type="hidden" id="selectedQuestions" name="selectedQuestions" value="${QuestionLookupForm.selectedQuestions}" />
                    // if change value = '${....}', then "'" will cause problem
                    if (desc.indexOf("\"") > 0) {
                        desc = desc.replace("\"", "&#034;");
                    } else if (desc.indexOf("'") > 0) {
                   //     desc = desc.replace("'", "");
                    }
                        if (StringUtils.isBlank(questions)) {
                            questions = question.getQuestionId()+"#f#"+desc+"#f#"+question.getQuestionTypeId();
                        } else {
                            questions = questions + "#q#" +question.getQuestionId()+"#f#"+desc+"#f#"+question.getQuestionTypeId();
                            
                        }
                    }
            questionLookupForm.setLookupResultsSequenceNumber(null);
        }
        
        questionLookupForm.setSelectedQuestions(questions);
        if (StringUtils.isBlank(questions) && StringUtils.isNotBlank(questionLookupForm.getNewQuestion())) {
            // when single lookup return, this refresh will be called too
           forward =  mapping.findForward("singleLookup"); 
        } else {
           forward =  mapping.findForward("multiLookup");       
        }
        return forward;

    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.execute(mapping, form, request, response);
        String lookupType = request.getParameter("lookupType");
        if (StringUtils.isNotBlank(lookupType)) {
            if (lookupType.equals("single")) {
                forward = mapping.findForward("singleLookup");
            } else if (lookupType.equals("multivalue")) {
                forward = mapping.findForward("multiLookup");
            }
        }
        return forward;
    }

}
