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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.w3c.dom.Element;

public class BatchCorrespondenceEvent extends NotificationEventBase implements NotificationContext {

    private Long detailId;
    private String subject;
    public BatchCorrespondenceEvent(Protocol protocol) {
        super(protocol);
    }


    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getRecipients(org.w3c.dom.Element)
     */
    public void getRecipients(Element recipients) {
        super.getRecipients(recipients);
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getTitle()
     */
    public String getTitle() {
        return "Protocol " + getProtocol().getProtocolNumber() + " Batch Correspondence";
    }

    public String getTemplatePath() {
        return "BatchCorrespondenceNotification.xsl";
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        // TODO : just give it a code for now.  may not need it when move to kc notification
        return "111";
    }


    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        String roleNamespace = StringUtils.substringBefore(notificationRecipient.getRoleName(), Constants.COLON);
        String roleName = StringUtils.substringAfter(notificationRecipient.getRoleName(), Constants.COLON);
        
        if (StringUtils.equals(roleNamespace, RoleConstants.PROTOCOL_ROLE_TYPE) && StringUtils.equals(roleName, RoleConstants.PROTOCOL_AGGREGATOR)) {
            notificationRecipient.setRoleQualifier("protocol");
            notificationRecipient.setQualifierValue(getProtocol().getProtocolNumber());
        } else if (StringUtils.equals(roleNamespace, RoleConstants.DEPARTMENT_ROLE_TYPE) && StringUtils.equals(roleName, RoleConstants.IRB_ADMINISTRATOR))  {
            notificationRecipient.setRoleQualifier(KcKimAttributes.UNIT_NUMBER);
            notificationRecipient.setQualifierValue(getProtocol().getLeadUnitNumber());
//            notificationRecipient.setRoleQualifier(KcKimAttributes.SUBUNITS);
//            notificationRecipient.setQualifierValue(getProtocol().getLeadUnitNumber());
//            notificationRecipient.setRoleQualifier(null);
        } else {
            throw new UnknownRoleException(notificationRecipient.getRoleName(), "BatchCorrespondence");
        }
        
    }   
    
    public String replaceContextVariables(String text) {
        ProtocolActionsNotificationService protocolActionsNotificationService = KraServiceLocator
                .getService(ProtocolActionsNotificationService.class);
        try {
            if (text.contains("{MESSAGE_SUBJECT}")) {
                return StringUtils.replace(text, "{MESSAGE_SUBJECT}", getSubject());
            }
            else {
                String message = protocolActionsNotificationService.getTransFormData(getProtocol(), getTemplate());
                message = message.replaceAll("\\$amp;", "&amp;");
                // message = message.replace("$amp;", "&amp;");
                message = message.replace("detailId", "detailId" + getDetailId());
                return StringUtils.replace(text, "{MESSAGE_BODY}", message);
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public void sedNotification() {
        KcNotificationService kcNotificationService = KraServiceLocator.getService(KcNotificationService.class);
        List<KcNotification> notifications = kcNotificationService.createNotifications(getProtocol().getProtocolDocument().getDocumentNumber(), Integer.toString(CoeusModule.IRB_MODULE_CODE_INT), "111", this);
        kcNotificationService.sendNotifications(notifications, this);

    }


    public Long getDetailId() {
        return detailId;
    }


    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }
}
