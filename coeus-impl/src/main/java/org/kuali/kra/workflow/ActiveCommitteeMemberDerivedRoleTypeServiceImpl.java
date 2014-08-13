/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.*;


public class ActiveCommitteeMemberDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
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
	
	

	private boolean isQualified(CommitteeMembership membership, Map<String,String> qualification) {
	    if (LOG.isDebugEnabled()) {
	        LOG.debug(String.format("Checking qualification of membership:%s",membership));
	    }
	    boolean result = membership.isActive();
	    if (qualifyingCommitteeMembershipTypeCodes.size()>0) {
	        if (LOG.isDebugEnabled() && !qualifyingCommitteeMembershipTypeCodes.contains(membership.getMembershipTypeCode())) {
	            LOG.debug(String.format("Membership %s does not have membership type code in the qualifying map.", membership));
	        }
	        result &= qualifyingCommitteeMembershipTypeCodes.contains(membership.getMembershipTypeCode());
	    }
	    return result;
	}
	
	@Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		List<RoleMembership> members = new ArrayList<RoleMembership>();
		String committeeId = qualification.get(KcKimAttributes.COMMITTEE);
		
		if (LOG.isDebugEnabled()) {
		    LOG.debug(String.format("Running getRoleMembersFromDerivedRole for committee %s",committeeId));
		}
		
		if (!StringUtils.isEmpty(committeeId)) {
		    Committee committee = committeeService.getCommitteeById(committeeId);
    		if (committee != null ) {
    		    for (CommitteeMembershipBase membership : committee.getCommitteeMemberships()) {
    		        if (isQualified((CommitteeMembership) membership,qualification)) {
    		            members.add(RoleMembership.Builder.create(null, null, membership.getPersonId(), MemberType.PRINCIPAL, null).build());
    		            if (LOG.isDebugEnabled()) {
    		                LOG.debug(String.format("Adding %s for getRoleMembersFromDerivedRole for committee %s",committee));
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

        if (!StringUtils.isEmpty(committeeId)) {
            Committee committee = committeeService.getCommitteeById(committeeId);
            if (committee != null) {
                for (CommitteeMembershipBase membership : committee.getCommitteeMemberships()) {
                    if (isQualified((CommitteeMembership) membership,qualification) && membership.getPersonId()!=null && StringUtils.equals(principalId, membership.getPersonId())) {
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

    /*
     * Should override if derivedRoles should not to be cached.  Currently defaults to system-wide default.
     */
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }


}
