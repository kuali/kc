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
package org.kuali.kra.common.notification;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;

public class NotificationLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8081840497073635509L;
    
    private static final String VIEW = "view";

    
    private NotificationAuthorizationService notificationAuthorizationService;

    /**
     * Only display edit, copy and view links for the Questions if proper permission is given.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        boolean hasModifyPermission = getNotificationAuthorizationService().hasPermission(PermissionConstants.MODIFY_NOTIFICATION);
        boolean hasViewPermission = hasModifyPermission || getNotificationAuthorizationService().hasPermission(PermissionConstants.VIEW_NOTIFICATION);
        if (hasModifyPermission) {
            AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlDataList.add(htmlData);

            AnchorHtmlData htmlData1 = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            htmlDataList.add(htmlData1);
        } 
        // if user can view question, then if doc number exists, use doc service to view, otherwise open for editing in read-only mode
        if (hasViewPermission) {
            AnchorHtmlData htmlData2 = new AnchorHtmlData();
            htmlData2 = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlData2.setDisplayText(VIEW);
            htmlDataList.add(htmlData2);
        }
        return htmlDataList;
    }
    
    public NotificationAuthorizationService getNotificationAuthorizationService() {
        return notificationAuthorizationService;
    }

    public void setNotificationAuthorizationService(NotificationAuthorizationService notificationAuthorizationService) {
        this.notificationAuthorizationService = notificationAuthorizationService;
    }

    
    
}
