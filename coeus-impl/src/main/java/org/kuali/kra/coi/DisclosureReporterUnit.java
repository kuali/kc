/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.util.KRADConstants;

public abstract class DisclosureReporterUnit extends KcPersistableBusinessObjectBase {
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
