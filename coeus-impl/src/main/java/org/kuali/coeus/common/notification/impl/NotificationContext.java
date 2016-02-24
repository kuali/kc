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
package org.kuali.coeus.common.notification.impl;

import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;

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
