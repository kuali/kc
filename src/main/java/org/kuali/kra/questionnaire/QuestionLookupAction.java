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
package org.kuali.kra.questionnaire;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

/*
 * This class is used for question look in Questionnaire maintenance
 */
public class QuestionLookupAction extends KualiAction {
    private static final String PFP = "#f#";
    private static final String PQP = "#q#";
    private static final String SINGLE_LOOKUP = "singleLookup";
    private static final String MULTI_LOOKUP = "multiLookup";
    private static final String REPLACE_LOOKUP = "replaceLookup";
    
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.refresh(mapping, form, request, response);
        QuestionLookupForm questionLookupForm = (QuestionLookupForm) form;
        String questions = Constants.EMPTY_STRING;
        if (questionLookupForm.getLookupResultsBOClassName() != null && questionLookupForm.getLookupResultsSequenceNumber() != null) {
            String lookupResultsSequenceNumber = questionLookupForm.getLookupResultsSequenceNumber();
            
            @SuppressWarnings("unchecked")
            Class<BusinessObject> lookupResultsBOClass = (Class<BusinessObject>) Class.forName(questionLookupForm.getLookupResultsBOClassName());

            Collection<BusinessObject> rawValues = KraServiceLocator.getService(LookupResultsService.class)
                    .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                            GlobalVariables.getUserSession().getPerson().getPrincipalId());
            int idx = 0;
            String idxString = StringUtils.substringBetween(questionLookupForm.getLookedUpCollectionName(), "[", "]");
            if (StringUtils.isNotBlank(idxString)) {
                idx = Integer.parseInt(idxString);
            }
            questionLookupForm.setSelectedQuestions(Constants.EMPTY_STRING);
            for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                Question question = (Question) iter.next();
                String desc = question.getQuestion();
                // need to deal with '"' in questio's description
                // This '"' caused trouble for document.getElementById("selectedQuestions").value;
                // It only getvalue up to '"', so not the whole string is returned
                if (desc.indexOf("\"") > 0) {
                    desc = desc.replace("\"", "&#034;");
                }
                if (StringUtils.isBlank(questions)) {
                    questions = question.getQuestionRefId() + PFP + desc + PFP + question.getQuestionTypeId() + PFP
                            + question.getSequenceNumber();
                }
                else {
                    questions = questions + PQP + question.getQuestionRefId() + PFP + desc + PFP
                            + question.getQuestionTypeId() + PFP + question.getSequenceNumber();

                }
                questions = questions + PFP + getQuestionResponse(question);
            }
            questionLookupForm.setLookupResultsSequenceNumber(null);
        }

        questionLookupForm.setSelectedQuestions(questions);
        if (questionLookupForm.getNodeIndex() >= 0) {
            // when single lookup return, this refresh will be called too
            forward = mapping.findForward(SINGLE_LOOKUP);
        }
        else if (questionLookupForm.getNodeIndex() == -2) {
            forward = mapping.findForward(REPLACE_LOOKUP);
        }
        else {
            forward = mapping.findForward(MULTI_LOOKUP);
        }
        return forward;

    }

    private String getQuestionResponse(Question question) {
        String retString = "";
        if (question.getQuestionTypeId().equals(new Integer(6))) {
            String className = question.getLookupClass();
            className = className.substring(className.lastIndexOf(".") + 1);
            retString = className + PFP + question.getMaxAnswers() + PFP + question.getLookupReturn();

        }
        else {
            retString = question.getDisplayedAnswers() + PFP + question.getMaxAnswers() + PFP + question.getAnswerMaxLength();
        }
        return retString;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        String lookupType = request.getParameter("lookupType");
        if (StringUtils.isNotBlank(lookupType)) {
            if (lookupType.equals("single")) {
                forward = mapping.findForward(SINGLE_LOOKUP);
            }
            else if (lookupType.equals("multivalue")) {
                forward = mapping.findForward(MULTI_LOOKUP);
            }
            else if (lookupType.equals("replace")) {
                forward = mapping.findForward(REPLACE_LOOKUP);
            }
        }
        return forward;
    }

}
