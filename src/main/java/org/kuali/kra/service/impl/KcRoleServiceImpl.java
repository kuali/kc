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
package org.kuali.kra.service.impl;

    /**
     * Copyright 2005-2012 The Kuali Foundation
     *
     * Licensed under the Educational Community License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     *
     * http://www.opensource.org/licenses/ecl2.php
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */
   
import org.kuali.rice.kim.impl.role.RoleServiceImpl;
import java.util.Map;


public class KcRoleServiceImpl extends RoleServiceImpl {

    @Override
    protected Boolean getPrincipalHasRoleFromCache(String principalId, String roleId, Map<String, String> qualification, boolean checkDelegations) {            
        return null;
    }


}
