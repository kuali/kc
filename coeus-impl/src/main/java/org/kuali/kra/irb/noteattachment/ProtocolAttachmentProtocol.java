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
package org.kuali.kra.irb.noteattachment;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;

/**
 * This class represents the Protocol Attachment Protocol.
 */
public class ProtocolAttachmentProtocol extends ProtocolAttachmentProtocolBase {

    private static final long serialVersionUID = -7115904344245464654L;

    private static final String GROUP_CODE = "1";

    public static final String INCOMPLETE_STATUS_CODE = "1";
    public static final String COMPLETE_STATUS_CODE = "2";    
    
    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentProtocol() {
        super();
    }

    /**
     * Convenience ctor to add the protocol as an owner.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param protocol the protocol.
     */
    public ProtocolAttachmentProtocol(final Protocol protocol) {
        super(protocol);
    }

    @Override
    public String getGroupCode() {
        return GROUP_CODE;
    }

    @Override
    public String getAttachmentDescription() {
        return "Protocol Attachment";
    }

    public boolean isDraft() {
        return ProtocolAttachmentStatus.DRAFT.equals(documentStatusCode);
    }
    
    public void setDraft() {
        documentStatusCode = ProtocolAttachmentStatus.DRAFT;
    }
    
    public boolean isFinal() {
        return ProtocolAttachmentStatus.FINALIZED.equals(documentStatusCode);
    }
    
    public void setFinal() {
        documentStatusCode = ProtocolAttachmentStatus.FINALIZED;
    }
    
    public boolean isDeleted() {
        return ProtocolAttachmentStatus.DELETED.equals(documentStatusCode);
    }
    
    public void setDeleted() {
        documentStatusCode = ProtocolAttachmentStatus.DELETED;
    }
}
