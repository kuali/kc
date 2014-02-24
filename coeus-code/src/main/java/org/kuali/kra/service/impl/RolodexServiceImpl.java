/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RolodexServiceImpl implements RolodexService {

    private BusinessObjectService businessObjectService;

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.service.RolodexService#getRolodex(int)
     */
    public Rolodex getRolodex(int rolodexId) {
        Rolodex rolodex = null;

        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("rolodexId", rolodexId);
        Collection<Rolodex> rolodexes = businessObjectService.findMatching(Rolodex.class, fieldValues);
        if (rolodexes.size() == 1) {
            rolodex = rolodexes.iterator().next();
        }

        return rolodex;
    }
}
