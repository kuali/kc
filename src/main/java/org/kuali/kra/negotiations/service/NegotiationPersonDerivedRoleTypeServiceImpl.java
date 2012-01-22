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
package org.kuali.kra.negotiations.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

/**
 * Negotiation Person Derived Role Type. Returns all contact persons for document related to negotiation.
 */
public class NegotiationPersonDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
    private NegotiationService negotiationService;
    
    /**
	 * Constructs a NegotiationPersonDerivedRoleTypeServiceImpl.java.
	 */
    public NegotiationPersonDerivedRoleTypeServiceImpl() {
        
    }	
	
	@Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		String negotiationId = qualification.get(KcKimAttributes.NEGOTIATION);
		List<RoleMembership> members = new ArrayList<RoleMembership>();
		
        if (StringUtils.isNotBlank(negotiationId)) {
            Negotiation negotiation = getBusinessObjectService().findBySinglePrimaryKey(Negotiation.class, negotiationId);
            Negotiable negotiableBo = getNegotiationService().getAssociatedObject(negotiation);
            if (negotiableBo != null) {
                List<NegotiationPersonDTO> persons = negotiableBo.getProjectPeople();
                filterListByRole(persons, roleName);
                for (NegotiationPersonDTO person : persons) {
                    if (StringUtils.isNotBlank(person.getPerson().getPersonId())) {
                        members.add(RoleMembership.Builder.create(null, null, person.getPerson().getPersonId(), MemberType.PRINCIPAL, null).build());
                    }
                }
            }
        }
	        
		return members;
	}
	
	/**
	 * Filter the list of negotiation persons by their role. Typically the role name
	 * is used to indicate PI, COI or KP. If the role name does not match any known
	 * role the list is not filtered.
	 * @param persons
	 * @param roleName
	 */
	protected void filterListByRole(List<NegotiationPersonDTO> persons, String roleName) {
	    if (StringUtils.equals(roleName, Constants.PRINCIPAL_INVESTIGATOR_ROLE)
	            || StringUtils.equals(roleName, Constants.CO_INVESTIGATOR_ROLE)
	            || StringUtils.equals(roleName, Constants.KEY_PERSON_ROLE)) {
    	    Iterator<NegotiationPersonDTO> iter = persons.iterator();
    	    while (iter.hasNext()) {
    	        NegotiationPersonDTO person = iter.next();
    	        if (!StringUtils.equals(person.getRoleCode(), roleName)) {
    	            iter.remove();
    	        }
    	    }
	    }
	}

    public NegotiationService getNegotiationService() {
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    @Override
    protected List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<String>();
        requiredAttributes.add(KcKimAttributes.NEGOTIATION);
        return requiredAttributes;
    }

    
}
