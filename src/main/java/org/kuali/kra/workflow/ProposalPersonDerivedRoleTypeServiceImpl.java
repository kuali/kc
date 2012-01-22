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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

public class ProposalPersonDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    public static final String PRIMARY_INVESTIGATOR_ROLE_NAME = "PrimaryInvestigator";
    public static final String CO_INVESTIGATOR_ROLE_NAME = "CoInvestigator";
    public static final String KEYPERSON_ROLE_NAME = "KeyPerson";
    private static final Map<String, String> proposalRoleCodeConsants = new HashMap<String, String>();
    
    static {
        proposalRoleCodeConsants.put(PRIMARY_INVESTIGATOR_ROLE_NAME, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        proposalRoleCodeConsants.put(CO_INVESTIGATOR_ROLE_NAME, Constants.CO_INVESTIGATOR_ROLE);
        proposalRoleCodeConsants.put(KEYPERSON_ROLE_NAME, Constants.KEY_PERSON_ROLE);
    }
    
	private ProposalPersonService proposalPersonService;
	
	protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KcKimAttributes.PROPOSAL);
	}
	
	@Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		String proposalNumber = qualification.get(KcKimAttributes.PROPOSAL);
		List<RoleMembership> members = new ArrayList<RoleMembership>();
		
        if (StringUtils.isNotBlank(proposalNumber)) {
            List<ProposalPerson> proposalPersons = getProposalPersonService().getProposalKeyPersonnel(proposalNumber, roleName);
            for ( ProposalPerson proposalPerson : proposalPersons ) {
                if ( StringUtils.isNotBlank(proposalPerson.getPersonId()) ) {
                    members.add( RoleMembership.Builder.create(null, null, proposalPerson.getPersonId(), MemberType.PRINCIPAL, null).build() );
                }
            }
        }
	        
		return members;
	}

	@Override
	public boolean hasDerivedRole(
			String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification){
		validateRequiredAttributesAgainstReceived(qualification);
	
		String proposalNumber = qualification.get(KcKimAttributes.PROPOSAL);
		
		if (StringUtils.isNotBlank(proposalNumber)) {
            List<ProposalPerson> proposalPersons = getProposalPersonService().getProposalKeyPersonnel(proposalNumber, roleName);
            for ( ProposalPerson proposalPerson : proposalPersons ) {
                if ( StringUtils.isNotBlank(proposalPerson.getPersonId()) &&  proposalPerson.getPersonId().equalsIgnoreCase(principalId)) {
                    return true;
                }
            }
		} 
     
		return false;
	}

    public ProposalPersonService getProposalPersonService() {
        return proposalPersonService;
    }

    public void setProposalPersonService(ProposalPersonService proposalPersonService) {
        this.proposalPersonService = proposalPersonService;
    }

}
