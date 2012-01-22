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
package org.kuali.kra.coi;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Unit;
import org.kuali.rice.krad.util.KRADConstants;

public abstract class DisclosureReporterUnit extends KraPersistableBusinessObjectBase {
    private String unitName;
    private Unit unit; 
    
    public abstract String getUnitNumber();
    public abstract Long getReporterUnitId();
    public abstract void setUnitNumber(String unitNumber);
    public abstract boolean isLeadUnitFlag();

    public abstract void setLeadUnitFlag(boolean leadUnitFlag);

//    public abstract String getPersonId();

 
    public String getUnitName() {
        // first use getUnit(0 to see if unit needs to be refreshed
        if (getUnit() != null) {
            unitName = unit.getUnitName();
        }
        else {
            if (StringUtils.isNotBlank(getUnitNumber())) {
                unitName = "not found";
            }
            else {
                unitName = KRADConstants.EMPTY_STRING;
            }
        }
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Unit getUnit() {
        if (StringUtils.isNotBlank(getUnitNumber())) {
            if (this.getReporterUnitId() == null) {
                this.refreshReferenceObject("unit");
            } else if (unit == null) {
                this.refreshReferenceObject("unit");
            }
        } else {
            unit = null;
        }
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

}
