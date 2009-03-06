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
package org.kuali.kra.committee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeMembershipService;

public class CommitteeMembershipServiceImpl implements CommitteeMembershipService {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleServiceImpl.class);

    private static final String REFERENCE_PERSON = "person";
    private static final String REFERENCE_ROLODEX = "rolodex";

    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Business Object Service. Injected by the Spring Framework.
     * 
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#addCommitteeMembership(org.kuali.kra.committee.bo.Committee, org.kuali.kra.committee.bo.CommitteeMembership)
     */
    public void addCommitteeMembership(Committee committee, CommitteeMembership committeeMembership) {
        
        committeeMembership.setCommitteeId(committee.getCommitteeId());
        committeeMembership.setMembershipId("0");
        committeeMembership.setSequenceNumber(0);

        //Refresh Person or Rolodex
        if(!StringUtils.isBlank(committeeMembership.getPersonId())) {
            committeeMembership.refreshReferenceObject(REFERENCE_PERSON);
        }else {
            committeeMembership.refreshReferenceObject(REFERENCE_ROLODEX);
        }
        
        committee.getCommitteeMemberships().add(committeeMembership);
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#deleteCommitteeMembership(org.kuali.kra.committee.bo.Committee)
     */
    public void deleteCommitteeMembership(Committee committee) {
        List<CommitteeMembership> deletedCommitteememberships = new ArrayList<CommitteeMembership>();
        for(CommitteeMembership committeeMembership : committee.getCommitteeMemberships()) {
            if(committeeMembership.isDelete()) {
                deletedCommitteememberships.add(committeeMembership);
            }
        }
        committee.getCommitteeMemberships().removeAll(deletedCommitteememberships);
    }
   
}
