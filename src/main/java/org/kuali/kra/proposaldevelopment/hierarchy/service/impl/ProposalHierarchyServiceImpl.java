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
package org.kuali.kra.proposaldevelopment.hierarchy.service.impl;

import org.kuali.kra.proposaldevelopment.hierarchy.IneligibleChildException;
import org.kuali.kra.proposaldevelopment.hierarchy.InvalidHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;

/**
 * This class...
 */
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#createHierarchy(java.lang.String)
     */
    public String createHierarchy(String initialChildProposalNumber) throws IneligibleChildException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyProposal(java.lang.String)
     */
    public String getHierarchyProposal(String childProposalNumber) throws IneligibleChildException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#isChild(java.lang.String)
     */
    public boolean isChild(String proposalNumber) throws IneligibleChildException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#isParent(java.lang.String)
     */
    public boolean isParent(String proposalNumber) throws InvalidHierarchyException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#linkToHierarchy(java.lang.String, java.lang.String)
     */
    public void linkToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) throws InvalidHierarchyException,
            IneligibleChildException {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#removeFromHierarchy(java.lang.String)
     */
    public void removeFromHierarchy(String childProposalNumber) throws IneligibleChildException {
        // TODO Auto-generated method stub

    }

}
