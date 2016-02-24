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
package org.kuali.kra.irb.summary;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProtocolSummary extends org.kuali.kra.protocol.summary.ProtocolSummary {

    private static final long serialVersionUID = -259451094188180892L;
    
    private List<ParticipantSummary> participants = new ArrayList<ParticipantSummary>();
        
    public ProtocolSummary() {
        
    }

    public List<ParticipantSummary> getParticipants() {
        return participants;
    }
    
    public void add(ParticipantSummary participantSummary) {
        participants.add(participantSummary);
    }

    public void compare(org.kuali.kra.protocol.summary.ProtocolSummary other) {        
        super.compare(other);
        compareParticipants((ProtocolSummary) other);
    }
    
    private void compareParticipants(ProtocolSummary other) {
        for (ParticipantSummary participant : participants) {
            participant.compare(other);
        }
    }
    
    public ParticipantSummary findParticipant(String description) {
        for (ParticipantSummary participant : participants) {
            if (StringUtils.equals(participant.getDescription(), description)) {
                return participant;
            }
        }
        return null;
    }
}
