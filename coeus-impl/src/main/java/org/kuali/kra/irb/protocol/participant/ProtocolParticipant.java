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

import org.kuali.kra.protocol.ProtocolAssociateBase;

/**
 * 
 * This class implements the protocol participant object.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipant extends ProtocolAssociateBase {


    private static final long serialVersionUID = 1716821047021762233L;

    private Long protocolParticipantId;

    private String participantTypeCode;

    private Integer participantCount;

    private ParticipantType participantType;

    public ProtocolParticipant() {
    }

    public Long getProtocolParticipantId() {
        return protocolParticipantId;
    }

    public void setProtocolParticipantId(Long protocolParticipantId) {
        this.protocolParticipantId = protocolParticipantId;
    }

    public String getParticipantTypeCode() {
        return participantTypeCode;
    }

    public void setParticipantTypeCode(String participantTypeCode) {
        this.participantTypeCode = participantTypeCode;
    }

    public Integer getParticipantCount() {
        return (participantCount == null) ? 0 : participantCount;
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
    public void resetPersistenceState() {
        this.setProtocolParticipantId(null);
    }
}
