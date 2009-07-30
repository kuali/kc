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

import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;

public class QuestionLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = 7936563894902841571L;

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
