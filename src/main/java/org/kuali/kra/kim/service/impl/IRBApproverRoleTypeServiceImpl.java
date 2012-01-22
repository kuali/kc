/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kns.kim.role.RoleTypeServiceBase;

public class IRBApproverRoleTypeServiceImpl extends RoleTypeServiceBase {

    @Override
    public boolean performMatch(Map<String,String> qualification, Map<String,String> roleQualifier) {
        if(roleQualifiedByProtocolKey(roleQualifier)) {  
            return qualification.containsKey(KcKimAttributes.PROTOCOL) && StringUtils.equals(qualification.get(KcKimAttributes.PROTOCOL), 
                    roleQualifier.get(KcKimAttributes.PROTOCOL));
        } 
        
        return false; 
    }

    protected boolean roleQualifiedByProtocolKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.PROTOCOL);
    }

}
