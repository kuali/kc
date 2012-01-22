/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;


public class ActiveCommitteeMemberOnScheduledDateDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
    private CommitteeService committeeService;
    private CommitteeScheduleService committeeScheduleService;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActiveCommitteeMemberOnScheduledDateDerivedRoleTypeServiceImpl.class);
    private static final String DEFAULT_QUALIFYING_COMMITTEE_MEMBERSHIP_TYPE_CODE = "1";
    
    //Must a membership be a voting one to qualify for the role?
    /**
     * Set contains what membership type codes will qualify for the role.
     * This should be set in the configuration of the service, but will default
     * to have type
     */
    protected Set<String> qualifyingCommitteeMembershipTypeCodes;
    
    public void init() {
        if (qualifyingCommitteeMembershipTypeCodes == null) {
            LOG.info(String.format("No qualifying committee membership type codes were specified, defaulting to type code %s.",DEFAULT_QUALIFYING_COMMITTEE_MEMBERSHIP_TYPE_CODE));
            qualifyingCommitteeMembershipTypeCodes = new HashSet<String>();
            qualifyingCommitteeMembershipTypeCodes.add(DEFAULT_QUALIFYING_COMMITTEE_MEMBERSHIP_TYPE_CODE);
        }
    }
    
    protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KcKimAttributes.COMMITTEE);
		requiredAttributes.add(KcKimAttributes.COMMITTEESCHEDULE);
	}
	
	

	private boolean isQualified(CommitteeMembership membership, CommitteeSchedule schedule, Map<String,String> qualification) {
	    if (LOG.isDebugEnabled()) {
	        LOG.debug(String.format("Checking qualification of membership:%s",membership));
	    }
	    boolean result = true;
	    if (qualifyingCommitteeMembershipTypeCodes.size()>0) {
	        if (LOG.isDebugEnabled() && !qualifyingCommitteeMembershipTypeCodes.contains(membership.getMembershipTypeCode())) {
	            LOG.debug(String.format("Membership %s does not have membership type code in the qualifying map."));
	        }
	        result &= qualifyingCommitteeMembershipTypeCodes.contains(membership.getMembershipTypeCode());
	    }
	    boolean isActiveOnScheduledDate = false;
	    if (schedule.getScheduledDate() != null ) {
	       isActiveOnScheduledDate = membership.isActive(schedule.getScheduledDate());
        }
	    if (LOG.isDebugEnabled() ) {
	       LOG.debug(String.format("isActive for schedule %s returns %s", schedule, isActiveOnScheduledDate ));
	    }
	    result &= isActiveOnScheduledDate;
	    
	    return result;
	}
	
	@Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		List<RoleMembership> members = new ArrayList<RoleMembership>();
		String committeeId = qualification.get(KcKimAttributes.COMMITTEE);
		String scheduleId = qualification.get(KcKimAttributes.COMMITTEESCHEDULE);
		
		if (LOG.isDebugEnabled()) {
		    LOG.debug(String.format("Running getRoleMembersFromDerivedRole for committee %s",committeeId));
		}
		
		if (!StringUtils.isEmpty(committeeId) && !StringUtils.isEmpty(scheduleId)) {
		    Committee committee = committeeService.getCommitteeById(committeeId);
		    CommitteeSchedule schedule = committeeService.getCommitteeSchedule(committee, scheduleId);
    		if (committee != null && schedule != null ) {
    		    for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
    		        if (isQualified(membership,schedule,qualification)) {
    		            members.add(RoleMembership.Builder.create(null, null, membership.getPersonId(), MemberType.PRINCIPAL, null).build() );
    		            if (LOG.isDebugEnabled()) {
    		                LOG.debug(String.format("Adding %s for getRoleMembersFromDerivedRole for committee %s, schedule %s",committeeId,scheduleId));
    		            }
    		        }
    		    }
    		}
		}
		
		return members;
	}

	@Override
	public boolean hasDerivedRole(
	        String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification){

   validateRequiredAttributesAgainstReceived(qualification);
        
        String committeeId = qualification.get(KcKimAttributes.COMMITTEE);
        String scheduleId = qualification.get(KcKimAttributes.COMMITTEESCHEDULE);
        
        if (!StringUtils.isEmpty(committeeId) && !StringUtils.isEmpty(scheduleId)) {
            Committee committee = committeeService.getCommitteeById(committeeId);
            CommitteeSchedule schedule = committeeService.getCommitteeSchedule(committee, scheduleId);
            if (committee != null && schedule!=null) {
                for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
                    if (membership.getPersonId()!=null && StringUtils.equals(principalId, membership.getPersonId()) && isQualified(membership,schedule,qualification)) {
                        return true;
                    }
                }
            }
        }
        return false;
	}

    public CommitteeService getCommitteeService() {
        return committeeService;
    }

    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
	
    @Override
    public List<String> getQualifiersForExactMatch() {
        ArrayList<String> quals = new ArrayList<String>();
        quals.add(KcKimAttributes.COMMITTEE);
        quals.add(KcKimAttributes.COMMITTEESCHEDULE);
        return quals;
    }

    public Set<String> getQualifyingCommitteeMembershipTypeCodes() {
        return qualifyingCommitteeMembershipTypeCodes;
    }

    public void setQualifyingCommitteeMembershipTypeCodes(Set<String> qualifyingCommitteeMembershipTypeCodes) {
        this.qualifyingCommitteeMembershipTypeCodes = qualifyingCommitteeMembershipTypeCodes;
    }

    public CommitteeScheduleService getCommitteeScheduleService() {
        return committeeScheduleService;
    }

    public void setCommitteeScheduleService(CommitteeScheduleService committeeScheduleService) {
        this.committeeScheduleService = committeeScheduleService;
    }
    
}
