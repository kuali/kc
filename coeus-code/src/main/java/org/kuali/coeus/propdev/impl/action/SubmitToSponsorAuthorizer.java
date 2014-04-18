/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.action;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;

/**
 * The Submit to Sponsor Authorizer determines if the user can
 * submit a proposal to Sponsor.  This is only allowed if the
 * person has the necessary permission and is not a child in a
 * hierarchy
 */
public class SubmitToSponsorAuthorizer extends ProposalAuthorizer {

    public boolean isAuthorized(String userId, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        return hasProposalPermission(userId, doc, PermissionConstants.SUBMIT_TO_SPONSOR) &&
               !doc.getDevelopmentProposal().isChild();
    }
}
