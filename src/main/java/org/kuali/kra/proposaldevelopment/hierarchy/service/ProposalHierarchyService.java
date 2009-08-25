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
package org.kuali.kra.proposaldevelopment.hierarchy.service;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchyChild;

/**
 * This class...
 */

public interface ProposalHierarchyService {

    /**
     * This method takes the proposal indicated by initialChildProposalNumber, creates a Hierarchy
     * and links the proposal as the initial child.
     * 
     * @param initialChildProposalNumber 
     * @return the proposal number of the new hierarchy
     * @throws ProposalHierarchyException if the proposal is already a member of a hierarchy or does not exist
     */
    public String createHierarchy(String initialChildProposalNumber) throws ProposalHierarchyException;

    /**
     * This method links the proposal represented by newChildProposalNumber to the Hierarchy
     * represented by hierarchyProposalNumber.
     * 
     * @param hierarchyProposalNumber the hierarchy to link the new child to
     * @param newChildProposalNumber the proposal to link to the hierarchy
     * @throws ProposalHierarchyException if hierarchyProposalNumber is not a valid Hierarchy or does not exist
     * or if newChildProposalNumber is already a member of a hierarchy or does not exist
     */
    public void linkToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) throws ProposalHierarchyException;

    /**
     * This method removes childProposalNumber from the hierarchy of which it is a member
     * 
     * @param childProposalNumber the proposal to remove
     * @throws ProposalHierarchyException if childProposalNumber is not a member of a hierarchy or does not exist
     */
    public void removeFromHierarchy(String childProposalNumber) throws ProposalHierarchyException;

    /**
     * This method returns the proposal number of the hierarchy to which childProposalNumber belongs
     * 
     * @param childProposalNumber the child proposal in question
     * @return the proposal number of the hierarchy to which childProposalNumber belongs
     * @throws ProposalHierarchyException if childProposalNumber is not a member of a hierarchy or does not exist
     */
    public String getHierarchyParent(String childProposalNumber) throws ProposalHierarchyException;

    /**
     * This method synchronizes the contents of one child into its hierarchy.  If the child has changed since its last synchronization, the parent is reaggregated.
     * @param childProposalNumber the child proposal in question
     * @throws ProposalHierarchyException if childProposalNumber is not a member of a hierarchy or does not exist
     */
    public void synchronizeChild(String childProposalNumber) throws ProposalHierarchyException;
    
    /**
     * This method  synchronizes the contents of all children into the hierarchy.  If any child has changed since its last synchronization, the parent is reaggregated.
     * @param hierarchyProposalNumber the hierarchy in question
     * @throws ProposalHierarchyException if hierarchyProposalNumber is not a valid Hierarchy or does not exist
     */
    public void synchronizeAllChildren(String hierarchyProposalNumber) throws ProposalHierarchyException;
    
    /**
     * This method aggregated the contents of the "parent" and children of the hierarchy into a single proposal
     * @param hierarchyProposalNumber the hierarchy in question
     * @throws ProposalHierarchyException if hierarchyProposalNumber is not a valid Hierarchy or does not exist
     */
    public void aggregateHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException;
    
    public List<HierarchyProposalSummary> getProposalSummaries(String proposalNumber) throws ProposalHierarchyException;

    public DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException;
    public ProposalHierarchyChild getHierarchyChild(String childProposalNumber) throws ProposalHierarchyException;
}
