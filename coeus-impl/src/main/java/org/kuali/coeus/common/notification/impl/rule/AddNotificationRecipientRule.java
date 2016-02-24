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
package org.kuali.coeus.common.notification.impl.rule;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.AddNotificationRecipientEvent;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

/**
 * Runs the rule processing for adding a Notification Type Recipient.
 */
public class AddNotificationRecipientRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddNotificationRecipientEvent> {

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
