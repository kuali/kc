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
package org.kuali.kra.protocol.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * This class contains rules to validate protocol units for each protocol personnel.
 */
public abstract class ProtocolUnitRuleBase extends KcTransactionalDocumentRuleBase implements AddProtocolUnitRule {

    private static final String ERROR_PROPERTY_NEW_PERSON_UNIT = "personnelHelper.newProtocolPersonUnits"; 
    private static final String ERROR_PROPERTY_UNIT_NUMBER = ".unitNumber"; 
    private String ERROR_PROPERTY_PERSON_INDEX = "[personIndex]";
    private String PERSON_INDEX = "personIndex";
    
    @Override
    public boolean processAddProtocolUnitBusinessRules(AddProtocolUnitEvent addProtocolUnitEvent) {
        boolean isValid = true;

        ProtocolUnitBase protocolUnit = addProtocolUnitEvent.getProtocolUnit();
        int personIndex = addProtocolUnitEvent.getPersonIndex();
        ProtocolPersonBase protocolPerson = ((ProtocolDocumentBase) addProtocolUnitEvent.getDocument()).getProtocol().getProtocolPerson(personIndex);
        isValid &= !isEmptyProtocolUnit(protocolUnit, personIndex);
        if(isValid) {
            isValid &= isValidProtocolUnit(protocolUnit, personIndex);
            isValid &= !isDuplicateProtocolUnit(protocolPerson, protocolUnit, personIndex);
        }
        return isValid;
    }
    

    /**
     * This method is to check whether protocol unit number is empty
     * @param protocolUnit
     * @return
     */
    private boolean isEmptyProtocolUnit(ProtocolUnitBase protocolUnit, int personIndex) {
        boolean isEmpty = false;
        if(StringUtils.isBlank(protocolUnit.getUnitNumber())) {
            reportError(formatErrorPropertyName(personIndex, ERROR_PROPERTY_UNIT_NUMBER), KeyConstants.ERROR_PROTOCOL_UNIT_INVALID);
            isEmpty = true;
        }
        return isEmpty;
    }
    
    /**
     * This method is to check whether new unit added already exists in the list
     * @param protocolPerson
     * @param protocolUnit
     * @param personIndex
     * @return
     */
    private boolean isDuplicateProtocolUnit(ProtocolPersonBase protocolPerson, ProtocolUnitBase protocolUnit, int personIndex) {
        boolean duplicateUnit = false;
        duplicateUnit = getProtocolPersonnelService().isDuplicateUnit(protocolPerson, protocolUnit);
        if(duplicateUnit) {
            reportError(formatErrorPropertyName(personIndex, ERROR_PROPERTY_UNIT_NUMBER), KeyConstants.ERROR_PROTOCOL_UNIT_DUPLICATE);
        }
        return duplicateUnit;
    }
    
    /**
     * This method is to check whether unit number is valid
     * @param protocolUnit
     * @return
     */
    private boolean isValidProtocolUnit(ProtocolUnitBase protocolUnit, int personIndex) {
        boolean validUnit = true;
        if(StringUtils.isBlank(getUnitService().getUnitName(protocolUnit.getUnitNumber()))) {
            reportError(formatErrorPropertyName(personIndex, ERROR_PROPERTY_UNIT_NUMBER), KeyConstants.ERROR_PROTOCOL_UNIT_INVALID);
            validUnit = false;
        }
        return validUnit;
    }

    /**
     * This method is to format error property for new protocol unit.
     * Person holding new unit is identified by personIndex
     * This is to display message in appropriate tab
     * @param personIndex
     * @param errorKey
     * @return String - formatted error property
     */
    private String formatErrorPropertyName(int personIndex, String errorKey) {
        String errorProperty = null;
        errorProperty = new StringBuilder(ERROR_PROPERTY_NEW_PERSON_UNIT)
        .append(ERROR_PROPERTY_PERSON_INDEX.replaceAll(PERSON_INDEX, Integer.toString(personIndex)))
        .append(errorKey)
        .toString();
        return errorProperty;
    }
    
    /**
     * This method is to get unit service
     * @return UnitService
     */
    private UnitService getUnitService() {
        return KcServiceLocator.getService(UnitService.class);
    }
    
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    protected abstract ProtocolPersonnelService getProtocolPersonnelService();

}
