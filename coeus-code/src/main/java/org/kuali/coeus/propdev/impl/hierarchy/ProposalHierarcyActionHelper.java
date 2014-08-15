/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatusService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.*;


public class ProposalHierarcyActionHelper {
    private static final Log LOG = LogFactory.getLog(ProposalHierarcyActionHelper.class);

    private ProposalHierarchyService hierarchyService;
    private KcAuthorizationService authorizationService;
    private ParameterService parameterService;
    private ProposalBudgetStatusService proposalBudgetStatusService;

    public void syncAllHierarchy(ProposalDevelopmentDocument doc) {
        syncAllHierarchy(doc, false);
    }
    public void syncAllHierarchy(ProposalDevelopmentDocument doc, boolean allowEndDateChange) {
        if (validateHierarchyForSyncAll(doc.getDevelopmentProposal(), allowEndDateChange)) {
            try {
                getProposalHierarchyService().synchronizeAllChildren(doc);
                KNSGlobalVariables.getMessageList().add(MESSAGE_SYNC_SUCCESS);    
            }
            catch (ProposalHierarchyException e) {
                doUnexpectedError(e, FIELD_GENERIC, true);
            }
        }
    }
    
    public void removeFromHierarchy(DevelopmentProposal childProposal) {
        if (!getProposalHierarchyService().validateRemovePermissions(childProposal, GlobalVariables.getUserSession().getPrincipalId())) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, KeyConstants.AUTHORIZATION_VIOLATION, new String[0]);
        }
        else if (validateChildForRemoval(childProposal)) {
            try {
                getProposalHierarchyService().removeFromHierarchy(childProposal);
                KNSGlobalVariables.getMessageList().add(MESSAGE_REMOVE_SUCCESS);
    
            }
            catch (ProposalHierarchyException e) {
                doUnexpectedError(e, FIELD_GENERIC, true);
            }
        }
    }

    public void syncToHierarchyParent(DevelopmentProposal childProposal) {
        syncToHierarchyParent(childProposal, false);
    }
    
    public void syncToHierarchyParent(DevelopmentProposal childProposal, boolean allowEndDateChange) {
        DevelopmentProposal hierarchy = getProposalHierarchyService().getDevelopmentProposal(childProposal.getHierarchyParentProposalNumber());
        if (validateChildForSync(childProposal, hierarchy, allowEndDateChange)) {
            try {
                getProposalHierarchyService().synchronizeChild(childProposal);
                KNSGlobalVariables.getMessageList().add(MESSAGE_SYNC_SUCCESS);
    
            }
            catch (ProposalHierarchyException e) {
                doUnexpectedError(e, FIELD_GENERIC, true);
            }
        }
    }
    
    public void syncBudgetToParent(ProposalDevelopmentBudgetExt budget, DevelopmentProposal childProposal, boolean allowEndDateChange) {
        DevelopmentProposal hierarchy = getProposalHierarchyService().getDevelopmentProposal(childProposal.getHierarchyParentProposalNumber());
        if (validateChildForSync(childProposal, hierarchy, allowEndDateChange)) {
            try {
                getProposalHierarchyService().synchronizeChildProposalBudget(budget, childProposal);
                KNSGlobalVariables.getMessageList().add(MESSAGE_SYNC_SUCCESS);
    
            }
            catch (ProposalHierarchyException e) {
                doUnexpectedError(e, FIELD_GENERIC, true);
            }
        }
    }    

    public void createHierarchy(DevelopmentProposal initialChildProposal) {
        LOG.info(String.format("createHierarchy called with Proposal $s", initialChildProposal.getProposalNumber()));
        if (validateChildCandidate(initialChildProposal)) {
            try {
                MessageMap messageMap = GlobalVariables.getMessageMap();
                String parentProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposal);
                if (GlobalVariables.getMessageMap() != messageMap) {
                    GlobalVariables.getMessageMap().merge(messageMap);
                }
                KNSGlobalVariables.getMessageList().add(MESSAGE_CREATE_SUCCESS, parentProposalNumber);
            }
            catch (ProposalHierarchyException e) {
                doUnexpectedError(e, FIELD_GENERIC, true);
            }
        }
        LOG.info(String.format("createHierarchy completed", initialChildProposal.getProposalNumber()));
    }
    
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode) {
        linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode, false);
    }
    
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode, boolean allowEndDateChange) {
        if (!getAuthoriztionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), hierarchyProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY)
                || !getAuthoriztionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), newChildProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY)) {
            GlobalVariables.getMessageMap().putError(FIELD_CHILD_NUMBER, KeyConstants.AUTHORIZATION_VIOLATION, new String[0]);
        }
        else if (validateParent(hierarchyProposal)) {
            boolean valid = true;
            valid &= validateChildCandidate(newChildProposal);
            if (valid && validateChildCandidateForHierarchy(hierarchyProposal, newChildProposal, allowEndDateChange)) {
                try {

                    MessageMap messageMap = GlobalVariables.getMessageMap();
                    getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
                    if (GlobalVariables.getMessageMap() != messageMap) {
                        GlobalVariables.getMessageMap().merge(messageMap);
                    }
                    KNSGlobalVariables.getMessageList().add(MESSAGE_LINK_SUCCESS, newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber());
                }
                catch (ProposalHierarchyException e) {
                    doUnexpectedError(e, FIELD_GENERIC, true);
                }
            }
        }
    }
    
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) {
        List<HierarchyPersonnelSummary> retval = null;
        try {
            retval = getProposalHierarchyService().getHierarchyPersonnelSummaries(parentProposalNumber);
        } catch (ProposalHierarchyException e) {
            doUnexpectedError(e, FIELD_GENERIC, false);
        }
        
        return retval;
    }

    public List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) {
        List<HierarchyProposalSummary> retval = null;
        try {
            retval = getProposalHierarchyService().getHierarchyProposalSummaries(proposalNumber);
        } catch (ProposalHierarchyException e) {
            doUnexpectedError(e, FIELD_GENERIC, false);
        }
        return retval;
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return getProposalHierarchyService().getDevelopmentProposal(proposalNumber);
    }
    
    public List<DevelopmentProposal> getChildProposals(String parentProposalNumber) {
        List<DevelopmentProposal> childProposals = new ArrayList<DevelopmentProposal>();
        
        try {
            childProposals.addAll(getProposalHierarchyService().getHierarchyChildren(parentProposalNumber));
        } catch (ProposalHierarchyException e) {
            doUnexpectedError(e, FIELD_GENERIC, false);
        }
        
        return childProposals;
    }
    
    private ProposalHierarchyService getProposalHierarchyService() {
        if (hierarchyService == null) {
            hierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return hierarchyService;
    }
    
    private KcAuthorizationService getAuthoriztionService() {
        if (authorizationService == null) {
            authorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        }
        return authorizationService;
    }
    
    private boolean validateParent(DevelopmentProposal proposal) {
        boolean valid = true;
        if (!proposal.isParent()) {
            GlobalVariables.getMessageMap().putError(FIELD_PARENT_NUMBER, ERROR_LINK_NOT_PARENT, new String[0]);
            valid = false;
        }
        else if (hasCompleteBudget(proposal)) {
            GlobalVariables.getMessageMap().putError(FIELD_PARENT_NUMBER, ERROR_LINK_PARENT_BUDGET_COMPLETE, new String[0]);
            valid = false;
        }
        return valid;        
    }
    
    private boolean validateChildCandidate(DevelopmentProposal proposal) {
        boolean valid = true;
        proposal.getProposalDocument().refreshBudgetDocumentVersions();
        if (proposal.isInHierarchy()) {
            GlobalVariables.getMessageMap().putError(FIELD_CHILD_NUMBER, ERROR_LINK_ALREADY_MEMBER, new String[0]);
            return false;
        }
        if (proposal.getProposalDocument().getBudgetDocumentVersions().isEmpty()) {
            GlobalVariables.getMessageMap().putError(FIELD_CHILD_NUMBER, ERROR_LINK_NO_BUDGET_VERSION, new String[0]);
            valid = false;
        }
        else {
            if (!hasFinalBudget(proposal)) {
                GlobalVariables.getMessageMap().putWarning(FIELD_CHILD_NUMBER, WARNING_LINK_NO_FINAL_BUDGET, new String[] {proposal.getProposalNumber()});
            }
        }
        if (proposal.getPrincipalInvestigator() == null) {
            GlobalVariables.getMessageMap().putError(FIELD_CHILD_NUMBER, ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR, new String[0]);
            valid = false;
        }
        return valid;
    }

    private boolean validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child, boolean allowEndDateChange) {
        boolean valid = true;
        if (!StringUtils.equalsIgnoreCase(hierarchy.getSponsorCode(), child.getSponsorCode())) {
            GlobalVariables.getMessageMap().putWarning(FIELD_CHILD_NUMBER, WARNING_LINK_DIFFERENT_SPONSOR, new String[0]);
        }
        try {
            ProposalHierarchyErrorDto budgetError = getProposalHierarchyService().validateChildBudgetPeriods(hierarchy, child, allowEndDateChange);
            if (budgetError != null) {
                valid = false;
                GlobalVariables.getMessageMap().putError(FIELD_CHILD_NUMBER, budgetError.getErrorKey(), budgetError.getErrorParameters());
            }
        }
        catch (ProposalHierarchyException e) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_UNEXPECTED, e.getMessage());
            valid = false;            
        }
        return valid;
    }
    
    private boolean validateChildForRemoval(DevelopmentProposal child) {
        boolean valid = true;
        try {
            DevelopmentProposal hierarchy = getProposalHierarchyService().lookupParent(child);
            if (hasCompleteBudget(hierarchy)) {
                GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_REMOVE_PARENT_BUDGET_COMPLETE, new String[0]);
                valid = false;
            }
        
        }
        catch (ProposalHierarchyException e) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_UNEXPECTED, e.getMessage());
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
        String completeCode = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        getProposalBudgetStatusService().loadBudgetStatus(proposal);
        return StringUtils.equalsIgnoreCase(proposal.getBudgetStatus(), completeCode);
    }
    
    private void doUnexpectedError (ProposalHierarchyException e, String field, boolean rollback) {
        LOG.error(String.format("Unexpected error in Proposal Hierarchy handling: %s", e.toString()), e);
        if (rollback) {
            PlatformTransactionManager txMgr = KcServiceLocator.getService("transactionManager");
            txMgr.rollback(txMgr.getTransaction(null));
        }
        GlobalVariables.getMessageMap().putError(field, ERROR_UNEXPECTED, e.toString());
    }

    protected ParameterService getParameterService(){
        if (parameterService == null)
            parameterService = KcServiceLocator.getService(ParameterService.class);
        return parameterService;
    }
    protected ProposalBudgetStatusService getProposalBudgetStatusService (){
        if (proposalBudgetStatusService == null)
            proposalBudgetStatusService = KcServiceLocator.getService(ProposalBudgetStatusService.class);
        return proposalBudgetStatusService;
    }

    public boolean checkParentChildStatusMatch(ProposalDevelopmentDocument document) {
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        boolean match = true;
        try {
            if (proposal.isParent() 
                    && hasCompleteBudget(document) 
                    && !getProposalHierarchyService().allChildBudgetsAreComplete(proposal.getProposalNumber())) {
                match = false; 
                GlobalVariables.getMessageMap().putError(FIELD_PARENT_BUDGET_STATUS, ERROR_BUDGET_CHILD_STATUSES_NOT_COMPLETE);
            }
            else if (proposal.isChild() 
                    && !hasCompleteBudget(document) 
                    && hasCompleteBudget(getProposalHierarchyService().lookupParent(proposal))) {
                match = false;
            }        
        } catch (ProposalHierarchyException e) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_UNEXPECTED, e.getMessage());
            match = false;
        }
        return match;
    }
    
    public boolean validateChildForSync (DevelopmentProposal child, DevelopmentProposal hierarchy, boolean allowEndDateChange) {
        boolean valid = true;
        if (child.getPrincipalInvestigator() == null) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_SYNC_NO_PRINCIPLE_INVESTIGATOR, child.getProposalNumber());
            valid = false;
        }
        try {
            ProposalHierarchyErrorDto budgetError = getProposalHierarchyService().validateChildBudgetPeriods(hierarchy, child, allowEndDateChange);
            if (budgetError != null) {
                valid = false;
                GlobalVariables.getMessageMap().putError(FIELD_CHILD_NUMBER, budgetError.getErrorKey(), budgetError.getErrorParameters());
            }
        }
        catch (ProposalHierarchyException e) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_UNEXPECTED, e.getMessage());
            valid = false;            
        }
        return valid;
    }
    
    public boolean validateHierarchyForSyncAll (DevelopmentProposal hierarchy, boolean allowEndDateChange) {
        boolean valid = true;
        try {
            for (DevelopmentProposal child : getProposalHierarchyService().getHierarchyChildren(hierarchy.getProposalNumber())) {
                valid &= validateChildForSync(child, hierarchy, allowEndDateChange);
            }
        }
        catch (ProposalHierarchyException e) {
            GlobalVariables.getMessageMap().putError(FIELD_GENERIC, ERROR_UNEXPECTED, e.getMessage());
            valid = false;
        }
        return valid;
    }
    
    private boolean hasCompleteBudget(ProposalDevelopmentDocument pdDoc) {
        boolean retval = false;
        String completeCode = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);

        for (BudgetDocumentVersion version : pdDoc.getBudgetDocumentVersions()) {
            if (!(version.getBudgetVersionOverview().getBudgetStatus() == null ) && version.getBudgetVersionOverview().getBudgetStatus().equalsIgnoreCase(completeCode)) {
                retval = true;
                break;
            }
        }
        return retval;
    }
}
