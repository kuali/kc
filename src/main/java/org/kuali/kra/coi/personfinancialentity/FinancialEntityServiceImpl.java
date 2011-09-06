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
package org.kuali.kra.coi.personfinancialentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class is to implement the detail of methods declared in FinancialENtityService
 */
@Transactional
public class FinancialEntityServiceImpl implements FinancialEntityService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntities(java.lang.String, boolean)
     */
    public List<PersonFinIntDisclosure> getFinancialEntities(String personId, boolean active) {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", personId);
        if (active) {
            fieldValues.put("statusCode", "1");
        }
        else {
            fieldValues.put("statusCode", "2");
        }
        fieldValues.put("currentFlag", "Y");

        return (List<PersonFinIntDisclosure>) businessObjectService.findMatching(PersonFinIntDisclosure.class, fieldValues);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public FinancialEntityReporter getFinancialEntityReporter(String personId) {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", personId);
        fieldValues.put("reporterRoleId", "FER");

        List<FinancialEntityReporter> reporters = (List<FinancialEntityReporter>) businessObjectService.findMatching(
                FinancialEntityReporter.class, fieldValues);
        if (reporters.isEmpty()) {
            FinancialEntityReporter reporter = new FinancialEntityReporter();
            reporter.setFinancialEntityUnits(new ArrayList<FinancialEntityUnit>());
            reporter.setPersonId(personId);
            reporter.setReporterRoleId("FER");
            FinancialEntityUnit leadUnit = createLeadUnit(personId);
            if (leadUnit != null) {
                reporter.getFinancialEntityUnits().add(leadUnit);
            }
            businessObjectService.save(reporter);
            return reporter;
        } else {
            int i = 0;
            for (FinancialEntityUnit financialEntityUnit : reporters.get(0).getFinancialEntityUnits()) {
                if (financialEntityUnit.isLeadUnitFlag()) {
                    reporters.get(0).setSelectedUnit(i);
                    break;
                }
                i++;
            }
        }
        return reporters.get(0);
    }

    public void addFinancialEntityUnit(FinancialEntityReporter financialEntityReporter , FinancialEntityUnit newFinancialEntityUnit) {
        
        List<FinancialEntityUnit> financialEntityUnits = financialEntityReporter.getFinancialEntityUnits();
        if (newFinancialEntityUnit.isLeadUnitFlag()) {
            resetLeadUnit(financialEntityUnits);
            financialEntityReporter.setSelectedUnit(financialEntityUnits.size());
        }
        financialEntityUnits.add(newFinancialEntityUnit);
    }

    public void deleteFinancialEntityUnit(FinancialEntityReporter financialEntityReporter,List<FinancialEntityUnit> deletedUnits, int unitIndex) {
        
        List<FinancialEntityUnit> financialEntityUnits = financialEntityReporter.getFinancialEntityUnits();
        FinancialEntityUnit deletedUnit = financialEntityUnits.get(unitIndex);
        if (deletedUnit.getFinancialEntityUnitsId() != null) {
            deletedUnits.add(deletedUnit);
        }
        financialEntityUnits.remove(unitIndex);
        if (deletedUnit.isLeadUnitFlag() && !financialEntityUnits.isEmpty()) {
            financialEntityUnits.get(0).setLeadUnitFlag(true);
            financialEntityReporter.setSelectedUnit(0);
        }
    }

    public void resetLeadUnit(FinancialEntityReporter financialEntityReporter) {
        List<FinancialEntityUnit> financialEntityUnits = financialEntityReporter.getFinancialEntityUnits();
        resetLeadUnit(financialEntityUnits);
        financialEntityUnits.get(financialEntityReporter.getSelectedUnit()).setLeadUnitFlag(true);
    }

    private void resetLeadUnit(List<FinancialEntityUnit> financialEntityUnits) {
        for (FinancialEntityUnit  financialEntityUnit : financialEntityUnits) {
            financialEntityUnit.setLeadUnitFlag(false);
        }
        
    }
    
    private FinancialEntityUnit createLeadUnit(String personId) {

        FinancialEntityUnit leadUnit = null;
        KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(personId);
        if (kcPerson != null && kcPerson.getUnit() != null) {
            leadUnit = new FinancialEntityUnit();
            leadUnit.setLeadUnitFlag(true);
            leadUnit.setUnitNumber(kcPerson.getUnit().getUnitNumber());
            leadUnit.setUnitName(kcPerson.getUnit().getUnitName());
            leadUnit.setPersonId(personId);
        }
        return leadUnit;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
