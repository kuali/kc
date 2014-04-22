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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.data.DataObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;

@Component("proposalBudgetStatusService")
public class ProposalBudgetStatusServiceImpl implements ProposalBudgetStatusService {
    
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;

	public void saveBudgetFinalVersionStatus(ProposalDevelopmentDocument pdDocument) {
        ProposalBudgetStatus proposalBudgetStatus = getProposalBudgetStatus(pdDocument.getDevelopmentProposal().getProposalNumber());
        
        if (proposalBudgetStatus == null) {
            if (pdDocument.getDevelopmentProposal().getProposalNumber() != null) {
                proposalBudgetStatus = new ProposalBudgetStatus();
                proposalBudgetStatus.setProposalNumber(pdDocument.getDevelopmentProposal().getProposalNumber());
                proposalBudgetStatus.setBudgetStatusCode(pdDocument.getDevelopmentProposal().getBudgetStatus());
                getDataObjectService().save(proposalBudgetStatus);
            }
        } else if (proposalBudgetStatus.getBudgetStatusCode() == null || 
                !proposalBudgetStatus.getBudgetStatusCode().equals(pdDocument.getDevelopmentProposal().getBudgetStatus())) {
            proposalBudgetStatus.setBudgetStatusCode(pdDocument.getDevelopmentProposal().getBudgetStatus());
            getDataObjectService().save(proposalBudgetStatus);
        } // else there is no change - do nothing.
    }
    
    public void loadBudgetStatus(DevelopmentProposal proposal) {
        if (proposal != null) {
            ProposalBudgetStatus proposalBudgetStatus = getProposalBudgetStatus(proposal.getProposalNumber());
            if (proposalBudgetStatus != null) {
                proposal.setBudgetStatus(proposalBudgetStatus.getBudgetStatusCode());
                if (proposalBudgetStatus.getBudgetStatus() != null) {
                    proposal.setBudgetStatusDescription(proposalBudgetStatus.getBudgetStatus().getDescription());
                }
                else {
                    proposal.setBudgetStatusDescription("Unset");
                }
            }
        }
    }
    
    /**
     * This method returns the ProposalBudgetStatus that corresponds to the given proposal.
     * @param proposalNumber
     * @return ProposalBudgetStatus
     */
    protected ProposalBudgetStatus getProposalBudgetStatus(String proposalNumber) {
    	if (StringUtils.isBlank(proposalNumber)) {
    	    return null;
    	}
    	ProposalBudgetStatus proposalBudgetStatus = getDataObjectService().find(ProposalBudgetStatus.class, proposalNumber);
        return proposalBudgetStatus;
    }

    public void loadBudgetStatusByProposalDocumentNumber(String documentNumber) {
        DevelopmentProposal proposal = getDataObjectService().find(DevelopmentProposal.class, documentNumber);
        loadBudgetStatus(proposal);
    }

    public DataObjectService getDataObjectService() {
		return dataObjectService;
	}
    
    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
     
}
