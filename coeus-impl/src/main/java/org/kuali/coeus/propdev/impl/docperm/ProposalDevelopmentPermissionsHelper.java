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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.role.Role;

/**
 * Defines the Permissions Helper for Development Proposal.
 */
public class ProposalDevelopmentPermissionsHelper {

    private ProposalDevelopmentDocument proposalDevelopmentDocument;
    private ProposalRoleService proposalRoleService;
    private KcPersonService kcPersonService;
    private KcAuthorizationService kcAuthorizationService;

    private Map<String, ProposalUserRoles>pendingRoleMap = new TreeMap<String, ProposalUserRoles>();
    private List<ProposalUserRoles> workingUserRoles;


    private static final Log LOG = LogFactory.getLog(Budget.class);

    /**
     * Constructs a ProposalDevelopmentPermissionselper.
     * @param document - the Proposal Development document
     */
    public ProposalDevelopmentPermissionsHelper(ProposalDevelopmentDocument document) {
        proposalDevelopmentDocument = document;
        // populate initial roles with default roles for document
        workingUserRoles = getUserRoles();
    }

    private KcAuthorizationService getKcAuthorizationService() {
    	if (kcAuthorizationService == null) {
    		kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
    	}
    	return kcAuthorizationService;
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }
    
    /**
     * Get the list of Proposal User Roles.  Each user has one or more
     * roles assigned to the proposal.  This method builds the list each
     * time it is invoked.
     * 
     * @return the list of users with proposal roles and sorted by their full name
     */
	public List<ProposalUserRoles> getUserRoles() {
		// Add persons into the ProposalUserRolesList for each of the roles.
		Collection<Role> roles = getKimProposalRoles();
		for (Role role : roles) {
	        List<String> personIds = getKcAuthorizationService().getPrincipalsInRole(getProposalDevelopmentDocument(), role.getName());
	        for (String personId : personIds) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(personId);
	        	if (person != null) {
	        		ProposalUserRoles proposalUserRole = getPendingRoleMap().get(person.getUserName());
	        		if (proposalUserRole != null) {
	        			proposalUserRole.addRoleName(role.getName());
	        		} else {
                        ProposalUserRoles newRole = new ProposalUserRoles();
                        newRole.setUsername(person.getUserName());
                        newRole.setFullname(person.getFullName());
                        newRole.addRoleName(role.getName());
                        getPendingRoleMap().put(person.getUserName(), newRole);
		        	}
		        } else {
                    LOG.error("Attempting to get roles for null user role!");
                }
		    }
		}
		return new ArrayList<ProposalUserRoles>(getPendingRoleMap().values());
	}

    /**
     * Get all of the proposal roles.
     * @return
     */
    public Collection<Role> getKimProposalRoles() {
        return getProposalRoleService().getRolesForDisplay();
    }

    public ProposalRoleService getProposalRoleService() {
        if (proposalRoleService == null) {
        	proposalRoleService = KcServiceLocator.getService(ProposalRoleService.class);
        }
		return proposalRoleService;
	}

	public void setProposalRoleService(ProposalRoleService proposalRoleService) {
		this.proposalRoleService = proposalRoleService;
	}

	public KcPersonService getKcPersonService() {
		if (kcPersonService == null) {
			kcPersonService = KcServiceLocator.getService(KcPersonService.class);
		}
		return kcPersonService;
	}

	public void setKcPersonService(KcPersonService kcPersonService) {
		this.kcPersonService = kcPersonService;
	}

	public Map<String, ProposalUserRoles> getPendingRoleMap() {
		return pendingRoleMap;
	}

	public void setPendingRoleMap(Map<String, ProposalUserRoles> pendingRoleMap) {
		this.pendingRoleMap = pendingRoleMap;
	}

	public List<ProposalUserRoles> getWorkingUserRoles() {
		return workingUserRoles;
	}

	public void setWorkingUserRoles(List<ProposalUserRoles> workingUserRoles) {
		this.workingUserRoles = workingUserRoles;
	}
}
