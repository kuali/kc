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

import org.kuali.kra.protocol.noteattachment.AddProtocolAttachmentProtocolRuleImplBase;

/**
 * Implementation of {@link AddProtocolAttachmentProtocolRule AddProtocolAttachmentProtocolRule}.
 * @see AddProtocolAttachmentProtocolRule for details
 */
public class AddProtocolAttachmentProtocolRuleImpl extends AddProtocolAttachmentProtocolRuleImplBase {
    
    
    public AddProtocolAttachmentProtocolRuleImpl() {
        super();
        
        baseHelper = new ProtocolAttachmentBaseRuleHelper(NoteAndAttachmentPrefix.NEW_ATTACHMENT_PROTOCOL.getPrefixName());
        protocolHelper = new ProtocolAttachmentProtocolRuleHelper(NoteAndAttachmentPrefix.NEW_ATTACHMENT_PROTOCOL.getPrefixName());
    }
}
