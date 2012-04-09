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
package org.kuali.kra.protocol.personnel;

import java.util.List;
import org.kuali.kra.infrastructure.Constants;

public abstract class ProtocolPersonnelServiceImpl implements ProtocolPersonnelService {

    /**
     * @see org.kuali.kra.protocol.personnel.ProtocolPersonnelService#getPrincipalInvestigator(java.util.List)
     */
    public ProtocolPerson getPrincipalInvestigator(List<ProtocolPerson> protocolPersons) {
        ProtocolPerson principalInvestigator = null;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(isPrincipalInvestigator(protocolPerson)) {
                principalInvestigator = protocolPerson;
                break;
            }
        }
        return principalInvestigator;
    }
    
    /**
     * This method is to check if the person has the role Principal Investigator
     * @param protocolPerson
     * @return true / false
     */
    public boolean isPrincipalInvestigator(ProtocolPerson protocolPerson) {
        boolean isInvestigator = false;
        if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(getPrincipalInvestigatorRole())) {
            isInvestigator = true;
        }
        return isInvestigator;
    }
    
    
    /**
     * This method is to get principal investigator role
     * @return String - PI role
     */
    protected String getPrincipalInvestigatorRole() {
        return Constants.PRINCIPAL_INVESTIGATOR_ROLE;
    }
    

}
