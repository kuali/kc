/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.type.KimType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


@Component("proposalRoleService")
public class ProposalRoleServiceImpl implements ProposalRoleService {

    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public void setKcPersonService(KcPersonService service) {
        this.kcPersonService = service;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public SystemAuthorizationService getSystemAuthorizationService() {
        return systemAuthorizationService;
    }

    public List<Role> getRoles() {
        return systemAuthorizationService.getRoles(RoleConstants.PROPOSAL_ROLE_TYPE);
    }

    protected boolean isStandardProposalRole(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.AGGREGATOR)
                || StringUtils.equals(roleName, RoleConstants.NARRATIVE_WRITER)
                || StringUtils.equals(roleName, RoleConstants.BUDGET_CREATOR) || StringUtils.equals(roleName, RoleConstants.VIEWER);
    }

    protected boolean isRoleUnassigned(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.UNASSIGNED);
    }

    public List<Role> getRolesForDisplay() {
        List<Role> proposalRoles = getRoles();
        List<Role> finalRoleList = new ArrayList<Role>();

        for (Role role : proposalRoles) {
            if (isRoleUnassigned(role.getName())) {
                finalRoleList.add(role);
            } else if (isStandardProposalRole(role.getName())){
                finalRoleList.add(role);
            }
        }
        
        /*
         * Now add in all of the other user-defined proposal roles.
         */
        for (Role role : proposalRoles) {
            if (!isRoleUnassigned(role.getName()) && !isStandardProposalRole(role.getName())) {
                finalRoleList.add(role);
            }
        }

        filterDerivedRoles(finalRoleList);

        return finalRoleList;
    }

    protected void filterDerivedRoles(List<Role> roles) {
        Iterator<Role> iter = roles.iterator();
        while (iter.hasNext()) {
            Role role = iter.next();
            KimType type = systemAuthorizationService.getKimTypeInfoForRole(role);
            // filter out derived roles and roles that are not based on Unit or workflow
            if (StringUtils.startsWith(type.getName(), "Derived Role") || StringUtils.startsWith(type.getName(), "Default")) {
                iter.remove();
            }
        }
    }

    public List<ProposalUserRoles> getUserRoles(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        List<ProposalUserRoles> proposalUserRolesList = new ArrayList<ProposalUserRoles>();
        ProposalRoleService proposalRoleService = KcServiceLocator.getService(ProposalRoleService.class);

        // Add persons into the ProposalUserRolesList for each of the roles.
        Collection<Role> roles = proposalRoleService.getRolesForDisplay();
        for (Role role : roles) {
            addPersons(proposalUserRolesList, role.getName(), proposalDevelopmentDocument);
        }

        sortProposalUsers(proposalUserRolesList);
        return proposalUserRolesList;
    }

    /**
     * Add a set of persons to the proposalUserRolesList for a given role.
     *
     * @param propUserRolesList the list to add to
     * @param roleName the name of role to query for persons assigned to that role
     */
    private void addPersons(List<ProposalUserRoles> propUserRolesList, String roleName, ProposalDevelopmentDocument document) {
        KcAuthorizationService proposalAuthService = KcServiceLocator.getService(KcAuthorizationService.class);

        List<String> persons = proposalAuthService.getPrincipalsInRole(document, roleName);
        for (String person : persons) {
            ProposalUserRoles proposalUserRoles = findProposalUserRoles(propUserRolesList, person);
            if (proposalUserRoles != null) {
                proposalUserRoles.addRoleName(roleName);
            } else {
                propUserRolesList.add(buildProposalUserRoles(person, roleName));
            }
        }
    }

    /**
     * Find a user in the list of proposalUserRolesList based upon the user's username.
     *
     * @param propUserRolesList the list to search
     * @param username the user's username to search for
     * @return the proposalUserRoles or null if not found
     */
    private ProposalUserRoles findProposalUserRoles(List<ProposalUserRoles> propUserRolesList, String username) {
        for (ProposalUserRoles proposalUserRoles : propUserRolesList) {
            if (StringUtils.equals(username, proposalUserRoles.getUsername())) {
                return proposalUserRoles;
            }
        }
        return null;
    }

    /**
     * Build a ProposalUserRoles instance.  Assemble the information about
     * the user (person) into a ProposalUserRoles along with the home unit
     * info for that person.
     *
     * @param principalId the person
     * @param roleName the name of the role
     * @return a new ProposalUserRoles instance
     */
    private ProposalUserRoles buildProposalUserRoles(String principalId, String roleName) {
        ProposalUserRoles proposalUserRoles = new ProposalUserRoles();
        KcPerson person = getKcPersonService().getKcPersonByPersonId(principalId);

        // Set the person's username, rolename, fullname, and home unit.

        proposalUserRoles.setUsername(person.getUserName());
        proposalUserRoles.addRoleName(roleName);
        proposalUserRoles.setFullname(person.getFullName());
        proposalUserRoles.setUnitNumber(person.getOrganizationIdentifier());

        // Query the database to find the name of the unit.

        UnitService unitService = KcServiceLocator.getService(UnitService.class);
        Unit unit = unitService.getUnit(person.getOrganizationIdentifier());
        if (unit != null) {
            proposalUserRoles.setUnitName(unit.getUnitName());
        }

        return proposalUserRoles;
    }

    private void sortProposalUsers(List<ProposalUserRoles> proposalUserRolesList) {
        // Sort the list of users by their full name.

        Collections.sort(proposalUserRolesList, new Comparator<ProposalUserRoles>() {
            public int compare(ProposalUserRoles o1, ProposalUserRoles o2) {
                return o1.getFullname().compareTo(o2.getFullname());
            }
        });
    }

}
