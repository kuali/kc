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
package org.kuali.kra.personmasschange.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.UnitAdministratorPersonMassChangeService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Defines the service for performing a Person Mass Change on Units.
 * 
 * Person roles that might be replaced are: Administrative Officer, OSP Administrator, Unit Head, Dean VP, Other Individual to Notify, Administrative Contact, 
 * Financial Contact.
 */
@Component("unitAdministratorPersonMassChangeService")
public class UnitAdministratorPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements UnitAdministratorPersonMassChangeService {

    @Override
    public List<UnitAdministrator> getUnitAdministratorChangeCandidates(PersonMassChange personMassChange) {
        Set<UnitAdministrator> unitAdministratorChangeCandidates = new HashSet<UnitAdministrator>();
        
        List<UnitAdministrator> unitAdministrators = new ArrayList<UnitAdministrator>();
        if (personMassChange.getUnitAdministratorPersonMassChange().requiresChange()) {
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

        if (personMassChange.getUnitAdministratorPersonMassChange().isAdministrativeOfficer()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.ADMINISTRATIVE_OFFICER_TYPE_CODE);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isOspAdministrator()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.OSP_ADMINISTRATOR_TYPE_CODE);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isUnitHead()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.UNIT_HEAD_TYPE_CODE);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isDeanVP()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.DEAN_VP_TYPE_CODE);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isOtherIndividualToNotify()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.OTHER_INDIVIDUAL_TO_NOTIFY_TYPE_CODE);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isAdministrativeContact()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.ADMINISTRATIVE_CONTACT_TYPE_CODE);
        }
        if (personMassChange.getUnitAdministratorPersonMassChange().isFinancialContact()) {
            isUnitAdministratorChangeCandidate 
                |= isChangeCandidate(personMassChange, unitAdministrator, UnitAdministratorType.FINANCIAL_CONTACT_TYPE_CODE);
        }
        
        return isUnitAdministratorChangeCandidate;
    }
    
    private boolean isChangeCandidate(PersonMassChange personMassChange, UnitAdministrator unitAdministrator, String... unitAdministratorTypes) {
        return isUnitAdministratorOfType(unitAdministrator, unitAdministratorTypes) && isPersonIdMassChange(personMassChange, unitAdministrator.getPersonId());
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
            
            getBusinessObjectService().delete(unitAdministratorChangeCandidate);
            getBusinessObjectService().save(newUnitAdministrator);
        }
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    protected String getDocumentName() {
        throw new RuntimeException("unimplemented");
    }

    @Override
    protected String getWarningKey() {
        throw new RuntimeException("unimplemented");
    }

}