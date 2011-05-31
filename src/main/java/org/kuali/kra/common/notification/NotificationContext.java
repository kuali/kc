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
package org.kuali.kra.common.notification;

import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;

/**
 * A context from which a Notification is sent. Contains instance-specific information 
 * needed to process and send the Notification.
 */
public interface NotificationContext {
    
    /**
     * Replace context-specific variables in the message or subject. 
     * E.G., {DOCUMENT_NUMBER} -> 1234567.
     * 
     * @param text The text to process.
     * @return The new text with context variables replaced.
     */
    String replaceContextVariables(String text);
    
    /**
     * Populate context-specific role qualifier values on role-based recipients. 
     * For example many roles are qualified by Document Number.
     * 
     * @param notificationRecipient
     * @throws UnknownRoleException if the role is unknown for this context.
     */
    void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException;

}
