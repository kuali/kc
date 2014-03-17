/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.protocol.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolAssociateBase;

public abstract class ProtocolUnitBase extends ProtocolAssociateBase {


    private static final long serialVersionUID = 8187880795930346699L;

    private Integer protocolUnitsId;

    private Integer protocolPersonId;

    private String unitNumber;

    private boolean leadUnitFlag;

    private String personId;

    private String unitName;

    private Unit unit;

    @SkipVersioning
    private ProtocolPersonBase protocolPerson;

    public ProtocolUnitBase() {
        setLeadUnitFlag(false);
    }

    public Integer getProtocolUnitsId() {
        return protocolUnitsId;
    }

    public void setProtocolUnitsId(Integer protocolUnitsId) {
        this.protocolUnitsId = protocolUnitsId;
    }

    public Integer getProtocolPersonId() {
        return protocolPersonId;
    }

    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public boolean getLeadUnitFlag() {
        return leadUnitFlag;
    }

    public void setLeadUnitFlag(boolean leadUnitFlag) {
        this.leadUnitFlag = leadUnitFlag;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ProtocolPersonBase getProtocolPerson() {
        return protocolPerson;
    }

    public void setProtocolPerson(ProtocolPersonBase protocolPerson) {
        this.protocolPerson = protocolPerson;
    }

    // Note this field isn't persisted in protocolUnit so  
    // we've got do pull from the Unit reference.  
    public String getUnitName() {
        if (StringUtils.isEmpty(unitName) && StringUtils.isNotEmpty(unitNumber)) {
            this.refreshReferenceObject("unit");
            if (unit != null) {
                setUnitName(unit.getUnitName());
            }
        }
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void init(ProtocolPersonBase protocolPerson) {
        setProtocolPerson(protocolPerson);
        setProtocolPersonId(protocolPerson.getProtocolPersonId());
        //this is a little weird...  
        this.init(protocolPerson.getProtocol());
    }

    @Override
    public void resetPersistenceState() {
        this.setProtocolUnitsId(null);
    }
}
