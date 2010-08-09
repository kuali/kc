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
package org.kuali.kra.irb.protocol.participant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.irb.Protocol;


/**
 * This class...
 */
public class ParticipantsHelper implements Serializable {
    
    private static final long serialVersionUID = 2276161861254166963L;
    
    private ProtocolParticipantBean newParticipant;
    private List<ProtocolParticipantBean> existingParticipants;
    private List<ProtocolParticipantBean> existingParticipantsToDelete;
    
    /**
     * 
     * Constructs a ParticipantsHelper.java.
     */
    public ParticipantsHelper() {
        newParticipant = new ProtocolParticipantBean();
        existingParticipants = new ArrayList<ProtocolParticipantBean>();
        existingParticipantsToDelete = new ArrayList<ProtocolParticipantBean>();
    }
    
    /**
     * 
     * Constructs a ParticipantsHelper.java.
     * @param protocol
     */
    public ParticipantsHelper(Protocol protocol) {
        this();
        for (ProtocolParticipant participant : protocol.getProtocolParticipants()) {
            String participantCountString = participant.getParticipantCount() == null ? "" : participant.getParticipantCount().toString();
            ProtocolParticipantBean bean = new ProtocolParticipantBean(participant.getProtocolParticipantId().toString(), 
                    participant.getParticipantTypeCode(), participantCountString, participant.getParticipantType().getDescription());
            existingParticipants.add(bean);
        }
    }
    
    /**
     * 
     * This method...
     */
    public void prepareView() {
        //setNewProtocolParticipant(new ProtocolParticipant());
    }
    
    public ProtocolParticipantBean getNewParticipant() {
        return newParticipant;
    }

    public void setNewParticipant(ProtocolParticipantBean newParticipant) {
        this.newParticipant = newParticipant;
    }

    public List<ProtocolParticipantBean> getExistingParticipants() {
        return existingParticipants;
    }

    public void setExistingParticipants(List<ProtocolParticipantBean> existingParticipants) {
        this.existingParticipants = existingParticipants;
    }
}

