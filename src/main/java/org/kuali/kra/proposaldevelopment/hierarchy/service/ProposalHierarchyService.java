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

import org.kuali.kra.proposaldevelopment.hierarchy.IneligibleChildException;
import org.kuali.kra.proposaldevelopment.hierarchy.InvalidHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;

/**
 * This class...
 */

public interface ProposalHierarchyService {

    /**
     * This method determines if the proposal indicated by the proposalNumber is a Hierarchy parent
     * 
     * @param proposalNumber The number of the proposal in question
     * @return true if the proposal indicated by proposalNumber is a Hiearchy parent, false otherwise.
     * @throws InvalidHierarchyException is proposalNumber does not exist
     */
    public boolean isParent(String proposalNumber) throws InvalidHierarchyException;

    /**
     * This method determines if the proposal indicated by the proposalNumber is a Hierarchy child
     * 
     * @param proposalNumber The number of the proposal in question
     * @return true if the proposal indicated by proposalNumber is a Hiearchy child, false otherwise.
     * @throws IneligibleChildException if proposalNumber does not exist
     */
    public boolean isChild(String proposalNumber) throws IneligibleChildException;

    /**
     * This method takes the proposal indicated by initialChildProposalNumber, creates a Hierarchy
     * and links the proposal as the initial child.
     * 
     * @param initialChildProposalNumber 
     * @return the proposal number of the new hierarchy
     * @throws IneligibleChildException if the proposal is already a member of a hierarchy or does not exist
     */
    public String createHierarchy(String initialChildProposalNumber) throws IneligibleChildException;

    /**
     * This method links the proposal represented by newChildProposalNumber to the Hierarchy
     * represented by hierarchyProposalNumber.
     * 
     * @param hierarchyProposalNumber the hierarchy to link the new child to
     * @param newChildProposalNumber the proposal to link to the hierarchy
     * @throws InvalidHierarchyException if hierarchyProposalNumber is not a valid Hierarchy or does not exist
     * @throws IneligibleChildException if newChildProposalNumber is already a member of a hierarchy or does not exist
     */
    public void linkToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) throws InvalidHierarchyException, IneligibleChildException;

    /**
     * This method removes childProposalNumber from the hierarchy of which it is a member
     * 
     * @param childProposalNumber the proposal to remove
     * @throws IneligibleChildException if childProposalNumber is not a member of a hierarchy or does not exist
     */
    public void removeFromHierarchy(String childProposalNumber) throws IneligibleChildException;

    /**
     * This method returns the proposal number of the hierarchy to which childProposalNumber belongs
     * 
     * @param childProposalNumber the child proposal in question
     * @return the proposal number of the hierarchy to which childProposalNumber belongs
     * @throws IneligibleChildException if childProposalNumber is not a member of a hierarchy or does not exist
     */
    public String getHierarchyProposal(String childProposalNumber) throws IneligibleChildException;

}
