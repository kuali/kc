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
