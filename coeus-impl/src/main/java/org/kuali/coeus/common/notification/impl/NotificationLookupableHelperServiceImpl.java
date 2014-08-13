/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.notification.impl;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.ArrayList;
import java.util.List;

public class NotificationLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    private KcNotificationAuthorizationService kcNotificationAuthorizationService;

    /**
     * Only display edit, copy and view links for the Questions if proper permission is given.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        boolean hasModifyPermission = getKcNotificationAuthorizationService().hasPermission(PermissionConstants.MODIFY_NOTIFICATION);
        if (hasModifyPermission) {
            AnchorHtmlData editHtmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlDataList.add(editHtmlData);
        }
        return htmlDataList;
    }
    
    public KcNotificationAuthorizationService getKcNotificationAuthorizationService() {
        return kcNotificationAuthorizationService;
    }

    public void setKcNotificationAuthorizationService(KcNotificationAuthorizationService kcNotificationAuthorizationService) {
        this.kcNotificationAuthorizationService = kcNotificationAuthorizationService;
    }

    
    
}
