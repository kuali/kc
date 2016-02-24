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
