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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.kim.api.role.Role;

import java.util.List;

public interface ProposalRoleService {

    public List<Role> getRoles();
    
    public List<Role> getRolesForDisplay();

    public List<ProposalUserRoles> getUserRoles(ProposalDevelopmentDocument document);

}
