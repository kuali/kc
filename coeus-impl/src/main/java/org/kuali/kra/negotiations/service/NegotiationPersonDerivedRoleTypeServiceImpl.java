/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.negotiations.service;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        return true;
    }    

    
}
