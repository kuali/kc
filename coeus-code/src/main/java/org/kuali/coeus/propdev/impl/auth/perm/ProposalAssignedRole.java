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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.kuali.rice.krad.bo.BusinessObjectBase;

import java.util.List;

public class ProposalAssignedRole extends BusinessObjectBase {

    private String roleName;

    private List<String> userNames;

    public ProposalAssignedRole(String roleName, List<String> userNames) {
        this.roleName = roleName;
        this.userNames = userNames;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void refresh() {
    }
}
