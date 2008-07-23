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
package org.kuali.kra.proposaldevelopment.service;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

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
     * given proposal.
     * @param proposalDevelopmentDocument the proposal development document
     * @return the proposal state type code
     */
    public String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument);
}
