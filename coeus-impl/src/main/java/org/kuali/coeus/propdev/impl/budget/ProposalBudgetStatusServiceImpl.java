/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
