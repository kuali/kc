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
package org.kuali.kra.institutionalproposal.proposallog.service;

/**
 * External services provided by the Proposal Log module.
 */
public interface ProposalLogService {
    
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
     * links merged temporary proposal log and permanent proposal log
     */
    void updateMergedTempLog(String tempProposalNumber, String permProposalNumber);
    
    /**
     * links merged institutional proposal and proposal log
     */    
    void updateMergedInstProposal(Long proposalId, String proposalNumber);
}
