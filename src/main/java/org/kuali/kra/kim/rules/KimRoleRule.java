/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.kim.rules;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;

public class KimRoleRule extends KraMaintenanceDocumentRuleBase {
    
    private static final String DUPLICATE_ROLE_NAME = "error.kim.duplicaterole";
    private static final String CANNOT_DESCEND = "error.kim.descend";
    
    /**
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) { 
        boolean success = false;
        KimRole role = (KimRole) document.getDocumentBusinessObject();
        
        if (role.getId() == null) {
            success = processCreation(role);
        } else {
            success = processUpdate(role);
        }
        
        return success;
    }
    
    private boolean processUpdate(KimRole role) {
        KimRole origRole = getRole(role.getId());
        return true;
    }

    private boolean processCreation(KimRole role) {
        boolean success = true;
        if (isDuplicateRoleName(role)) {
            success = false;
            this.putFieldError("name", DUPLICATE_ROLE_NAME);
        }
        if (isInvalidDescend(role)) {
            success = false;
            this.putFieldError("descend", CANNOT_DESCEND);
        }
        return success;
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    private KimRole getRole(Long roleId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("id", roleId);
        return (KimRole) getBusinessObjectService().findByPrimaryKey(KimRole.class, primaryKey);
    }

    private boolean isDuplicateRoleName(KimRole role) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("name", role.getName());
        return (getBusinessObjectService().countMatching(KimRole.class, fieldValues) != 0);
    }
    
    private boolean isInvalidDescend(KimRole role) {
        boolean isInvalid = false;
        if (StringUtils.equals(role.getRoleTypeCode(), "P") && role.getDescend()) {
            isInvalid = true;
        }
        return isInvalid;
    }
}
