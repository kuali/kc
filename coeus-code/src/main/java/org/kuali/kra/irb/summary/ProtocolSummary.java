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
