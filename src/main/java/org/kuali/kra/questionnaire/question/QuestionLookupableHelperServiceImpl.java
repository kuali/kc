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
package org.kuali.kra.questionnaire.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.CollectionIncomplete;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;

import edu.emory.mathcs.backport.java.util.Collections;

public class QuestionLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = 7936563894902841571L;

    private static final String VIEW = "view";
    
    private QuestionAuthorizationService questionAuthorizationService;

    /**
     * Since Question is being versioned, the lookup should only return active versions of the question
     * (the one with the highest sequenceNumber).
     * 
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        
        List<Question> activeQuestions = getActiveQuestions((List<Question>) super.getSearchResultsUnbounded(fieldValues));

        // Calculate the actualSizeIfTruncated (zero if result is not truncated)
        Long matchingResultsCount = new Long(activeQuestions.size());
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Question.class);
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete(activeQuestions, new Long(0));
        } else {
            return new CollectionIncomplete(trimResult(activeQuestions, searchResultsLimit), matchingResultsCount);
        }
        
    }
    
    /**
     * 
     * This method finds the most up to date questions.
     * @param questions
     * @return questions, a list containing only the most recent versions of the question
     */
    private List<Question> getActiveQuestions(List<Question> questions) {
        List<Question> activeQuestions = new ArrayList<Question>();
        List<Integer> questionIds = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(questions)) {
            Collections.sort(questions, Collections.reverseOrder());
            for (Question question : questions) {
                if (!questionIds.contains(question.getQuestionId())) {
                    activeQuestions.add(question);
                    questionIds.add(question.getQuestionId());
                }
            }
        }
        return activeQuestions;
    }
    
    /**
     * This method trims the search result.
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    private List<Question> trimResult(List<Question> result, Integer trimSize) {
        List<Question> trimedResult = new ArrayList<Question>();
        for (Question question : result) {
            if (trimedResult.size()< trimSize) {
                trimedResult.add(question); 
            }
        }
        return trimedResult;
    }
    
    /**
     * Only display edit, copy and view links for the Questions if proper permission is given.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if(questionAuthorizationService.hasPermission(PermissionConstants.MODIFY_QUESTION)) {
            AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlData.setHref(htmlData.getHref().replace("maintenance", "../maintenance"));
            htmlDataList.add(htmlData);
            AnchorHtmlData htmlData1 = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            htmlData1.setHref(htmlData1.getHref().replace("maintenance", "../maintenance"));
            htmlDataList.add(htmlData1);
        } 
        if (questionAuthorizationService.hasPermission(PermissionConstants.VIEW_QUESTION)) {
            AnchorHtmlData htmlData = new AnchorHtmlData();
            htmlData.setDisplayText(VIEW);
            // TODO: cniesen - populate the URL
            htmlData.setHref("http://www.kuali.org/");
            htmlDataList.add(htmlData);
        }
        return htmlDataList;
    }

    public void setQuestionAuthorizationService(QuestionAuthorizationService questionAuthorizationService) {
        this.questionAuthorizationService = questionAuthorizationService;
    }


}
