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
package org.kuali.kra.negotiations.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * Validation class for activity attachments.
 */
public class NegotiationActivityAttachmentRuleImpl implements NegotiationActivityAttachmentAddRule {
    
    private final ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);

    @Override
    public boolean processAddAttachmentRule(NegotiationActivityAttachmentAddRuleEvent event) {
        boolean result = true;
        
        NegotiationDocument document = (NegotiationDocument) event.getDocument();
        Negotiation negotiation = document.getNegotiation();
        NegotiationActivity activity = event.getActivity();
        NegotiationActivityAttachment newAttachment = event.getNewAttachment();
        
        GlobalVariables.getMessageMap().addToErrorPath(event.getErrorPathPrefix());
        
        result &= this.validateAttachmentRule(negotiation, activity, newAttachment);
        if (!errorReporter.propertyHasErrorReported(GlobalVariables.getMessageMap().getKeyPath("newFile", true))
                && StringUtils.isBlank(newAttachment.getNewFile().getFileName())) {
            result = false;
            errorReporter.reportError("newFile", KeyConstants.ERROR_REQUIRED, "File (File)");
        }
        
        GlobalVariables.getMessageMap().removeFromErrorPath(event.getErrorPathPrefix());
        
        return result;
    }
    
    /**
     * 
     * Call this to validate individual attachments.
     * @param negotiation
     * @param activity
     * @param attachment
     * @return
     */
    public boolean validateAttachmentRule(Negotiation negotiation, NegotiationActivity activity, NegotiationActivityAttachment attachment) {
        boolean result = true;
        if (!errorReporter.propertyHasErrorReported(GlobalVariables.getMessageMap().getKeyPath("description", true))
                && StringUtils.isBlank(attachment.getDescription())) {
            result = false;
            errorReporter.reportError("description", KeyConstants.ERROR_REQUIRED, "Description (Description)");
        }
        return result;
    }

}
