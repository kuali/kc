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
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.w3c.dom.Element;

public class RejectReviewEvent extends NotificationEventBase implements NotificationContext {
    public static final String REVIEW_REJECTED = "903";
    private ProtocolOnlineReview onlineReview;
    private String reason;
    
    public RejectReviewEvent() {
    }

    public RejectReviewEvent(Protocol protocol) {
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
        return "Protocol " + getProtocol().getProtocolNumber() + " Review returned to reviewer";
    }

    public String getTemplatePath() {
        return "RejectReviewNotification.xsl";
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return REVIEW_REJECTED;
    }

    @Override
    public boolean isReviewerNotification() {
        return true;    
    }
    
    @Override
    public boolean isInvestigatorIncluded() {
        return false;    
    }
    
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        String roleNamespace = StringUtils.substringBefore(notificationRecipient.getRoleName(), Constants.COLON);
        String roleName = StringUtils.substringAfter(notificationRecipient.getRoleName(), Constants.COLON);
        
        if (StringUtils.equals(roleNamespace, RoleConstants.PROTOCOL_ROLE_TYPE) && StringUtils.equals(roleName, RoleConstants.IRB_PROTOCOL_ONLINE_REVIEWER)) {
            if ("submissionId".equals(notificationRecipient.getRoleQualifier())) {
           // notificationRecipient.setRoleQualifier("protocol");
            notificationRecipient.setQualifierValue(getProtocol().getProtocolSubmission().getSubmissionId().toString());
            } else if ("protocolOnlineReviewId".equals(notificationRecipient.getRoleQualifier())) {
                // notificationRecipient.setRoleQualifier("protocol");
                notificationRecipient.setQualifierValue(getOnlineReview().getProtocolOnlineReviewId().toString());
                
            } else if ("protocolLeadUnitNumber".equals(notificationRecipient.getRoleQualifier())) {
                // notificationRecipient.setRoleQualifier("protocol");
                notificationRecipient.setQualifierValue(getProtocol().getLeadUnitNumber());
                
            }
        } else {
            throw new UnknownRoleException(notificationRecipient.getRoleName(), "RejectReview");
        }
        
    }   
    
    public String replaceContextVariables(String text) {
        ProtocolActionsNotificationService protocolActionsNotificationService = KraServiceLocator
                .getService(ProtocolActionsNotificationService.class);
        try {
            if (text.contains("{MESSAGE_SUBJECT}")) {
                return StringUtils.replace(text, "{MESSAGE_SUBJECT}", getTitle());
            }
            else {
                String message = protocolActionsNotificationService.getTransFormData(getProtocol(), getTemplate());
                message = message.replaceAll("\\$amp;", "&amp;");
                text = StringUtils.replace(text, "{MESSAGE_BODY}", message);
                return StringUtils.replace(text, "{REASON}", getReason());
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public void sendNotification() {
        KcNotificationService kcNotificationService = KraServiceLocator.getService(KcNotificationService.class);
        List<KcNotification> notifications = kcNotificationService.createNotifications(getProtocol().getProtocolDocument().getDocumentNumber(), Integer.toString(CoeusModule.IRB_MODULE_CODE_INT), getActionTypeCode(), this);
        kcNotificationService.sendNotifications(notifications, this);

    }

    public ProtocolOnlineReview getOnlineReview() {
        return onlineReview;
    }

    public void setOnlineReview(ProtocolOnlineReview onlineReview) {
        this.onlineReview = onlineReview;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
