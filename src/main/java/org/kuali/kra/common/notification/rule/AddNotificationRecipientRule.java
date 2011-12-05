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
package org.kuali.kra.common.notification.rule;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.event.AddNotificationRecipientEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Runs the rule processing for adding a Notification Type Recipient.
 */
public class AddNotificationRecipientRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddNotificationRecipientEvent> {

    private static final String FULL_NAME_FIELD = "fullName";
    
    @Override
    public boolean processRules(AddNotificationRecipientEvent addNotificationRecipientEvent) {
        boolean rulePassed = true;
        
        NotificationTypeRecipient notificationTypeRecipient = addNotificationRecipientEvent.getNotificationTypeRecipient();
        List<NotificationTypeRecipient> notificationTypeRecipients = addNotificationRecipientEvent.getNotificationTypeRecipients();
        
        getDictionaryValidationService().validateBusinessObject(notificationTypeRecipient);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();
        rulePassed &= validateRecipient(notificationTypeRecipient);
        rulePassed &= validateUniqueRecipients(notificationTypeRecipient, notificationTypeRecipients);
        
        return rulePassed;
    }
    
    private boolean validateRecipient(NotificationTypeRecipient notificationTypeRecipient) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(notificationTypeRecipient.getRoleName()) && StringUtils.isBlank(notificationTypeRecipient.getPersonId()) 
                                                                         && StringUtils.isBlank(notificationTypeRecipient.getRolodexId())) {
            isValid = false;
            reportError(FULL_NAME_FIELD, KeyConstants.ERROR_NOTIFICATION_EMPTY_NOTIFICATION_RECIPIENT);
        }

        return isValid;
    }
    
    private boolean validateUniqueRecipients(NotificationTypeRecipient newNotificationTypeRecipient, 
                                             List<NotificationTypeRecipient> existingNotificationTypeRecipients) {
        boolean isValid = true;

        for (NotificationTypeRecipient existingNotificationTypeRecipient : existingNotificationTypeRecipients) {
            if (isIdentifierEqual(newNotificationTypeRecipient.getRoleName(), existingNotificationTypeRecipient.getRoleName()) 
                    || isIdentifierEqual(newNotificationTypeRecipient.getPersonId(), existingNotificationTypeRecipient.getPersonId()) 
                    || isIdentifierEqual(newNotificationTypeRecipient.getRolodexId(), existingNotificationTypeRecipient.getRolodexId())) {
                isValid = false;
                reportError(FULL_NAME_FIELD, KeyConstants.ERROR_NOTIFICATION_DUPLICATE_NOTIFICATION_RECIPIENT);
            }
        }
        
        return isValid;
    }
    
    private static boolean isIdentifierEqual(String newIdentifier, String existingIdentifier) {
        return (StringUtils.isNotBlank(newIdentifier) || StringUtils.isNotBlank(existingIdentifier)) && StringUtils.equals(newIdentifier, existingIdentifier);
    }
    
}