/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.irb.protocol.participant;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.kuali.kra.irb.ProtocolAssociate;

/**
 * 
 * This class implements the protocol participant object.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantBean implements Serializable {
    private static final long serialVersionUID = 2276161861854166963L;
    private String protocolParticipantId;
    private String participantTypeCode;
    private String participantCount;
    private String participantTypeDescription;
    
    /**
     * 
     * Constructs a ProtocolParticipantBean.java.
     */
    public ProtocolParticipantBean() {
    }
    
    /**
     * 
     * Constructs a ProtocolParticipantBean.java.
     * @param protocolParticipantId
     * @param participantTypeCode
     * @param participantCount
     */
    public ProtocolParticipantBean(String protocolParticipantId, String participantTypeCode, String participantCount, String participantTypeDescription) {
        this.protocolParticipantId = protocolParticipantId;
        this.participantTypeCode = participantTypeCode;
        this.participantCount = participantCount;
        this.participantTypeDescription = participantTypeDescription;
    }

    public String getParticipantTypeDescription() {
        return participantTypeDescription;
    }

    public void setParticipantTypeDescription(String participantTypeDescription) {
        this.participantTypeDescription = participantTypeDescription;
    }

    public String getProtocolParticipantId() {
        return protocolParticipantId;
    }

    public void setProtocolParticipantId(String protocolParticipantId) {
        this.protocolParticipantId = protocolParticipantId;
    }

    public String getParticipantTypeCode() {
        return participantTypeCode;
    }

    public void setParticipantTypeCode(String participantTypeCode) {
        this.participantTypeCode = participantTypeCode;
    }

    public String getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(String participantCount) {
        this.participantCount = participantCount;
    }
    
    @Override
    public boolean equals(Object o) {
        ProtocolParticipantBean other = (ProtocolParticipantBean) o;
        return this.getParticipantTypeCode().equals(other.getParticipantTypeCode());
    }
}