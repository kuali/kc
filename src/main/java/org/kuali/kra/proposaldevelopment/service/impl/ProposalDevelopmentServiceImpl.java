/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UserRole;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RightConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.service.UserRoleService;

// TODO : extends PersistenceServiceStructureImplBase  is a hack to temporarily resolve get class descriptor.
public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {
    private BusinessObjectService businessObjectService;
    private UserRoleService userRoleService;
    
    /**
     * This method...
     * @param proposalDevelopmentDocument
     * @param proposalOrganization
     */
    public void initializeUnitOrganzationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Organization proposalOrganization = proposalDevelopmentDocument.getOrganization();
        
        //Unit number chosen, set Organzation, etc...
        if (proposalDevelopmentDocument.getOwnedByUnitNumber() != null && proposalOrganization == null) {
            //get Lead Unit details
            proposalDevelopmentDocument.refreshReferenceObject("ownedByUnit");
            String organizationId = proposalDevelopmentDocument.getOwnedByUnit().getOrganizationId();
            
            //get Organzation assoc. w/ Lead Unit
            proposalDevelopmentDocument.setOrganizationId(organizationId);
            proposalDevelopmentDocument.refreshReferenceObject("organization");
            proposalOrganization = proposalDevelopmentDocument.getOrganization();
            proposalDevelopmentDocument.setPerformingOrganizationId(organizationId);
            proposalDevelopmentDocument.refreshReferenceObject("performingOrganization");
            
            //initialize Proposal Locations with Organization details
            if (proposalDevelopmentDocument.getProposalLocations().isEmpty()) {
                ProposalLocation newProposalLocation = new ProposalLocation();
                newProposalLocation.setLocation(proposalOrganization.getOrganizationName());
                newProposalLocation.setRolodexId(proposalOrganization.getContactAddressId());
                newProposalLocation.refreshReferenceObject("rolodex");
                newProposalLocation.setLocationSequenceNumber(proposalDevelopmentDocument.getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER));
                proposalDevelopmentDocument.getProposalLocations().add(0, newProposalLocation);
            }
            
        }
    }
    
    public List<Unit> getDefaultModifyProposalUnitsForUser(String userName) {
        Map queryMap = new HashMap();
        queryMap.put("userName", userName);
        
        
        List<Person> persons = (List<Person>)getBusinessObjectService().findMatching(Person.class, queryMap);
        
        if (persons.size() > 1) {
            throw new RuntimeException("More than one person retieved for userName: " + userName);
        }
        
        Person person = persons.get(0);

        List<Unit> units = new ArrayList<Unit>();

        List<UserRole> userRoles = getUserRoleService().getUserRoles(person.getPersonId(), RightConstants.MODIFY_PROPOSAL);
        for (UserRole userRole : userRoles) {
            Unit unit = userRole.getUnit();
            if (!units.contains(unit))
            units.add(unit);
        }
        
        return units;
    }
    
    /**
     * Gets units for the given names. Useful when you know what you want.
     *
     * @param unitNumbers varargs representation of unitNumber array
     * @return Collection<Unit>
     */
    private Collection<Unit> getUnitsWithNumbers(String ... unitNumbers) {
        Collection<Unit> retval = new ArrayList<Unit>();

        for (String unitNumber : unitNumbers) {
            Map query_map = new HashMap();
            query_map.put("unitNumber", unitNumber);
            retval.add((Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, query_map));
        }
        
        return retval;
    }
        
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Gets the userRoleService attribute. 
     * @return Returns the userRoleService.
     */
    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    /**
     * Sets the userRoleService attribute value.
     * @param userRoleService The userRoleService to set.
     */
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


}
