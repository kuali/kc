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

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class ProposalHierarcyActionHelper {
    ProposalHierarchyService hierarchyService;
    
    public void syncAllHierarchy(String hierarchyProposalNumber) {
        // TODO rules
        try {
            getProposalHierarchyService().synchronizeAllChildren(hierarchyProposalNumber);
            GlobalVariables.getMessageList().add("message.hierarchy.syncSuccessful");

        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("message.hierarchy.syncFailed", e.getMessage());
        }
    }
    
    public void removeFromHierarchy(String childProposalNumber) {
        // TODO rules
        try {
            getProposalHierarchyService().removeFromHierarchy(childProposalNumber);
            GlobalVariables.getMessageList().add("message.hierarchy.removeSuccessful");

        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("message.hierarchy.removeFailed", e.getMessage());
        }
    }
    
    public void syncToHierarchyParent(String childProposalNumber) {
        // TODO rules
        try {
            getProposalHierarchyService().synchronizeChild(childProposalNumber);
            GlobalVariables.getMessageList().add("message.hierarchy.syncSuccessful");

        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add("message.hierarchy.syncFailed", e.getMessage());
        }
    }
/*    
    public void createHierarchy(String initialChildProposalNumber) {
        // TODO rules
        try {
            String parentProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposalNumber);
            GlobalVariables.getMessageList().add("message.hierarchy.createSuccessful", parentProposalNumber);
        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add(new ErrorMessage("error.hierarchy.createFailure", e.getMessage()));
        }
    }
*/    
    public void createHierarchy(DevelopmentProposal initialChildProposal) {
        // TODO rules
        try {
            String parentProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposal);
            GlobalVariables.getMessageList().add("message.hierarchy.createSuccessful", parentProposalNumber);
        }
        catch (Exception e) {
            GlobalVariables.getMessageList().add(new ErrorMessage("error.hierarchy.createFailure", e.getMessage()));
        }
    }
    
    public void linkToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) {
        // TODO rules
        try {
            getProposalHierarchyService().linkToHierarchy(hierarchyProposalNumber, newChildProposalNumber);
            GlobalVariables.getMessageList().add("message.hierarchy.linkSuccessful", newChildProposalNumber, hierarchyProposalNumber);
        }
        catch (Exception e) {
            GlobalVariables.getErrorMap().putError("newHierarchyProposal.proposalNumber", "error.hierarchy.linkFailure", e.getMessage());
        }
    }

    public void linkChildToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) {
        // TODO rules
        try {
            getProposalHierarchyService().linkToHierarchy(hierarchyProposalNumber, newChildProposalNumber);
            GlobalVariables.getMessageList().add("message.hierarchy.linkSuccessful", newChildProposalNumber, hierarchyProposalNumber);
        }
        catch (Exception e) {
            GlobalVariables.getErrorMap().putError("newHierarchyChildProposal.proposalNumber", "error.hierarchy.linkFailure", e.getMessage());
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
}
