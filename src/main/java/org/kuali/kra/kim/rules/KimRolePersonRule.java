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
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAclEntry;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

public class KimRolePersonRule extends KraMaintenanceDocumentRuleBase {
    
    private static final String DUPLICATE_ACL_ENTRY = "error.kim.duplicateaclentry";
    private static final String UNKNOWN_ROLE = "error.kim.unknown.role";
    private static final String UNKNOWN_PERSON = "error.kim.unknown.person";
    private static final String UNKNOWN_UNIT = "error.kim.unknown.unit";
    private static final String CANNOT_DESCEND = "error.kim.descend.roleperson";
    
    /**
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean isDocumentValidForSave(MaintenanceDocument maintenanceDocument) {
       
        boolean success = super.isDocumentValidForSave(maintenanceDocument);
        success &= processGlobalSaveDocumentBusinessRules(maintenanceDocument);
        
        UnitAclEntry entry = (UnitAclEntry) maintenanceDocument.getDocumentBusinessObject();
        
        if (entry.getVersionNumber() == null) {
            success &= processCreation(entry);
        } else {
            success &= processUpdate(entry);
        }
       
        return success;
    }
    
    private boolean processUpdate(UnitAclEntry aclEntry) {
        return processCreation(aclEntry);
    }

    private boolean processCreation(UnitAclEntry aclEntry) {
        boolean success = true;
        if (isDuplicate(aclEntry)) {
            success = false;
            this.putFieldError("", DUPLICATE_ACL_ENTRY);
        }
        
        if (!StringUtils.isBlank(aclEntry.getPersonId())) {
            Person person = getPerson(aclEntry.getPersonId());
            if (person == null) {
                success = false;
                this.putFieldError("personId", UNKNOWN_PERSON);
            }
        }
        
        if (!StringUtils.isBlank(aclEntry.getUnitNumber())) {
            Unit unit = getUnit(aclEntry.getUnitNumber());
            if (unit == null) {
                success = false;
                this.putFieldError("unitNumber", UNKNOWN_UNIT);
            }
        }
        
        KimRole role = null;
        if (aclEntry.getRoleId() != null) {
            role = getRole(aclEntry.getRoleId());
            if (role == null) {
                success = false;
                this.putFieldError("roleId", UNKNOWN_ROLE);
            }
        }
        
        if (isInvalidDescend(aclEntry, role)) {
            success = false;
            this.putFieldError("subunits", CANNOT_DESCEND);
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
    
    private Person getPerson(String personId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("personId", personId);
        return (Person) getBusinessObjectService().findByPrimaryKey(Person.class, primaryKey);
    }
    
    private Unit getUnit(String unitNumber) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("unitNumber", unitNumber);
        return (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, primaryKey);
    }
    
    private boolean isDuplicate(UnitAclEntry aclEntry) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", aclEntry.getPersonId());
        fieldValues.put("roleId", aclEntry.getRoleId());
        fieldValues.put("unitNumber", aclEntry.getUnitNumber());
        fieldValues.put("active", aclEntry.getActive());
        fieldValues.put("subunits", aclEntry.getSubunits());
        return (getBusinessObjectService().countMatching(UnitAclEntry.class, fieldValues) != 0);
    }
    
    private boolean isInvalidDescend(UnitAclEntry aclEntry, KimRole role) {
        boolean isInvalid = false;
        if (role != null) {
            if (!role.getDescend() && aclEntry.getSubunits()) {
                isInvalid = true;
            }
        }
        return isInvalid;
    }
}
