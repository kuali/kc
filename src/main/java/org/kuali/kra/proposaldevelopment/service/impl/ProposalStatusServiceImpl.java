/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

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
    
    public void saveBudgetFinalVersionStatus(BudgetDocument budgetDocument) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)budgetDocument.getParentDocument();
        ProposalBudgetStatus proposalStatus = getProposalStatus(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        
        if (proposalStatus == null) {
            if (proposalDevelopmentDocument != null && proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber() != null) {
                proposalStatus = new ProposalBudgetStatus();
                proposalStatus.setProposalNumber(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber());
                proposalStatus.setBudgetStatusCode(proposalDevelopmentDocument.getDevelopmentProposal().getBudgetStatus());
                businessObjectService.save(proposalStatus);
            }
        } else if (!ObjectUtils.isNull(budgetDocument.getParentDocument())) {
            if (proposalStatus.getBudgetStatusCode() == null && proposalDevelopmentDocument.getDevelopmentProposal().getBudgetStatus() != null) {
                proposalStatus.setBudgetStatusCode(proposalDevelopmentDocument.getDevelopmentProposal().getBudgetStatus());
                businessObjectService.save(proposalStatus);
            } else if (proposalStatus.getBudgetStatusCode() != null 
                    && !proposalStatus.getBudgetStatusCode().equals(proposalDevelopmentDocument.getDevelopmentProposal().getBudgetStatus())) {
                proposalStatus.setBudgetStatusCode(proposalDevelopmentDocument.getDevelopmentProposal().getBudgetStatus());
                businessObjectService.save(proposalStatus);
            }
        } // else no change or brand-new document; do nothing
        
        // Also save other budget versions. Can't map this in ORM because we don't want to save this version.
        if (!ObjectUtils.isNull(budgetDocument.getParentDocument())) {
            List<BudgetDocumentVersion> budgetDocumentVersions = budgetDocument.getParentDocument().getBudgetDocumentVersions();
            for (BudgetDocumentVersion documentVersion: budgetDocumentVersions) {
                BudgetVersionOverview version = documentVersion.getBudgetVersionOverview();
                if (!version.getBudgetVersionNumber().equals(budgetDocument.getBudget().getBudgetVersionNumber())) {
                    BudgetVersionOverview dbVersion = getBudgetVersion(version);
                    if (dbVersion == null || dbVersion.isFinalVersionFlag() != version.isFinalVersionFlag()) {
                        businessObjectService.save(version);
                    }
                }
            }
        }
    }
    
    public void loadBudgetStatus(DevelopmentProposal proposal) {
        ProposalBudgetStatus proposalStatus = getProposalStatus(proposal.getProposalNumber());
        if (proposalStatus != null) {
            proposal.setBudgetStatus(proposalStatus.getBudgetStatusCode());
        }
    }
    
    /**
     * This method returns the ProposalStatus that corresponds to the given proposal.
     * @param proposalNumber
     * @return ProposalStatus
     */
    private ProposalBudgetStatus getProposalStatus(String proposalNumber) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(Constants.PROPOSAL_NUMBER, proposalNumber);
        ProposalBudgetStatus proposalStatus = (ProposalBudgetStatus) boService.findByPrimaryKey(ProposalBudgetStatus.class, keyMap);
        return proposalStatus;
    }
    
    private BudgetVersionOverview getBudgetVersion(BudgetVersionOverview version) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("budgetId", version.getBudgetId());
        BudgetVersionOverview dbVersion = (BudgetVersionOverview) boService.findByPrimaryKey(BudgetVersionOverview.class, keyMap);
        return dbVersion;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void loadBudgetStatus(String proposalNumber) {
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(Constants.PROPOSAL_NUMBER, proposalNumber);
        DevelopmentProposal proposal = (DevelopmentProposal)businessObjectService.findByPrimaryKey(DevelopmentProposal.class, keyMap);
        loadBudgetStatus(proposal);
    }

    public void loadBudgetStatusByProposalDocumentNumber(String documentNumber) {
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", documentNumber);
        DevelopmentProposal proposal = (DevelopmentProposal)businessObjectService.findByPrimaryKey(DevelopmentProposal.class, keyMap);
        loadBudgetStatus(proposal);
    }
    
    
}
