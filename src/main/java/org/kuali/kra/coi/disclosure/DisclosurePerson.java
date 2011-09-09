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

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Transient;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;

public class DisclosurePerson extends KraPersistableBusinessObjectBase {
    /**
     * TODO : not sure about this table. 1. should we combine this with coi reporter/correspondent, 2. personRoleId do we need it
     **/

    private Long disclosurePersonId;
    private Long coiDisclosureId;
    private String personId;
    // private String personName;
    private String personRoleId;
    private List<DisclosurePersonUnit> disclosurePersonUnits;
    private CoiDisclosure coiDisclosure;
    private int selectedUnit;
    private transient KcPersonService kcPersonService;
    private transient KcPerson reporter;

    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    public Long getDisclosurePersonId() {
        return disclosurePersonId;
    }

    public void setDisclosurePersonId(Long disclosurePersonId) {
        this.disclosurePersonId = disclosurePersonId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(String personRoleId) {
        this.personRoleId = personRoleId;
    }

    public List<DisclosurePersonUnit> getDisclosurePersonUnits() {
        return disclosurePersonUnits;
    }

    public void setDisclosurePersonUnits(List<DisclosurePersonUnit> disclosurePersonUnits) {
        this.disclosurePersonUnits = disclosurePersonUnits;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public int getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(int selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public KcPerson getReporter() {
        if (reporter == null && this.personId != null) {
            reporter = getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return reporter;
    }

    /**
     * Gets the KC Person Service.
     * 
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }

        return this.kcPersonService;
    }


}
