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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CoiDisclosureServiceImpl implements CoiDisclosureService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;

    @SuppressWarnings("rawtypes")
    public DisclosurePerson getDisclosureReporter(String personId, Long coiDisclosureId) {
        List<DisclosurePerson> reporters = new ArrayList<DisclosurePerson>();
        if (coiDisclosureId != null) {
            Map fieldValues = new HashMap();
            fieldValues.put("personId", personId);
            fieldValues.put("personRoleId", "COIR");
            fieldValues.put("coiDisclosureId", coiDisclosureId);

            reporters = (List<DisclosurePerson>) businessObjectService.findMatching(DisclosurePerson.class, fieldValues);
        }
        if (reporters.isEmpty()) {
            DisclosurePerson reporter = new DisclosurePerson();
            reporter.setDisclosurePersonUnits(new ArrayList<DisclosurePersonUnit>());
            reporter.setPersonId(personId);
            reporter.setPersonRoleId("COIR");
            DisclosurePersonUnit leadUnit = createLeadUnit(personId);
            if (leadUnit != null) {
                reporter.getDisclosurePersonUnits().add(leadUnit);
            }
            return reporter;
        }
        else {
            int i = 0;
            for (DisclosurePersonUnit disclosurePersonUnit : reporters.get(0).getDisclosurePersonUnits()) {
                if (disclosurePersonUnit.isLeadUnitFlag()) {
                    reporters.get(0).setSelectedUnit(i);
                    break;
                }
                i++;
            }
        }
        return reporters.get(0);
    }

    public void addDisclosurePersonUnit(DisclosurePerson disclosurePerson , DisclosurePersonUnit newDisclosurePersonUnit) {
        
        List<DisclosurePersonUnit> disclosurePersonUnits = disclosurePerson.getDisclosurePersonUnits();
        if (newDisclosurePersonUnit.isLeadUnitFlag()) {
            resetLeadUnit(disclosurePersonUnits);
            disclosurePerson.setSelectedUnit(disclosurePersonUnits.size());
        }
        disclosurePersonUnits.add(newDisclosurePersonUnit);
    }

    public void deleteDisclosurePersonUnit(DisclosurePerson disclosurePerson,List<DisclosurePersonUnit> deletedUnits, int unitIndex) {
        
        List<DisclosurePersonUnit> disclosurePersonUnits = disclosurePerson.getDisclosurePersonUnits();
        DisclosurePersonUnit deletedUnit = disclosurePersonUnits.get(unitIndex);
        if (deletedUnit.getDisclosurePersonUnitsId() != null) {
            deletedUnits.add(deletedUnit);
        }
        disclosurePersonUnits.remove(unitIndex);
        if (deletedUnit.isLeadUnitFlag() && !disclosurePersonUnits.isEmpty()) {
            disclosurePersonUnits.get(0).setLeadUnitFlag(true);
            disclosurePerson.setSelectedUnit(0);
        }
    }

    public void resetLeadUnit(DisclosurePerson disclosurePerson) {
        List<DisclosurePersonUnit> disclosurePersonUnits = disclosurePerson.getDisclosurePersonUnits();
        if (CollectionUtils.isNotEmpty(disclosurePersonUnits)) {
            resetLeadUnit(disclosurePersonUnits);
            disclosurePersonUnits.get(disclosurePerson.getSelectedUnit()).setLeadUnitFlag(true);
        }
    }


    private void resetLeadUnit(List<DisclosurePersonUnit> disclosurePersonUnits) {
        for (DisclosurePersonUnit  disclosurePersonUnit : disclosurePersonUnits) {
            disclosurePersonUnit.setLeadUnitFlag(false);
        }
        
    }

    private DisclosurePersonUnit createLeadUnit(String personId) {

        DisclosurePersonUnit leadUnit = null;
        KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(personId);
        if (kcPerson != null && kcPerson.getUnit() != null) {
            leadUnit = new DisclosurePersonUnit();
            leadUnit.setLeadUnitFlag(true);
            leadUnit.setUnitNumber(kcPerson.getUnit().getUnitNumber());
            leadUnit.setUnitName(kcPerson.getUnit().getUnitName());
            leadUnit.setPersonId(personId);
        }
        return leadUnit;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
