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
package org.kuali.kra.irb.actions.notification;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationRenderingService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.w3c.dom.Element;

public class BatchCorrespondenceEvent extends IRBNotificationContext {

    private Long detailId;
    private String protocolCorrespondenceType;
    private String userFullname;
    
    public BatchCorrespondenceEvent(Protocol protocol) {
        super(protocol);
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        // TODO : just give it a code for now.
        return "111";
    }


    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        super.populateRoleQualifiers(notificationRecipient, "BatchCorrespondence");
    }   
    
    public String replaceContextVariables(String text) {
        KcNotificationRenderingService renderer = getNotificationRenderingService();
        Map<String, String> params = renderer.getReplacementParameters();
        params.put("{DETAIL_ID}", getDetailId().toString());
        params.put("{PROTOCOL_CORRESPONDENCE_TYPE}", getProtocolCorrespondenceType());
        params.put("{USER_FULLNAME}", getUserFullname());
        return renderer.render(text, params);
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#sendNotification()
     */
    public void sendNotification() {
        sendNotification(this);
    }


    public Long getDetailId() {
        return detailId;
    }


    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(String protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

}
