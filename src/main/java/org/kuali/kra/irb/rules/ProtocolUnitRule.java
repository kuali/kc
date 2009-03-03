/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.rule.AddProtocolUnitRule;
import org.kuali.kra.irb.rule.event.AddProtocolUnitEvent;
import org.kuali.kra.service.UnitService;

/**
 * This class contains rules to validate protocol units for each protocol personnel.
 */
public class ProtocolUnitRule extends ProtocolDocumentRule implements AddProtocolUnitRule {

    private static final String ERROR_PROPERTY_NEW_PERSON_UNIT = "newProtocolPersonUnits"; 
    private static final String ERROR_PROPERTY_UNIT_NUMBER = ".unitNumber"; 
    private String ERROR_PROPERTY_PERSON_INDEX = "[personIndex]";
    private String PERSON_INDEX = "personIndex";
    
    /**
     * @see org.kuali.kra.irb.rules.ProtocolDocumentRule#processAddProtocolUnitBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolUnitEvent)
     */
    public boolean processAddProtocolUnitBusinessRules(AddProtocolUnitEvent addProtocolUnitEvent) {
        boolean isValid = true;

        ProtocolUnit protocolUnit = addProtocolUnitEvent.getProtocolUnit();
        int personIndex = addProtocolUnitEvent.getPersonIndex();
        isValid &= isEmptyProtocolUnit(protocolUnit, personIndex);
        return isValid;
    }
    

    /**
     * This method is to check whether protocol unit number is empty
     * @param protocolUnit
     * @return
     */
    private boolean isEmptyProtocolUnit(ProtocolUnit protocolUnit, int personIndex) {
        boolean validUnit = true;
        if(StringUtils.isBlank(protocolUnit.getUnitNumber())) {
            reportError(formatErrorPropertyName(personIndex, ERROR_PROPERTY_UNIT_NUMBER), KeyConstants.ERROR_PROTOCOL_UNIT_INVALID);
            validUnit = false;
        }else {
            validUnit = isValidProtocolUnit(protocolUnit, personIndex);
        }
        return validUnit;
    }
    
    /**
     * This method is to check whether unit number is valid
     * @param protocolUnit
     * @return
     */
    private boolean isValidProtocolUnit(ProtocolUnit protocolUnit, int personIndex) {
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
        return KraServiceLocator.getService(UnitService.class);
    }
    
}
