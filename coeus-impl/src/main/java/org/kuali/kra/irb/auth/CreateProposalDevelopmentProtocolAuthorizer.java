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
package org.kuali.kra.irb.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
/**
 * This service class is used to do authorization for create proposal task for proposal development document.  
 */
public class CreateProposalDevelopmentProtocolAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {

        Protocol protocol = (Protocol)task.getProtocol();

        return ( canCreateProposal() && hasProposalRequiredFields(protocol)); 
    }

    private boolean canCreateProposal() {
        return hasUnitPermission(GlobalVariables.getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL);
    }
        
    private boolean hasProposalRequiredFields(Protocol protocol)
    {
        boolean isProtocolSaved = protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isSaved();
        boolean validProposalRequiredFields=true;

        if (!isProtocolSaved)
        {
            validProposalRequiredFields = false;
        }
        
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
        ProtocolFundingSource sponsorProtocolFundingSource = null; 
        for(ProtocolFundingSourceBase protocolFundingSource : protocolFundingSources)
        {
            if ( protocolFundingSource.isSponsorFunding() )
            {
                sponsorProtocolFundingSource = (ProtocolFundingSource) protocolFundingSource;
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
