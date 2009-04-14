/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.noteattachment;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ErrorReporter;

/**
 * Implementation of {@link AddProtocolAttachmentPersonnelRule AddProtocolAttachmentPersonnelRule}.
 * @see AddProtocolAttachmentPersonnelRule for details
 */
class AddProtocolAttachmentPersonnelRuleImpl implements AddProtocolAttachmentPersonnelRule {

    private static final String PROPERTY_PREFIX = "notesAndAttachmentsHelper.newAttachmentPersonnel";
    private static final String PERSON_ID = PROPERTY_PREFIX + ".personId";
    
    private final AddProtocolAttachmentBaseRuleHelper helper = new AddProtocolAttachmentBaseRuleHelper(PROPERTY_PREFIX);
    private final ErrorReporter errorReporter = new ErrorReporter();

    /** {@inheritDoc} */
    public boolean processAddProtocolAttachmentPersonnelRules(AddProtocolAttachmentPersonnelEvent event) {      
        
        final ProtocolAttachmentPersonnel newAttachmentPersonnel = event.getNewAttachmentPersonnel();
        
        boolean valid = this.helper.validType(newAttachmentPersonnel);
        valid &= validPerson(newAttachmentPersonnel);
        valid &= this.helper.validFile(newAttachmentPersonnel);
        valid &= this.helper.validDescription(newAttachmentPersonnel);
        
        return valid;
    }
    
    /**
     * Checks for a valid status.
     * @param newAttachmentPersonnel the attachment.
     * @return true is valid.
     */
    private boolean validPerson(final ProtocolAttachmentPersonnel newAttachmentPersonnel) {
        
        if (StringUtils.isBlank(newAttachmentPersonnel.getPersonId())) {
            this.errorReporter.reportError(PERSON_ID, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_PERSON);
            return false;
        }
        return true;
    }
}
