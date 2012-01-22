/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;

/**
 * The Alter Proposal Data Authorizer will only a user to
 * alter the proposal data under the following conditions:
 * 
 *       1. The proposal has been submitted to workflow and is enroute.
 *       2. The proposal has not been submitted to a sponsor.
 *       3. The user has permission to alter the data for the proposal.
 *       4. The proposal is not a child of a proposal in approval routing.
 */
public class AlterProposalDataAuthorizer extends ProposalAuthorizer {

    org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AlterProposalDataAuthorizer.class);
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.rice.kns.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String userId, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        //standard is authorized calculation without taking child status into account.
        boolean ret = kraWorkflowService.isEnRoute(doc) &&
        !doc.getDevelopmentProposal().getSubmitFlag() &&
        hasProposalPermission(userId, doc, PermissionConstants.ALTER_PROPOSAL_DATA);
       
        //check to see if the parent is enroute, if so deny the edit attempt.
        if( doc.getDevelopmentProposal().isChild() ) {
            ProposalHierarchyService hService = KraServiceLocator.getService( ProposalHierarchyService.class);
            try {
                if( hService.getParentWorkflowDocument(doc).isEnroute() )
                    ret = false;
            } catch (ProposalHierarchyException e) {
                LOG.error( String.format( "Exception looking up parent of DevelopmentProposal %s, authorizer is going to deny edit access to this child.", doc.getDevelopmentProposal().getProposalNumber()),e );
                ret = false;
            }
        }
        return ret;
    }      
     
}
