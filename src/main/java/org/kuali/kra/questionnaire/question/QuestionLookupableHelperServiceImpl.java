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
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;

import edu.emory.mathcs.backport.java.util.Collections;

public class QuestionLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = 7936563894902841571L;

    /**
     * Since Question is being versioned, the lookup should only return active versions of the question
     * (the one with the highest sequenceNumber).
     * 
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
//    // TODO: cniesen - Code has been temporarily disabled as it does not work correctly.  Until we actually
//    //       version the Question the default getSearchResults() works fine.
//    @Override
//    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
//        
//        return getActiveQuestions(super.getSearchResults(fieldValues));
//    }
    
    @SuppressWarnings("unchecked")
    private List<? extends BusinessObject> getActiveQuestions(List<? extends BusinessObject> searchResults) {

        List<Question> activeQuestions = new ArrayList<Question>();
        List<Integer> questionIds = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(searchResults)) {
            Collections.sort((List<Question>) searchResults, Collections.reverseOrder());
            for (Question question : (List<Question>) searchResults) {
                if (!questionIds.contains(question.getQuestionId())) {
                    activeQuestions.add(question);
                    questionIds.add(question.getQuestionId());
                }
            }
        }
        return activeQuestions;
    }
     
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
        htmlData.setHref(htmlData.getHref().replace("maintenance","../maintenance"));
        htmlDataList.add(htmlData);
        AnchorHtmlData htmlData1 = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
        htmlData1.setHref(htmlData1.getHref().replace("maintenance","../maintenance"));
        htmlDataList.add(htmlData1);
        return htmlDataList;
    }


}
