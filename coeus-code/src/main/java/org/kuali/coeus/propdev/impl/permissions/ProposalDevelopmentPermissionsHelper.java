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
package org.kuali.coeus.propdev.impl.permissions;

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

/**
 * Defines the Permissions Helper for Development Proposal.
 */
public class ProposalDevelopmentPermissionsHelper {

    private static final long serialVersionUID = 8832539481443727887L;

    private ProposalDevelopmentDocument proposalDevelopmentDocument;

    private transient List<ProposalUserRoles>userRoles;
    
    /**
     * Constructs a ProposalDevelopmentPermissionselper.
     * @param form the container form
     */
    public ProposalDevelopmentPermissionsHelper(ProposalDevelopmentForm form) {
        proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

    public void populatePropPermissions() {
   }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }
    
	public List<ProposalUserRoles> getUserRoles() {
		if (userRoles == null || userRoles.isEmpty()) {
			userRoles = proposalDevelopmentDocument.getDevelopmentProposal().getUserRoles();
		}
		if (userRoles == null || userRoles.isEmpty()) {
			userRoles = new ArrayList<ProposalUserRoles>();
			ProposalUserRoles role = new ProposalUserRoles();
			role.setFullname("Abraham Lincoln");
			role.setUnitNumber("000001");
			role.setUnitName("University");
			role.addRoleName("Commander In Chief");
			userRoles.add(role);
			role.setFullname("");
			role.setUnitNumber("IN-IN");
			role.setUnitName("AOP");
			role.addRoleName("Commander, US Army");
			userRoles.add(role);
			role.setFullname("Philip Sheridan");
			role.setUnitNumber("IN-IN");
			role.setUnitName("AOT");
			role.addRoleName("Commander, Army of the Tennessee");
			userRoles.add(role);
		}
		return userRoles;
	}



}