/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.proposallog.service;

import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLogType;

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
    
    /**
     * Based on the provided description will return the associated ProposalLogType. If no exact matches are found, then null is returned.
     * @param description
     * @return
     */
    ProposalLogType getProposalLogTypeFromDescription(String description);
}
