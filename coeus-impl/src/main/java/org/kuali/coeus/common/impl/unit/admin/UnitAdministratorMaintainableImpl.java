/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.unit.admin;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.Map;

public class UnitAdministratorMaintainableImpl extends KraMaintainableImpl implements Maintainable {


    private static final long serialVersionUID = -4267900712064482626L;
    private static final String KIM_PERSON_LOOKUPABLE_REFRESH_CALLER = "kimPersonLookupable";
    private static final String UNIT_ID_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.unitId";
    private static final String PRINCIPAL_ID_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.principalId";
    private static final String UNIT_ADMINISTRATOR_TYPE_CODE_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.unitAdministratorTypeCode";

    private transient DataObjectService dataObjectService;

    public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

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
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        errorReporter.reportError("document.newMaintainableObject.unitNumber", 
                        UNIT_ID_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    
    private void reportInvalidPrincipalId(UnitAdministrator unitAdministrator) {
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        errorReporter.reportError("document.newMaintainableObject.person.userName", 
                        PRINCIPAL_ID_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    
    private void reportInvalidUnitAdministratorTypeCode(UnitAdministrator unitAdministrator) {
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        errorReporter.reportError("document.newMaintainableObject.unitAdministratorTypeCode", 
                        UNIT_ADMINISTRATOR_TYPE_CODE_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    private boolean isValidPrincipalId(String principalId) {
        boolean valid = true;
        KcPersonService personService = KcServiceLocator.getService(KcPersonService.class);
        if ( StringUtils.isEmpty(principalId) ) {
            valid = false;
        } else {
            KcPerson person = personService.getKcPersonByPersonId(principalId);
            if( person == null )  valid=false;
        }
        
        return valid;
    }

    
    private boolean isUnitIdValid(String unitNumber) {
        Unit result = getDataObjectService().find(Unit.class, unitNumber);
        return result != null;
    }

    private boolean isUnitAdministratorTypeCodeValid(String unitAdministratorTypeCode) {
        UnitAdministratorType adminType = getDataObjectService().find(UnitAdministratorType.class, unitAdministratorTypeCode);
        return adminType != null;
    }

}
