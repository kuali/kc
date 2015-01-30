/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.List;

/**
 * Runs the rule processing for saving a Notification.
 */
public class SendNotificationRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<SendNotificationEvent> {
    
    private static final String FULL_NAME_FIELD = "fullName";
    
    @Override
    public boolean processRules(SendNotificationEvent sendNotificationEvent) {
        boolean rulePassed = true;
        
        List<NotificationTypeRecipient> notificationTypeRecipients = sendNotificationEvent.getNotificationTypeRecipients();

        rulePassed &= validateNonEmptyRecipients(notificationTypeRecipients);
        
        return rulePassed;
    }
    
    private boolean validateNonEmptyRecipients(List<NotificationTypeRecipient> notificationTypeRecipients) {
        boolean isValid = true;
        
        if (notificationTypeRecipients.isEmpty()) {
            isValid = false;
            reportError(FULL_NAME_FIELD, KeyConstants.ERROR_NOTIFICATION_RECIPIENTS_REQUIRED);
        }
        
        return isValid;
    }

}
