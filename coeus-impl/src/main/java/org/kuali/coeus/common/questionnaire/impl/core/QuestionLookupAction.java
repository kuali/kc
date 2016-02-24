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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;

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

            Collection<BusinessObject> rawValues = KcServiceLocator.getService(LookupResultsService.class)
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
                    questions = question.getId() + PFP + desc + PFP + question.getQuestionTypeId() + PFP
                            + question.getSequenceNumber();
                }
                else {
                    questions = questions + PQP + question.getId() + PFP + desc + PFP
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
