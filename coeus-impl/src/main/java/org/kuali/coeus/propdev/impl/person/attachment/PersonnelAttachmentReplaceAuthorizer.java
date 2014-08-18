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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;

public class PersonnelAttachmentReplaceAuthorizer extends ProposalAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        boolean result = false;
        if (doc.getDevelopmentProposal().getProposalState() != null) {
            boolean hasPerm = hasProposalPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE);
            boolean isInProgress = StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getProposalState().getDescription(), "In Progress");
            boolean isApprovalPending = StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getProposalState().getDescription(), "Approval Pending");
            boolean isRevisionRequested = StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getProposalState().getDescription(), "Revisions Requested");
            result = hasPerm && (isInProgress || isApprovalPending || isRevisionRequested);
        }
        return result;
    }

}
