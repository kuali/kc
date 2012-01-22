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
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.UnitPersonMassChangeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Units.
 */
public class UnitPersonMassChangeServiceImpl implements UnitPersonMassChangeService {

    private BusinessObjectService businessObjectService;

    @Override
    @SuppressWarnings("unchecked")
    public List<Unit> getUnitChangeCandidates(PersonMassChange personMassChange) {
        List<Unit> unitChangeCandidates = new ArrayList<Unit>();

        Collection<Unit> units = getBusinessObjectService().findAll(Unit.class);
        for (Unit unit : units) {
            for (UnitAdministrator unitAdministrator : unit.getUnitAdministrators()) {
                if (StringUtils.equals(personMassChange.getReplaceePersonId(), unitAdministrator.getPersonId())) {
                    unitChangeCandidates.add(unit);
                    break;
                }
            }
        }
        
        return unitChangeCandidates;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Unit> unitChangeCandidates) {
        for (Unit unitChangeCandidate : unitChangeCandidates) {
            List<UnitAdministrator> unitAdministratorChangeCandidates = new ArrayList<UnitAdministrator>();
            
            for (UnitAdministrator unitAdministrator : unitChangeCandidate.getUnitAdministrators()) {
                if (StringUtils.equals(personMassChange.getReplaceePersonId(), unitAdministrator.getPersonId())) {
                    unitAdministratorChangeCandidates.add(unitAdministrator);
                }
            }
            
            for (UnitAdministrator unitAdministratorChangeCandidate : unitAdministratorChangeCandidates) {
                UnitAdministrator newUnitAdministrator = new UnitAdministrator();
                newUnitAdministrator.setUnitNumber(unitAdministratorChangeCandidate.getUnitNumber());
                newUnitAdministrator.setPersonId(personMassChange.getReplacerPersonId());
                newUnitAdministrator.setUnitAdministratorTypeCode(unitAdministratorChangeCandidate.getUnitAdministratorTypeCode());
                newUnitAdministrator.setUnit(unitAdministratorChangeCandidate.getUnit());
                newUnitAdministrator.setUnitAdministratorType(unitAdministratorChangeCandidate.getUnitAdministratorType());
                
                unitChangeCandidate.getUnitAdministrators().remove(unitAdministratorChangeCandidate);
                unitChangeCandidate.getUnitAdministrators().add(newUnitAdministrator);
                
                getBusinessObjectService().delete(unitAdministratorChangeCandidate);
                getBusinessObjectService().save(newUnitAdministrator);
            }
        }
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}