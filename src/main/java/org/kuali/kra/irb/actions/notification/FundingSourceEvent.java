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

public class FundingSourceEvent extends NotificationEventBase implements NotificationContext {
    public static final String FUNDING_SOURCE = "904";
    private String fundingType;
    private String subject;
    private String action;
    
    public FundingSourceEvent() {
    }

    public FundingSourceEvent(Protocol protocol) {
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
        return "Add funding source to Protocol " + getProtocol().getProtocolNumber();
    }

    public String getTemplatePath() {
        return "FundingSourceNotification.xsl";
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return FUNDING_SOURCE;
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
        
        if (StringUtils.equals(roleNamespace, RoleConstants.DEPARTMENT_ROLE_TYPE) && StringUtils.equals(roleName, RoleConstants.FUNDING_SOURCE_MONITOR))  {
            notificationRecipient.setRoleQualifier(KcKimAttributes.UNIT_NUMBER);
            notificationRecipient.setQualifierValue(getProtocol().getLeadUnitNumber());
                
        } else {
            throw new UnknownRoleException(notificationRecipient.getRoleName(), "FundingSource");
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
                text =  StringUtils.replace(text, "{MESSAGE_BODY}", message);
                text =  StringUtils.replace(text, "{ACTION}", getAction());
                return StringUtils.replace(text, "{FUNDING_TYPE}", getFundingType());
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

    public String getFundingType() {
        return fundingType;
    }

    public void setFundingType(String fundingType) {
        this.fundingType = fundingType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
