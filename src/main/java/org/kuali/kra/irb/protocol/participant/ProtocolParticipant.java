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

package org.kuali.kra.irb.protocol.participant;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;

/**
 * 
 * This class implements the protocol participant object.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
@Entity
@Table(name = "PROTOCOL_VULNERABLE_SUB")
public class ProtocolParticipant extends ProtocolAssociate {

    @Id
    @Column(name = "PROTOCOL_VULNERABLE_SUB_ID")
    private Long protocolParticipantId;

    @Column(name = "PROTOCOL_ID")
    private Long protocolId;

    @Column(name = "VULNERABLE_SUBJECT_TYPE_CODE")
    private String participantTypeCode;

    @Column(name = "SUBJECT_COUNT")
    private Integer participantCount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "VULNERABLE_SUBJECT_TYPE_CODE", insertable = true, updatable = true)
    private ParticipantType participantType;

    public ProtocolParticipant() {
    }

    public Long getProtocolParticipantId() {
        return protocolParticipantId;
    }

    public void setProtocolParticipantId(Long protocolParticipantId) {
        this.protocolParticipantId = protocolParticipantId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getParticipantTypeCode() {
        return participantTypeCode;
    }

    public void setParticipantTypeCode(String participantTypeCode) {
        this.participantTypeCode = participantTypeCode;
    }

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("protocolParticipantId", getProtocolParticipantId());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("participantTypeCode", getParticipantTypeCode());
        hashMap.put("participantCount", getParticipantCount());
        return hashMap;
    }

    public void init(Protocol protocol) {
        setProtocolParticipantId(null);
        setProtocolId(protocol.getProtocolId());
        setProtocolNumber(protocol.getProtocolNumber());
        setSequenceNumber(protocol.getSequenceNumber());
    }

}