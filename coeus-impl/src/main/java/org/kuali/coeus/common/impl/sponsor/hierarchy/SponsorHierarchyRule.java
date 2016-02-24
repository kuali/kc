/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
