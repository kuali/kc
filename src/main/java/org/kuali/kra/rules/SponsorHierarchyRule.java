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
package org.kuali.kra.rules;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

public class SponsorHierarchyRule {
    
    public SponsorHierarchyRule() {
    }
    
    
    public boolean newHierarchyNameRequired(SponsorHierarchyForm sponsorHierarchyForm) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (StringUtils.isBlank(sponsorHierarchyForm.getNewHierarchyName())) {
            errorMap.putError("newHierarchyName", RiceKeyConstants.ERROR_REQUIRED, "New Hierarchy Name");
            valid = false;
        } else {
            Map fieldValues = new HashMap();
            fieldValues.put("hierarchyName", sponsorHierarchyForm.getNewHierarchyName());
            if (KraServiceLocator.getService(BusinessObjectService.class).countMatching(SponsorHierarchy.class, fieldValues) > 0) {
                errorMap.putError("newHierarchyName", KeyConstants.ERROR_SPONSOR_HIERARCHY_EXISTS, sponsorHierarchyForm.getNewHierarchyName());
                valid = false;
                
            }
        }
        return valid;
    }

}
