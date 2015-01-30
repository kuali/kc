/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.auth.perm;

import java.util.List;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;

public interface ProposalDevelopmentPermissionsService {

    public List<ProposalUserRoles> getPermissions(ProposalDevelopmentDocument document);

    public void savePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> persistedUsers, List<ProposalUserRoles> newUsers);
    
    public void saveProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc);
    
    public void deleteProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc);

    public boolean validateAddPermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser);

    public boolean validateDeletePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, int index);

    public boolean validateUpdatePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser);

    public void processAddPermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser);

    public void processDeletePermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser);

    public void processUpdatePermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser);
}
