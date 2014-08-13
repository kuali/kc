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
package org.kuali.kra.institutionalproposal.proposallog.service;

import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;

import java.util.List;

/**
 * External services provided by the Proposal Log module.
 */
public interface ProposalLogService {
    
    /**
     * Tie the temporary proposal to the permanent one and update the status.
     * @param permamentProposalLog
     * @param temporaryProposalNumber
     */
    void mergeProposalLog(ProposalLog permanentProposalLog, String temporaryProposalNumber);    
    
    /**
     * Update the state of the log entry for the given proposal number to reflect that it has been merged
     * with another proposal log.
     * 
     * @param proposalNumber String
     */
    void mergeProposalLog(String proposalNumber);
    
    /**
     * Update the state of the log entry for the given proposal number to reflect that it has been promoted
     * to an Institutional Proposal.
     * 
     * @param proposalNumber String
     */
    void promoteProposalLog(String proposalNumber);
    
    /**
     * links merged institutional proposal and proposal log
     */    
    void updateMergedInstProposal(Long proposalId, String proposalNumber);
    
    /**
     * Gets all temporary proposal logs with the matching pi that haven't been merged yet.
     * @param proposalLogTypeCode
     * @param piId
     * @return
     */
    List<ProposalLog> getMatchingTemporaryProposalLogs(String proposalLogTypeCode, String piId, String rolodexId);    
}
