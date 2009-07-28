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

/**
 * This class...
 */
public interface ProposalHierarchySyncService {

    /**
     * This method...
     * @param childProposalNumber
     * @throws IneligibleChildException
     */
    public void synchronizeChild(String childProposalNumber) throws IneligibleChildException;
    
    /**
     * This method...
     * @param hierarchyProposalNumber
     * @throws InvalidHierarchyException
     */
    public void synchronizeAllChildren(String hierarchyProposalNumber) throws InvalidHierarchyException;
    
    /**
     * This method...
     * @param childProposalNumber
     * @return
     * @throws IneligibleChildException
     */
    public int computeChildHashCode(String childProposalNumber) throws IneligibleChildException;
}
