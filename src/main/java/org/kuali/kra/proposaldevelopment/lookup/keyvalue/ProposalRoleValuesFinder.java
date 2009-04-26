/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Collection<KimRole> roles = keyValuesService.findAll(KimRole.class);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        /*
         * Add in all of the standard proposal roles so they show up first in the drop-down list.
         */
        for (KimRole role : roles) {
            if (StringUtils.equals(role.getRoleTypeCode(), RoleConstants.PROPOSAL_ROLE_TYPE)) {
                KeyLabelPair pair = new KeyLabelPair(role.getName(), role.getName());
                if (role.isUnassigned()) {
                    keyValues.add(0, pair);
                } else if (role.isStandardProposalRole()){
                    keyValues.add(pair);
                }
            }
        }
        
        /*
         * Now add in all of the other user-defined proposal roles.
         */
        for (KimRole role : roles) {
            if (StringUtils.equals(role.getRoleTypeCode(), RoleConstants.PROPOSAL_ROLE_TYPE)) {
                KeyLabelPair pair = new KeyLabelPair(role.getName(), role.getName());
                if (!role.isUnassigned() && !role.isStandardProposalRole()) {
                    keyValues.add(pair);
                }
            }
        }
        return keyValues;
    }
}
