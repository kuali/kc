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
package org.kuali.kra.irb.protocol.participant;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.irb.ProtocolDocument;

import java.util.List;

/**
 * This class represents the event when a <code>{@link ProtocolParticipant}</code> is added to a 
 * <code>{@link Protocol}</code>.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class AddProtocolParticipantEvent extends KcDocumentEventBaseExtension {
    
    private static final String NEW_PROTOCOL_PARTICIPANT_FIELD = "protocolHelper.newProtocolParticipant";
    
    private ProtocolParticipant newProtocolParticipant;
    private List<ProtocolParticipant> protocolParticipants;
    
    /**
     * Constructs a <code>{@link AddProtocolParticipantEvent}</code>.
     * 
     * @param errorPathPrefix
     * @param document
     * @param newProtocolParticipant
     * @param existingProtocolParticipants
     */
    public AddProtocolParticipantEvent(ProtocolDocument document, ProtocolParticipant newProtocolParticipant, 
            List<ProtocolParticipant> protocolParticipants) {
        super("Adding ProtocolParticipant to document " + getDocumentId(document), NEW_PROTOCOL_PARTICIPANT_FIELD, document);
    
        this.newProtocolParticipant = newProtocolParticipant;
        this.protocolParticipants = protocolParticipants;
    }
    
    public ProtocolParticipant getNewProtocolParticipant() {
        return newProtocolParticipant;
    }
    
    public List<ProtocolParticipant> getProtocolParticipants() {
        return protocolParticipants;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new AddProtocolParticipantRule();
    }
    
}