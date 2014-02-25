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
package org.kuali.coeus.common.impl.rolodex;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("rolodexService")
public class RolodexServiceImpl implements RolodexService {

	@Autowired
	@Qualifier("businessObjectService")
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
     * @see org.kuali.coeus.common.framework.rolodex.RolodexService#getRolodex(int)
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
