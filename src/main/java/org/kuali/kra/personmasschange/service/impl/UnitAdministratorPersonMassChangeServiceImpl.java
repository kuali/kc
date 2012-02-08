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
package org.kuali.kra.personmasschange.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.UnitAdministratorPersonMassChangeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Units.
 */
public class UnitAdministratorPersonMassChangeServiceImpl implements UnitAdministratorPersonMassChangeService {

    private BusinessObjectService businessObjectService;

    @Override
    public List<UnitAdministrator> getUnitAdministratorChangeCandidates(PersonMassChange personMassChange) {
        Set<UnitAdministrator> unitAdministratorChangeCandidates = new HashSet<UnitAdministrator>();
        
        List<UnitAdministrator> unitAdministrators = new ArrayList<UnitAdministrator>();
        if (personMassChange.getUnitAdministratorPersonMassChange().isAdministrativeOfficer() 
                || personMassChange.getUnitAdministratorPersonMassChange().isOspAdministrator() 
                || personMassChange.getUnitAdministratorPersonMassChange().isUnitHead() 
                || personMassChange.getUnitAdministratorPersonMassChange().isDeanVP()
                || personMassChange.getUnitAdministratorPersonMassChange().isOtherIndividualToNotify() 
                || personMassChange.getUnitAdministratorPersonMassChange().isAdministrator()) {
            unitAdministrators.addAll(getBusinessObjectService().findAll(UnitAdministrator.class));
        }

        for (UnitAdministrator unitAdministrator : unitAdministrators) {
            if (isUnitAdministratorChangeCandidate(personMassChange, unitAdministrator)) {
                unitAdministratorChangeCandidates.add(unitAdministrator);
            }
        }
        
        return new ArrayList<UnitAdministrator>(unitAdministratorChangeCandidates);
    }
    
    private boolean isUnitAdministratorChangeCandidate(PersonMassChange personMassChange, UnitAdministrator unitAdministrator) {
        boolean isUnitAdministratorChangeCandidate = false;
        
        String[] administrativeOfficerTypes = { UnitAdministratorType.ADMINISTRATIVE_OFFICER_TYPE_CODE };
        String[] ospAdministratorTypes = { UnitAdministratorType.OSP_ADMINISTRATOR_TYPE_CODE };
        String[] unitHeadTypes = { UnitAdministratorType.UNIT_HEAD_TYPE_CODE };
        String[] deanVPTypes = { UnitAdministratorType.DEAN_VP_TYPE_CODE };
        String[] otherIndividualToNotifyTypes = { UnitAdministratorType.OTHER_INDIVIDUAL_TO_NOTIFY_TYPE_CODE };
        String[] administratorTypes = { UnitAdministratorType.ADMINISTRATIVE_CONTACT_TYPE_CODE, UnitAdministratorType.FINANCIAL_CONTACT_TYPE_CODE };
        
        if (personMassChange.getUnitAdministratorPersonMassChange().isAdministrativeOfficer()) {
            isUnitAdministratorChangeCandidate |= isUnitAdministratorTypeChangeCandidate(personMassChange, unitAdministrator, administrativeOfficerTypes);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isOspAdministrator()) {
            isUnitAdministratorChangeCandidate |= isUnitAdministratorTypeChangeCandidate(personMassChange, unitAdministrator, ospAdministratorTypes);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isUnitHead()) {
            isUnitAdministratorChangeCandidate |= isUnitAdministratorTypeChangeCandidate(personMassChange, unitAdministrator, unitHeadTypes);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isDeanVP()) {
            isUnitAdministratorChangeCandidate |= isUnitAdministratorTypeChangeCandidate(personMassChange, unitAdministrator, deanVPTypes);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isOtherIndividualToNotify()) {
            isUnitAdministratorChangeCandidate |= isUnitAdministratorTypeChangeCandidate(personMassChange, unitAdministrator, otherIndividualToNotifyTypes);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isAdministrator()) {
            isUnitAdministratorChangeCandidate |= isUnitAdministratorTypeChangeCandidate(personMassChange, unitAdministrator, administratorTypes);
        }
        
        return isUnitAdministratorChangeCandidate;
    }
    
    private boolean isUnitAdministratorTypeChangeCandidate(PersonMassChange personMassChange, UnitAdministrator unitAdministrator, 
            String... unitAdministratorTypes) {
        
        return isUnitAdministratorOfType(unitAdministrator, unitAdministratorTypes) && isPersonIdMassChange(personMassChange, unitAdministrator);
    }
    
    private boolean isUnitAdministratorOfType(UnitAdministrator unitAdministrator, String... unitAdministratorTypes) {
        boolean isUnitAdministratorOfType = false;
        
        for (String unitAdministratorType : unitAdministratorTypes) {
            if (StringUtils.equals(unitAdministrator.getUnitAdministratorTypeCode(), unitAdministratorType)) {
                isUnitAdministratorOfType = true;
                break;
            }
        }
        
        return isUnitAdministratorOfType;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<UnitAdministrator> unitAdministratorChangeCandidates) {
        for (UnitAdministrator unitAdministratorChangeCandidate : unitAdministratorChangeCandidates) {
            UnitAdministrator newUnitAdministrator = new UnitAdministrator();
            newUnitAdministrator.setUnitNumber(unitAdministratorChangeCandidate.getUnitNumber());
            newUnitAdministrator.setPersonId(personMassChange.getReplacerPersonId());
            newUnitAdministrator.setUnitAdministratorTypeCode(unitAdministratorChangeCandidate.getUnitAdministratorTypeCode());
            newUnitAdministrator.setUnit(unitAdministratorChangeCandidate.getUnit());
            newUnitAdministrator.setUnitAdministratorType(unitAdministratorChangeCandidate.getUnitAdministratorType());

            getBusinessObjectService().delete(unitAdministratorChangeCandidate);
            getBusinessObjectService().save(newUnitAdministrator);
        }
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, UnitAdministrator unitAdministrator) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, unitAdministrator.getPersonId());
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}