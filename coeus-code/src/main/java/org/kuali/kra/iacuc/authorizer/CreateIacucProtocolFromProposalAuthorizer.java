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
package org.kuali.kra.iacuc.authorizer;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.rice.krad.util.GlobalVariables;
/**
 * This service class is used to do authorization for create protocol task for protocol document.  
 */
public class CreateIacucProtocolFromProposalAuthorizer extends ProposalAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProposalTask task) {

        DevelopmentProposal proposal = (DevelopmentProposal)task.getDocument().getDevelopmentProposal();

        return ( canCreateProtocol() && hasProposalRequiredFields(proposal)); 
    }

    private boolean canCreateProtocol()
    {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_IACUC_PROTOCOL);       
        TaskAuthorizationService taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        boolean canCreateProtocol = taskAuthorizationService.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
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
