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
package org.kuali.kra.protocol.noteattachment;

/**
 * Implementation of {@link AddProtocolAttachmentProtocolRule AddProtocolAttachmentProtocolRule}.
 * @see AddProtocolAttachmentProtocolRule for details
 */
public abstract class AddProtocolAttachmentProtocolRuleImpl implements AddProtocolAttachmentProtocolRule {

    protected ProtocolAttachmentBaseRuleHelper baseHelper;
    protected ProtocolAttachmentProtocolRuleHelper protocolHelper;
// TODO *********commented the code below during IACUC refactoring*********     
//    private final ProtocolAttachmentBaseRuleHelper baseHelper
//        = new ProtocolAttachmentBaseRuleHelper(NoteAndAttachmentPrefix.NEW_ATTACHMENT_PROTOCOL.getPrefixName());
//    
//    private final ProtocolAttachmentProtocolRuleHelper protocolHelper
//    = new ProtocolAttachmentProtocolRuleHelper(NoteAndAttachmentPrefix.NEW_ATTACHMENT_PROTOCOL.getPrefixName());
    
    /** {@inheritDoc} */
    public boolean processAddProtocolAttachmentProtocolRules(AddProtocolAttachmentProtocolEvent event) {      
        
        final ProtocolAttachmentProtocol newAttachmentProtocol = event.getNewAttachmentProtocol();
        
        boolean valid = this.baseHelper.validPrimitiveFields(newAttachmentProtocol);
        valid &= this.baseHelper.validTypeForGroup(newAttachmentProtocol);
        valid &= this.baseHelper.validDescriptionWhenRequired(newAttachmentProtocol);
        valid &= this.protocolHelper.validStatus(newAttachmentProtocol);
        valid &= this.baseHelper.validFile(newAttachmentProtocol);
        
        return valid;
    }
}
