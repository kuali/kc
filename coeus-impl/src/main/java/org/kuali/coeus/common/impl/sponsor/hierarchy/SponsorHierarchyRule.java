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
package org.kuali.coeus.common.impl.sponsor.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.Map;

public class SponsorHierarchyRule {
    
    public boolean newHierarchyNameRequired(SponsorHierarchyForm sponsorHierarchyForm) {
        boolean valid = true;
        
        MessageMap errorMap = GlobalVariables.getMessageMap();
        if (StringUtils.isBlank(sponsorHierarchyForm.getNewHierarchyName())) {
            errorMap.putError("newHierarchyName", RiceKeyConstants.ERROR_REQUIRED, "New Hierarchy Name");
            valid = false;
        } else {
            Map fieldValues = new HashMap();
            fieldValues.put("hierarchyName", sponsorHierarchyForm.getNewHierarchyName());
            if (KcServiceLocator.getService(BusinessObjectService.class).countMatching(SponsorHierarchy.class, fieldValues) > 0) {
                errorMap.putError("newHierarchyName", KeyConstants.ERROR_SPONSOR_HIERARCHY_EXISTS, sponsorHierarchyForm.getNewHierarchyName());
                valid = false;
                
            }
        }
        return valid;
    }

}
