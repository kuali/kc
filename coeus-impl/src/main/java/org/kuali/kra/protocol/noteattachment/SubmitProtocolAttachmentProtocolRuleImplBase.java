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
package org.kuali.kra.protocol.noteattachment;

import org.kuali.kra.protocol.ProtocolDocumentBase;

import java.util.List;

/**
 * Class handles rules for submitting a {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase}.
 * This class does not have a corresponding event or Rule interface because it is just used-by the mega
 * {@link org.kuali.kra.protocol.ProtocolDocumentRuleBase ProtocolDocumentRuleBase} for submit events.
 */
public abstract class SubmitProtocolAttachmentProtocolRuleImplBase {

    protected ProtocolAttachmentProtocolRuleHelperBase protocolHelper;

    /**
     * Executes the rules related to {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase} when saving a ProtocolDocumentBase.
     * @param document the document
     * @return true if valid  
     */
    public boolean processSubmitProtocolAttachmentProtocolRules(final ProtocolDocumentBase document) {      
        
        if (document == null) {
            throw new IllegalArgumentException("the document was null");
        }
        boolean valid = true;
        
        final List<ProtocolAttachmentProtocolBase> attachments = document.getProtocol().getAttachmentProtocols();
        
        for (int i = 0; i < attachments.size(); i++) {
            final ProtocolAttachmentProtocolBase attachment = attachments.get(i);
            this.setPropertyPrefixes(NoteAndAttachmentPrefix.ATTACHMENT_PROTOCOL.getIndexedPrefix(i));

            valid &= this.protocolHelper.validStatusForSubmission(attachment);
        }
        return valid;
    }
    
    /**
     * Resets the PropertyPrefixes on the used helpers.
     * @param prefix the prefix.
     */
    private void setPropertyPrefixes(String prefix) {
        this.protocolHelper.resetPropertyPrefix(prefix);
    }
}
