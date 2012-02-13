/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;

public class UnitAdministrator extends KraPersistableBusinessObjectBase implements AbstractUnitAdministrator {

    private String personId;

    private String unitAdministratorTypeCode;

    private String unitNumber;

    private Unit unit;

    private UnitAdministratorType unitAdministratorType;

    private transient KcPersonService kcPersonService;

    public UnitAdministrator() {
        super();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUnitAdministratorTypeCode() {
        return unitAdministratorTypeCode;
    }

    public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
        this.unitAdministratorTypeCode = unitAdministratorTypeCode;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public KcPerson getPerson() {
        return getKcPersonService().getKcPersonByPersonId(personId);
    }

    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public UnitAdministratorType getUnitAdministratorType() {
        return unitAdministratorType;
    }

    public void setUnitAdministratorType(UnitAdministratorType unitAdministratorType) {
        this.unitAdministratorType = unitAdministratorType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
