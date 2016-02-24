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
