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
package org.kuali.kra.bo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class UnitAdministratorMaintainableImpl extends KraMaintainableImpl implements Maintainable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4267900712064482626L;
    private static final String KIM_PERSON_LOOKUPABLE_REFRESH_CALLER = "kimPersonLookupable";
    private static final String UNIT_ID_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.unitId";
    private static final String PRINCIPAL_ID_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.principalId";
    private static final String UNIT_ADMINISTRATOR_TYPE_CODE_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.unitAdministratorTypeCode";
    

    /**
     * @see org.kuali.rice.kns.maintenance.Maintainable#refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document) {
        super.refresh(refreshCaller, fieldValues, document);
        if (KIM_PERSON_LOOKUPABLE_REFRESH_CALLER.equals(refreshCaller)) {
            UnitAdministrator unitAdministrator = (UnitAdministrator) this.getBusinessObject();
            String principalId = (String) fieldValues.get(KimConstants.PrimaryKeyConstants.PRINCIPAL_ID);
            unitAdministrator.setPersonId(principalId);
        }
    }
    
    @Override
    public void prepareForSave() {
        UnitAdministrator unitAdministrator = (UnitAdministrator)this.businessObject;
        
        if(!isUnitIdValid( unitAdministrator.getUnitNumber() )){
            reportInvalidUnitId(unitAdministrator);
        }
        
        if(!isUnitAdministratorTypeCodeValid( unitAdministrator.getUnitAdministratorTypeCode() )){
            reportInvalidUnitAdministratorTypeCode(unitAdministrator);
        }
        
        if( !isValidPrincipalId( unitAdministrator.getPersonId() )) {
            reportInvalidPrincipalId( unitAdministrator );
        }
        
        super.prepareForSave();
    }
    
    private void reportInvalidUnitId(UnitAdministrator unitAdministrator) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.unitNumber", 
                        UNIT_ID_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    
    private void reportInvalidPrincipalId(UnitAdministrator unitAdministrator) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.person.userName", 
                        PRINCIPAL_ID_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    
    private void reportInvalidUnitAdministratorTypeCode(UnitAdministrator unitAdministrator) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.unitAdministratorTypeCode", 
                        UNIT_ADMINISTRATOR_TYPE_CODE_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    private boolean isValidPrincipalId(String principalId) {
        boolean valid = true;
        KcPersonService personService = KraServiceLocator.getService(KcPersonService.class);
        if ( StringUtils.isEmpty(principalId) ) {
            valid = false;
        } else {
            KcPerson person = personService.getKcPersonByPersonId(principalId);
            if( person == null )  valid=false;
        }
        
        return valid;
    }

    
    private boolean isUnitIdValid(String unitNumber) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> validUnitParams = new HashMap<String, String>();
        validUnitParams.put("unitNumber", unitNumber);
        Collection<Unit> units = businessObjectService.findMatching(Unit.class, validUnitParams);
        return !units.isEmpty();
    }

    private boolean isUnitAdministratorTypeCodeValid(String unitAdministratorTypeCode) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> validParams = new HashMap<String, String>();
        validParams.put("unitAdministratorTypeCode", unitAdministratorTypeCode);
        Collection<UnitAdministratorType> units = businessObjectService.findMatching(UnitAdministratorType.class, validParams);
        return !units.isEmpty();
        
    }

    
}
