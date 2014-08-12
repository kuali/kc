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
package org.kuali.kra.iacuc.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
/**
 * This service class is used to do authorization for create proposal task for proposal development document.  
 */
public class CreateProposalDevelopmentIacucProtocolAuthorizer extends IacucProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {

        ProtocolBase protocol = (ProtocolBase)task.getProtocol();

        return ( canCreateProposal() && hasProposalRequiredFields(protocol)); 
    }

    private boolean canCreateProposal()
    {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROPOSAL);       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        boolean canCreateProposal = taskAuthenticationService.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
        return canCreateProposal;
    }
        
    private boolean hasProposalRequiredFields(ProtocolBase protocol)
    {
        boolean validProposalRequiredFields=true;
             
        if (StringUtils.isEmpty(protocol.getTitle()))
        {
            validProposalRequiredFields = false;
        }
        if (StringUtils.isEmpty(protocol.getLeadUnitNumber()))
        {
            validProposalRequiredFields = false;
        }
        if (StringUtils.isEmpty(protocol.getPrincipalInvestigatorId()))
        {
            validProposalRequiredFields = false;
        }
        // find sponsor from funding source
        List<ProtocolFundingSourceBase> protocolFundingSources = protocol.getProtocolFundingSources();
        ProtocolFundingSourceBase sponsorProtocolFundingSource = null; 
        for(ProtocolFundingSourceBase protocolFundingSource : protocolFundingSources)
        {
            if ( protocolFundingSource.isSponsorFunding() )
            {
                sponsorProtocolFundingSource = protocolFundingSource;
                break;
            }
        }
        if(sponsorProtocolFundingSource == null)
        {
            validProposalRequiredFields = false;
        }
        
        return validProposalRequiredFields;
    }
    
}
