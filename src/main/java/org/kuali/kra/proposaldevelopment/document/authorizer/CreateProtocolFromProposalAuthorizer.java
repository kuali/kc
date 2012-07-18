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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
/**
 * This service class is used to do authorization for create protocol task for protocol document.  
 */
public class CreateProtocolFromProposalAuthorizer extends ProposalAuthorizer {

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.protocol.auth.ProtocolTask)
     */
    public boolean isAuthorized(String userId, ProposalTask task) {

        DevelopmentProposal proposal = (DevelopmentProposal)task.getDocument().getDevelopmentProposal();

        return ( canCreateProtocol() && hasProposalRequiredFields(proposal)); 
    }

    private boolean canCreateProtocol()
    {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        boolean canCreateProtocol = taskAuthenticationService.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
        return canCreateProtocol;
    }
        
    private boolean hasProposalRequiredFields(DevelopmentProposal proposal)
    {
        boolean validProposalRequiredFields=true;
             
        if (StringUtils.isEmpty(proposal.getTitle()))
        {
            validProposalRequiredFields = false;
        }
        if (StringUtils.isEmpty(proposal.getOwnedByUnitNumber()))
        {
            validProposalRequiredFields = false;
        }
        ProposalPerson person = proposal.getPrincipalInvestigator();
        if (person == null || StringUtils.isEmpty(person.getPersonId()))
        {
            validProposalRequiredFields = false;
        }
        return validProposalRequiredFields;
    }
    
}
