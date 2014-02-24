/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.coeus.common.notification.impl;

import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.kra.util.EmailAttachment;

import java.util.List;

/**
 * A context from which a Notification is sent. Contains instance-specific information 
 * needed to process and send the Notification.
 */
public interface NotificationContext {
    
    /**
     * Returns the associated module as defined in {@link org.kuali.coeus.common.framework.module.CoeusModule}.
     * 
     * @return the Coeus module code
     * @see org.kuali.coeus.common.framework.module.CoeusModule
     */
    String getModuleCode();
    
    /**
     * Returns the action type code for this context.
     * 
     * @return the action type code
     */
    String getActionTypeCode();
    
    /**
     * Returns the document number for the associated document.
     * 
     * @return the document number
     */
    String getDocumentNumber();
    
    /**
     * Returns a the name used for the given context.
     * 
     * @return the context name
     */
    String getContextName();
    
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
    
    /**
     * This method provides support for email attachments for email notifications.
     * 
     * @return a list of email attachments or null if none are available.
     */
    List<EmailAttachment> getEmailAttachments();

}