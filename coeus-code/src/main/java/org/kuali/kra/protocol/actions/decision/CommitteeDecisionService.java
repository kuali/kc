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
