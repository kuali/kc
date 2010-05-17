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
package org.kuali.kra.questionnaire.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * Question specific lookupable helper service methods.
 */
public class QuestionLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = 7936563894902841571L;

    private static final String MAINTENANCE = "maintenance";
    private static final String NEW_MAINTENANCE = "../maintenance";
    private static final String VIEW = "view";
    private static final String SEQUENCE_STATUS_CURRENT = "C";

    
    private transient QuestionAuthorizationService questionAuthorizationService;

    /**
     * Since Question is being versioned, the lookup should only return active versions of the question
     * (the one with the highest sequenceNumber).
     * 
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        fieldValues.put("sequenceStatus", SEQUENCE_STATUS_CURRENT);
        return super.getSearchResults(fieldValues);
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
            htmlData.setHref(htmlData.getHref().replace(MAINTENANCE, NEW_MAINTENANCE));
            htmlDataList.add(htmlData);

            AnchorHtmlData htmlData1 = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            htmlData1.setHref(htmlData1.getHref().replace(MAINTENANCE, NEW_MAINTENANCE));
            htmlDataList.add(htmlData1);

            AnchorHtmlData htmlData2 = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlData2.setHref(htmlData2.getHref().replace(MAINTENANCE, NEW_MAINTENANCE) + "&readOnly=true");
            htmlData2.setDisplayText(VIEW);
            htmlDataList.add(htmlData2);
        } 
        if (questionAuthorizationService.hasPermission(PermissionConstants.VIEW_QUESTION)) {
            AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlData.setHref(htmlData.getHref().replace(MAINTENANCE, NEW_MAINTENANCE) + "&readOnly=true");
            htmlData.setDisplayText(VIEW);
            htmlDataList.add(htmlData);
        }
        return htmlDataList;
    }

    public void setQuestionAuthorizationService(QuestionAuthorizationService questionAuthorizationService) {
        this.questionAuthorizationService = questionAuthorizationService;
    }


}
