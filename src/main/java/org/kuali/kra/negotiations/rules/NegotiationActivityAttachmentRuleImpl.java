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
package org.kuali.kra.negotiations.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * Validation class for activity attachments.
 */
public class NegotiationActivityAttachmentRuleImpl implements NegotiationActivityAttachmentAddRule {
    
    private final ErrorReporter errorReporter = new ErrorReporter();

    /**
     * @see org.kuali.kra.negotiations.rules.NegotiationActivityAttachmentAddRule#processAddAttachmentRule(org.kuali.kra.negotiations.rules.NegotiationActivityAttachmentAddRuleEvent)
     */
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
