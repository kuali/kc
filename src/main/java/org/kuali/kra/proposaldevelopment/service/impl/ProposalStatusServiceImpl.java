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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

public class ProposalStatusServiceImpl implements ProposalStatusService {
    
    private BusinessObjectService businessObjectService;
    
    public void saveBudgetFinalVersionStatus(ProposalDevelopmentDocument pdDocument) {
        ProposalBudgetStatus proposalStatus = getProposalStatus(pdDocument.getDevelopmentProposal().getProposalNumber());
        
        if (proposalStatus == null) {
            if (pdDocument.getDevelopmentProposal().getProposalNumber() != null) {
                proposalStatus = new ProposalBudgetStatus();
                proposalStatus.setProposalNumber(pdDocument.getDevelopmentProposal().getProposalNumber());
                proposalStatus.setBudgetStatusCode(pdDocument.getDevelopmentProposal().getBudgetStatus());
                businessObjectService.save(proposalStatus);
            }
        } else if (proposalStatus.getBudgetStatusCode() == null || 
                !proposalStatus.getBudgetStatusCode().equals(pdDocument.getDevelopmentProposal().getBudgetStatus())) {
            proposalStatus.setBudgetStatusCode(pdDocument.getDevelopmentProposal().getBudgetStatus());
            businessObjectService.save(proposalStatus);
        } // else there is no change - do nothing.
    }
    
    public void loadBudgetStatus(DevelopmentProposal proposal) {
        if (proposal != null) {
            ProposalBudgetStatus proposalStatus = getProposalStatus(proposal.getProposalNumber());
            if (proposalStatus != null) {
                proposal.setBudgetStatus(proposalStatus.getBudgetStatusCode());
                if (proposalStatus.getBudgetStatus() != null) {
                    proposal.setBudgetStatusDescription(proposalStatus.getBudgetStatus().getDescription());
                }
                else {
                    proposal.setBudgetStatusDescription("Unset");
                }
            }
        }
    }
    
    /**
     * This method returns the ProposalStatus that corresponds to the given proposal.
     * @param proposalNumber
     * @return ProposalStatus
     */
    protected ProposalBudgetStatus getProposalStatus(String proposalNumber) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(Constants.PROPOSAL_NUMBER, proposalNumber);
        ProposalBudgetStatus proposalStatus = (ProposalBudgetStatus) boService.findByPrimaryKey(ProposalBudgetStatus.class, keyMap);
        return proposalStatus;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void loadBudgetStatusByProposalDocumentNumber(String documentNumber) {
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", documentNumber);
        DevelopmentProposal proposal = (DevelopmentProposal)businessObjectService.findByPrimaryKey(DevelopmentProposal.class, keyMap);
        loadBudgetStatus(proposal);
    }
    
    
}
