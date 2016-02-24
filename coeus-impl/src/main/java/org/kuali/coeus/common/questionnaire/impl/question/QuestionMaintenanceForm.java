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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionMultiChoice;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.collect.CollectionUtils;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionMaintenanceForm extends KualiMaintenanceForm {

    private static final Pattern INDEX_PATTERN = Pattern.compile("(document.newMaintainableObject.businessObject.questionMultiChoices\\Q[\\E)(\\d+)(\\Q]\\E.+)");
    private QuestionMultiChoice newQuestionMultiChoice;
    private static final long serialVersionUID = -627714142076110160L;

    /**
     * This method disables the caching of drop down lists.  
     * Question maintenance has a drop down list whose value depends on another drop down list.  With caching enabled the
     * drop down list will always be empty.  Disabling caching will reload the drop down list whenever the page is posted.
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {
        final int index = getLargestMultiChoiceIndex(CollectionUtils.toIterable(request.getParameterNames()));
        if (index != -1 && getDocument() != null) {
            final Question question = (Question) ((MaintenanceDocument) getDocument()).getNewMaintainableObject().getBusinessObject();
            question.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
            for (int i = question.getQuestionMultiChoices().size(); i <= index; i++) {
                question.getQuestionMultiChoices().add(new QuestionMultiChoice());
            }
        }

        super.populate(request);
        if (getActionFormUtilMap() != null && getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        }

        if (getDocument() != null) {
            final Question question = (Question) ((MaintenanceDocument) getDocument()).getNewMaintainableObject().getBusinessObject();
            if (Long.valueOf(Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE).equals(question.getQuestionTypeId())) {
                question.setDisplayedAnswers(question.getQuestionMultiChoices().size());
            }
        }
    }

    @Override
    public boolean isPropertyEditable(String propertyName) {
        //this is needed because we are adding input boxes dynamically.  Input must be registered on the server side to be editable.
        if (propertyName.startsWith("document.newMaintainableObject.businessObject.questionMultiChoices")) {
            return super.isPropertyEditable("document.newMaintainableObject.businessObject.questionMultiChoices");
        }

        return super.isPropertyEditable(propertyName);
    }

    /**
     * This method takes a list of parameter names searching for the largest multi choice index number.
     * @return the largest index or -1 if none found
     */
    private int getLargestMultiChoiceIndex(Iterable<String> parameterNames) {
        int i = -1;
        for (String name : parameterNames) {
            final Matcher indexMatcher = INDEX_PATTERN.matcher(name);
            if (indexMatcher.matches() && indexMatcher.groupCount() > 1) {
                int index = Integer.valueOf(indexMatcher.group(2));
                if (index > i) {
                    i = index;
                }
            }
        }
        return i;
    }

    /**
     * override this for view bootstrap data.  A new doc is initiated in this case.
     * this will make the document header looks 'Final'.
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#populateHeaderFields(org.kuali.rice.kew.api.WorkflowDocument)
     */
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        
        super.populateHeaderFields(workflowDocument);
        // readOnly is changing several times during load.  so better with this approach
        if (this.isReadOnly() && workflowDocument.isInitiated()) {
            getDocInfo().clear();
        }
    }

    public QuestionMultiChoice getNewQuestionMultiChoice() {
        return newQuestionMultiChoice;
    }

    public void setNewQuestionMultiChoice(QuestionMultiChoice newQuestionMultiChoice) {
        this.newQuestionMultiChoice = newQuestionMultiChoice;
    }
}
