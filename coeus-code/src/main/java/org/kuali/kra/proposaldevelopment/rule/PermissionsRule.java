/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.coeus.propdev.impl.auth.perm.ProposalUser;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalUserRoles;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.impl.auth.perm.ProposalUserEditRoles;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * Defines the Business Rule for processing Proposal Permissions.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PermissionsRule extends BusinessRule {
    
    /**
     * Determines the legality of adding a proposal user to the
     * given proposal development document.
     * 
     * @param document the proposal development document.
     * @param proposalUserRolesList list of proposal user roles
     * @param proposalUser the proposal user to be added to the document.
     * @return true if the addition is valid; otherwise false.
     */
    public boolean processAddProposalUserBusinessRules(ProposalDevelopmentDocument document,
                                                       List<ProposalUserRoles> proposalUserRolesList,
                                                       ProposalUser proposalUser);
    /**
     * Determines the legality of deleting a proposal user from the
     * given proposal development document.
     * 
     * @param document the proposal development document.
     * @param proposalUserRolesList list of proposal user roles
     * @param index the index into proposalUserRolesList of the user to delete
     * @return true if the addition is valid; otherwise false.
     */
    public boolean processDeleteProposalUserBusinessRules(ProposalDevelopmentDocument document,
                                                          List<ProposalUserRoles> proposalUserRolesList,
                                                          int index);
    
    /**
     * Determines if it is legal to edit the roles for a user.
     * 
     * @param document the proposal development document.
     * @param proposalUserRolesList list of proposal user roles
     * @param editRoles the proposal roles to edit for a user
     * @return true if the addition is valid; otherwise false.
     */
    public boolean processEditProposalUserRolesBusinessRules(ProposalDevelopmentDocument document,
                                                             List<ProposalUserRoles> proposalUserRolesList,
                                                             ProposalUserEditRoles editRoles);
}
