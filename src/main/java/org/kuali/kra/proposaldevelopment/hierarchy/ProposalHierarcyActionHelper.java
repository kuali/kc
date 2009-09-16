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
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class ProposalHierarcyActionHelper {
    private static final String FIELD_GENERIC = "newHierarchyProposal.x";
    private static final String FIELD_PARENT_NUMBER = "newHierarchyProposal.proposalNumber";
    private static final String FIELD_CHILD_NUMBER = "newHierarchyChildProposal.proposalNumber";

    private static final String MESSAGE_LINK_SUCCESS = "message.hierarchy.link.success";
    private static final String MESSAGE_CREATE_SUCCESS = "message.hierarchy.create.success";
    private static final String MESSAGE_SYNC_SUCCESS = "message.hierarchy.sync.success";
    private static final String MESSAGE_REMOVE_SUCCESS = "message.hierarchy.remove.success";

    private static final String WARNING_LINK_NO_FINAL_BUDGET = "warning.hierarchy.link.noFinalBudget";
    private static final String WARNING_LINK_DIFFERENT_SPONSOR = "warning.hierarchy.link.differentSponsor";
    
    private static final String ERROR_LINK_NOT_PARENT = "error.hierarchy.link.notParent";
    private static final String ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR = "error.hierarchy.link.noPrincipleInvestigator";
    private static final String ERROR_LINK_NO_BUDGET_VERSION = "error.hierarchy.link.noBudgetVersion";
    private static final String ERROR_REMOVE_PARENT_BUDGET_COMPLETE = "error.hierarchy.remove.parentBudgetComplete";
    private static final String ERROR_UNKNOWN = "error.hierarchy.unknown";

    ProposalHierarchyService hierarchyService;
    
    public void syncAllHierarchy(DevelopmentProposal hierarchyProposal) {
        // TODO rules
        try {
            getProposalHierarchyService().synchronizeAllChildren(hierarchyProposal);
            GlobalVariables.getMessageList().add(MESSAGE_SYNC_SUCCESS);

        }
        catch (Exception e) {
            GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
        }
    }
    
    public void removeFromHierarchy(DevelopmentProposal childProposal) {
        if (validateChildForRemoval(childProposal)) {
            try {
                getProposalHierarchyService().removeFromHierarchy(childProposal);
                GlobalVariables.getMessageList().add(MESSAGE_REMOVE_SUCCESS);
    
            }
            catch (Exception e) {
                GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
            }
        }
    }
    
    public void syncToHierarchyParent(DevelopmentProposal childProposal) {
        // TODO rules
        try {
            getProposalHierarchyService().synchronizeChild(childProposal);
            GlobalVariables.getMessageList().add(MESSAGE_SYNC_SUCCESS);

        }
        catch (Exception e) {
            GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
        }
    }

    public void createHierarchy(DevelopmentProposal initialChildProposal) {
        if (validateChildCandidate(initialChildProposal)) {
            try {
                String parentProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposal);
                GlobalVariables.getMessageList().add(MESSAGE_CREATE_SUCCESS, parentProposalNumber);
            }
            catch (Exception e) {
                GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
            }
        }
    }
    
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal) {
        if (validateParent(hierarchyProposal)) {
            boolean valid = true;
            valid &= validateChildCandidate(newChildProposal);
            valid &= validateChildCandidateForHierarchy(hierarchyProposal, newChildProposal);
            if (valid) {
                try {
                    getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal);
                    GlobalVariables.getMessageList().add(MESSAGE_LINK_SUCCESS, newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber());
                }
                catch (Exception e) {
                    GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
                }
            }
        }
    }

    public List<HierarchyProposalSummary> getHierarchySummaries(String proposalNumber) {
        List<HierarchyProposalSummary> retval = null;
        try {
            retval = getProposalHierarchyService().getProposalSummaries(proposalNumber);
        }
        catch (Exception e) {
            GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
        }
        return retval;
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return getProposalHierarchyService().getDevelopmentProposal(proposalNumber);
    }
    
    private ProposalHierarchyService getProposalHierarchyService() {
        if (hierarchyService == null) {
            hierarchyService = KraServiceLocator.getService(ProposalHierarchyService.class);
        }
        return hierarchyService;
    }
    
    private boolean validateParent(DevelopmentProposal proposal) {
        boolean valid = true;
        if (!proposal.isParent()) {
            GlobalVariables.getErrorMap().putError(FIELD_PARENT_NUMBER, ERROR_LINK_NOT_PARENT, new String[0]);
            valid = false;
        }
        return valid;        
    }
    
    private boolean validateChildCandidate(DevelopmentProposal proposal) {
        boolean valid = true;
        proposal.getProposalDocument().refreshReferenceObject("budgetDocumentVersions");
        if (proposal.getProposalDocument().getBudgetDocumentVersions().isEmpty()) {
            GlobalVariables.getErrorMap().putError(FIELD_CHILD_NUMBER, ERROR_LINK_NO_BUDGET_VERSION, new String[0]);
            valid = false;
        }
        else {
            if (!hasFinalBudget(proposal)) {
                GlobalVariables.getErrorMap().putWarning(FIELD_CHILD_NUMBER, WARNING_LINK_NO_FINAL_BUDGET, new String[] {proposal.getProposalNumber()});
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
            GlobalVariables.getErrorMap().putError(FIELD_CHILD_NUMBER, ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR, new String[0]);
            valid = false;
        }
        return valid;
    }

    private boolean validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        boolean valid = true;
        if (!StringUtils.equalsIgnoreCase(hierarchy.getSponsorCode(), child.getSponsorCode())) {
            GlobalVariables.getErrorMap().putWarning(FIELD_CHILD_NUMBER, WARNING_LINK_DIFFERENT_SPONSOR, new String[0]);
        }
        return valid;
    }
    
    private boolean validateChildForRemoval(DevelopmentProposal child) {
        boolean valid = true;
        try {
            DevelopmentProposal hierarchy = getProposalHierarchyService().lookupParent(child);
            if (hasCompleteBudget(hierarchy)) {
                GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_REMOVE_PARENT_BUDGET_COMPLETE, new String[0]);
                valid = false;
            }
        
        }
        catch (Exception e) {
            GlobalVariables.getErrorMap().putError(FIELD_GENERIC, ERROR_UNKNOWN, e.getMessage());
            valid = false;
        }
        return valid;
    }
    
    private boolean hasFinalBudget(DevelopmentProposal proposal) {
        boolean retval = false;
        for (BudgetDocumentVersion version : proposal.getProposalDocument().getBudgetDocumentVersions()) {
            if (version.getBudgetVersionOverview().isFinalVersionFlag()) {
                retval = true;
                break;
            }
        }
        return retval;
    }

    private boolean hasCompleteBudget(DevelopmentProposal proposal) {
        boolean retval = false;
        for (BudgetDocumentVersion version : proposal.getProposalDocument().getBudgetDocumentVersions()) {
            if (version.getBudgetVersionOverview().isFinalVersionFlag()) {
                if (StringUtils.equals(version.getBudgetVersionOverview().getBudgetStatus(), "1")) {
                    retval = true;
                }
                break;
            }
        }
        return retval;
    }
}
