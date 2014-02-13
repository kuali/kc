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
package org.kuali.kra.bo;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.irb.correspondence.CorrespondentType;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

/**
 * This class...
 */
public class IacucUnitCorrespondentMaintainableImpl extends KraMaintainableImpl implements Maintainable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -158475500509336068L;
    private static final String KIM_PERSON_LOOKUPABLE_REFRESH_CALLER = "kimPersonLookupable";
    private static final String UNIT_ID_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.unitId";
    private static final String PRINCIPAL_ID_INVALID_ERROR_KEY = "error.invalid.unitAdministrator.principalId";
    private static final String CORRESPONDENT_TYPE_CODE_INVALID_ERROR_KEY = "error.invalid.unitCorrespondent.correspondentType";

    /**
     * @see org.kuali.rice.kns.maintenance.Maintainable#refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document) {
        super.refresh(refreshCaller, fieldValues, document);
        if (KIM_PERSON_LOOKUPABLE_REFRESH_CALLER.equals(refreshCaller)) {
            IacucUnitCorrespondent unitCorrespondent = (IacucUnitCorrespondent) this.getBusinessObject();
            String principalId = (String) fieldValues.get(KimConstants.PrimaryKeyConstants.PRINCIPAL_ID);
            unitCorrespondent.setPersonId(principalId);
        }
    }
   
    @Override
    public void prepareForSave() {
        IacucUnitCorrespondent unitCorrespondent = (IacucUnitCorrespondent)this.businessObject;
        
        if(!isUnitIdValid( unitCorrespondent.getUnitNumber() )){
            reportInvalidUnitId(unitCorrespondent);
        }
        
        if(!isCorrespondentTypeCodeValid( unitCorrespondent.getCorrespondentTypeCode() )){
            reportInvalidCorrespondentTypeCode(unitCorrespondent);
        }
        
        if( !isValidPrincipalId( unitCorrespondent.getPersonId() )) {
            reportInvalidPrincipalId( unitCorrespondent );
        }
        
        super.prepareForSave();
    }
    
    private void reportInvalidUnitId(IacucUnitCorrespondent unitCorrespondent) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.unitNumber", 
                        UNIT_ID_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    
    private void reportInvalidPrincipalId(IacucUnitCorrespondent unitCorrespondent) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.person.userName", 
                        PRINCIPAL_ID_INVALID_ERROR_KEY,
                        new String[]{});
      
    }

    
    private void reportInvalidCorrespondentTypeCode(IacucUnitCorrespondent unitCorrespondent) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.correspondentTypeCode", 
                        CORRESPONDENT_TYPE_CODE_INVALID_ERROR_KEY,
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
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> validParams = new HashMap<String, String>();
        validParams.put("unitNumber", unitNumber);
        return !businessObjectService.findMatching(Unit.class, validParams).isEmpty();
    }

    private boolean isCorrespondentTypeCodeValid(Integer correspondentTypeCode) {
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> validParams = new HashMap<String, String>();
        validParams.put("correspondentTypeCode", correspondentTypeCode.toString());
        return !businessObjectService.findMatching(CorrespondentType.class, validParams).isEmpty();
        
    }

    
}
