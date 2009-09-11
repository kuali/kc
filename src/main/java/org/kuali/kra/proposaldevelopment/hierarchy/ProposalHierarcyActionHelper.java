/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.hierarchy;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class ProposalHierarcyActionHelper {
    ProposalHierarchyService hierarchyService;
    
    public void syncAllHierarchy(DevelopmentProposal hierarchyProposal) {
        // TODO rules
        try {
            getProposalHierarchyService().synchronizeAllChildren(hierarchyProposal);
            GlobalVariables.getMessageList().add("message.hierarchy.syncSuccessful");

        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("error.hierarchy.syncFailure", e.getMessage());
        }
    }
    
    public void removeFromHierarchy(DevelopmentProposal childProposal) {
        // TODO rules
        try {
            getProposalHierarchyService().removeFromHierarchy(childProposal);
            GlobalVariables.getMessageList().add("message.hierarchy.removeSuccessful");

        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("error.hierarchy.removeFailure", e.getMessage());
        }
    }
    
    public void syncToHierarchyParent(DevelopmentProposal childProposal) {
        // TODO rules
        try {
            getProposalHierarchyService().synchronizeChild(childProposal);
            GlobalVariables.getMessageList().add("message.hierarchy.syncSuccessful");

        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("error.hierarchy.syncFailure", e.getMessage());
        }
    }

    public void createHierarchy(DevelopmentProposal initialChildProposal) {
        if (validateInitialChildCandidate(initialChildProposal)) {
            try {
                String parentProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposal);
                GlobalVariables.getMessageList().add("message.hierarchy.create.success", parentProposalNumber);
            }
            catch (Exception e) {
                GlobalVariables.getMessageList().add(new ErrorMessage("error.hierarchy.createFailure", e.getMessage()));
            }
        }
    }
    
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal) {
        boolean valid = validateChildCandidate(newChildProposal);
        valid &= validateChildCandidateForHierarchy(hierarchyProposal, newChildProposal);
        if (valid) {
            try {
                getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal);
                GlobalVariables.getMessageList().add("message.hierarchy.link.success", newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber());
            }
            catch (Exception e) {
                GlobalVariables.getErrorMap().putError("newHierarchyProposal.proposalNumber", "error.hierarchy.linkFailure", e.getMessage());
            }
        }
    }

    public void linkChildToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal) {
        boolean valid = validateChildCandidate(newChildProposal);
        valid &= validateChildCandidateForHierarchy(hierarchyProposal, newChildProposal);
        if (valid) {
            try {
                getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal);
                GlobalVariables.getMessageList().add("message.hierarchy.link.success", newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber());
            }
            catch (Exception e) {
                GlobalVariables.getErrorMap().putError("newHierarchyChildProposal.proposalNumber", "error.hierarchy.linkFailure", e.getMessage());
            }
        }
    }
    
    public List<HierarchyProposalSummary> getHierarchySummaries(String proposalNumber) {
        List<HierarchyProposalSummary> retval = null;
        try {
            retval = getProposalHierarchyService().getProposalSummaries(proposalNumber);
        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("error.hierarchy.displayFailure", e.getMessage());
        }
        return retval;
    }

    
    private ProposalHierarchyService getProposalHierarchyService() {
        if (hierarchyService == null) {
            hierarchyService = KraServiceLocator.getService(ProposalHierarchyService.class);
        }
        return hierarchyService;
    }
    
    private boolean validateChildCandidate(DevelopmentProposal proposal) {
        boolean valid = true;
        proposal.getProposalDocument().refreshReferenceObject("budgetDocumentVersions");
        if (proposal.getProposalDocument().getBudgetDocumentVersions().isEmpty()) {
            GlobalVariables.getErrorMap().putError("newHierarchyChildProposal.proposalNumber", "error.hierarchy.link.noBudgetVersion", new String[0]);
            //GlobalVariables.getMessageList().add(new ErrorMessage("error.hierarchy.link.noBudgetVersion", new String[0]));
            valid = false;
        }
        else {
            boolean isFinalVersion = false;
            for (BudgetDocumentVersion version : proposal.getProposalDocument().getBudgetDocumentVersions()) {
                if (version.getBudgetVersionOverview().isFinalVersionFlag()) {
                    isFinalVersion = true;
                    break;
                }
            }
            if (!isFinalVersion) {
                GlobalVariables.getErrorMap().putWarning("newHierarchyChildProposal.proposalNumber", "message.hierarchy.link.noFinalBudget", new String[] {proposal.getProposalNumber()});
            }
        }
        boolean principleInvestigatorPresent = false;
        for (ProposalPerson person : proposal.getProposalPersons()) {
           if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), "PI")) {
               principleInvestigatorPresent = true;
               break;
           }
        }
        if (!principleInvestigatorPresent) {
            GlobalVariables.getErrorMap().putError("newHierarchyChildProposal.proposalNumber", "error.hierarchy.link.noPrincipleInvestigator", new String[0]);
            valid = false;
        }
        return valid;
    }
    
    private boolean validateInitialChildCandidate(DevelopmentProposal proposal) {
        boolean valid = validateChildCandidate(proposal);
        if (proposal.getOwnedByUnit() == null) {
            // TODO error message
            valid = false;
        }
        return valid;
    }
    
    private boolean validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        boolean valid = true;
        if (!StringUtils.equalsIgnoreCase(hierarchy.getSponsorCode(), child.getSponsorCode())) {
            GlobalVariables.getErrorMap().putWarning("newHierarchyChildProposal.proposalNumber", "message.hierarchy.link.differentSponsor", new String[0]);
        }
        
        return valid;
    }
}
