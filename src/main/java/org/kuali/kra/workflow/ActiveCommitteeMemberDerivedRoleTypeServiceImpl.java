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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;
import org.kuali.rice.kns.service.BusinessObjectService;


public class ActiveCommitteeMemberDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {
    
    private CommitteeService committeeService;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActiveCommitteeMemberDerivedRoleTypeServiceImpl.class);
    private static final String DEFAULT_QUALIFYING_COMMITTEE_MEMBERSHIP_TYPE_CODE = "1";
 
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
	}
	
	

	private boolean isQualified(CommitteeMembership membership, AttributeSet qualification) {
	    if (LOG.isDebugEnabled()) {
	        LOG.debug(String.format("Checking qualification of membership:%s",membership));
	    }
	    boolean result = membership.isActive();
	    if (qualifyingCommitteeMembershipTypeCodes.size()>0) {
	        if (LOG.isDebugEnabled() && !qualifyingCommitteeMembershipTypeCodes.contains(membership.getMembershipTypeCode())) {
	            LOG.debug(String.format("Membership %s does not have membership type code in the qualifying map."));
	        }
	        result &= qualifyingCommitteeMembershipTypeCodes.contains(membership.getMembershipTypeCode());
	    }
	    return result;
	}
	
	@Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, AttributeSet qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();
		String committeeId = qualification.get(KcKimAttributes.COMMITTEE);
		
		if (LOG.isDebugEnabled()) {
		    LOG.debug(String.format("Running getRoleMembersFromApplicationRole for committee %s",committeeId));
		}
		
		if (!StringUtils.isEmpty(committeeId)) {
		    Committee committee = committeeService.getCommitteeById(committeeId);
    		if (committee != null ) {
    		    for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
    		        if (isQualified(membership,qualification)) {
    		            members.add(new RoleMembershipInfo(null, null, membership.getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null) );
    		            if (LOG.isDebugEnabled()) {
    		                LOG.debug(String.format("Adding %s for getRoleMembersFromApplicationRole for committee %s",committee));
    		            }
    		        }
    		    }
    		}
		}
		
		return members;
	}

	@Override
	public boolean hasApplicationRole(
	        String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification){

   validateRequiredAttributesAgainstReceived(qualification);
        
        String committeeId = qualification.get(KcKimAttributes.COMMITTEE);

        if (!StringUtils.isEmpty(committeeId)) {
            Committee committee = committeeService.getCommitteeById(committeeId);
            if (committee != null) {
                for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
                    if (isQualified(membership,qualification) && membership.getPersonId()!=null && StringUtils.equals(principalId, membership.getPersonId())) {
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
        quals.add("committee");
        return quals;
    }

    public Set<String> getQualifyingCommitteeMembershipTypeCodes() {
        return qualifyingCommitteeMembershipTypeCodes;
    }

    public void setQualifyingCommitteeMembershipTypeCodes(Set<String> qualifyingCommitteeMembershipTypeCodes) {
        this.qualifyingCommitteeMembershipTypeCodes = qualifyingCommitteeMembershipTypeCodes;
    }
    
}
