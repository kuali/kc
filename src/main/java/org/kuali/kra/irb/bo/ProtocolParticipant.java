/*
 * Copyright 2006-2008 The Kuali Foundation
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

package org.kuali.kra.irb.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.ScienceKeyword;

import java.util.LinkedHashMap;
import java.sql.Date;

import javax.persistence.Column;

public class ProtocolParticipant extends KraPersistableBusinessObjectBase { 

    private Integer protocolId;
    private String protocolNumber; 
    private Integer sequenceNumber;
    private Integer participantTypeCode; 
    private Integer participantCount; 
    private ParticipantType participantType; 
    
    public ProtocolParticipant() { 

    } 

    /**
     * Constructs a ProtocolParticipant.
     * @param proposalNumber
     * @param scienceKeyword
     */
    public ProtocolParticipant(Integer protocolId, ParticipantType participantType, Integer participantCount) {
        this.protocolId = protocolId;
        this.participantTypeCode = participantType.getParticipantTypeCode();
        this.participantCount = participantCount;
        this.participantType = participantType;
    }
    
    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public Integer getParticipantTypeCode() {
        return participantTypeCode;
    }

    public void setParticipantTypeCode(Integer participantTypeCode) {
        this.participantTypeCode = participantTypeCode;
    }

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("participantTypeCode", getParticipantTypeCode());
        hashMap.put("participantCount", getParticipantCount());
        return hashMap;
    }
    
}