/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.auth.perm;

import java.util.List;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;

public interface ProposalDevelopmentPermissionsService {

    public void savePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> persistedUsers, List<ProposalUserRoles> newUsers);
    
    public void saveProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc);
    
    public void deleteProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc);
}
