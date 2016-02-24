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
package org.kuali.coeus.propdev.impl.state;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;

/**
 * The Proposal State Service is responsible for computing the state of a proposal.
 * The well-defined states are:
 * <ul>
 * <li>In Progress</li>
 * <li>Approval Pending</li>
 * <li>Approval Granted</li>
 * <li>Approval Not Initiated - Submitted</li>
 * <li>Approval Pending - Submitted</li>
 * <li>Approved and Submitted</li>
 * <li>Disapproved</li>
 * <li>Approved Post-Submission</li>
 * <li>Disapproved Post-Submission</li>
 * <li>Canceled</li>
 * <li>Document Error Occurred</li>
 * </ul>
 */
public interface ProposalStateService {

    /**
     * Get the Proposal State type code based upon the current configuration of the
     * given proposal.  The Proposal State must be recomputed whenever the workflow
     * route status changes or when the proposal is submitted to the sponsor.  The
     * order matters.  For example, if the proposal is approved and then submitted,
     * the state is "Approved and Submitted".  If the proposal is submitted first and
     * then approved, the state is "Approved Post-Submission".
     * 
     * @param proposalDevelopmentDocument the proposal development document
     * @param isReject was the action taken a rejection of the document?
     * @return the proposal state type code
     */
    String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isReject);

}
