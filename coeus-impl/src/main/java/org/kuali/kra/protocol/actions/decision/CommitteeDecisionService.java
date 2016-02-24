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
package org.kuali.kra.protocol.actions.decision;

import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteAbstaineeBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteRecusedBase;
import org.kuali.kra.protocol.ProtocolBase;

import java.util.List;

/**
 * The Committee Decision Service processes committee decisions.
 */
public interface CommitteeDecisionService<CD extends CommitteeDecision<? extends CommitteePersonBase> > {

    /**
     * Record the committee's decision.
     * @param protocol
     * @param committeeDecision
     */
    void processCommitteeDecision(ProtocolBase protocol, CD committeeDecision) throws Exception;
    
    /**
     * Finds all of the abstainer votes for the given protocolNumber and submissionNumber.
     * @param protocolNumber The human-readable protocol number
     * @param submissionNumber The submission number
     * @return the list of abstainee votes for the given protocolNumber
     */
    List<ProtocolVoteAbstaineeBase> getAbstainers(String protocolNumber, int submissionNumber);
    
    /**
     * Finds all of the recused votes for the given protocolNumber and submissionNumber.
     * @param protocolNumber The human-readable protocol number
     * @param submissionNumber The submission number
     * @return the list of recused votes for the given protocolNumber
     */
    List<ProtocolVoteRecusedBase> getRecusers(String protocolNumber, int submissionNumber);
}
