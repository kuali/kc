/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.service.ProposalRoleService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * Finds the available set of proposal roles.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalRoleValuesFinder extends KeyValuesBase {
    
    /**
     * The set of proposal roles is static.  
     * 
     * @return the list of key/value pairs of Proposal Roles.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        ProposalRoleService proposalRoleService = KraServiceLocator.getService(ProposalRoleService.class);
        List<Role> proposalRoles = proposalRoleService.getRolesForDisplay();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        for (Role role : proposalRoles) {
            KeyValue pair = new ConcreteKeyValue(role.getName(), role.getName());
            keyValues.add(pair);
        }
        
        return keyValues;
    }
}
