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