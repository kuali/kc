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
package org.kuali.kra.protocol.summary;

import java.io.Serializable;

public class UnitSummary implements Serializable {

    private static final long serialVersionUID = -5513896542027540966L;
    
    private String unitNumber;
    private String unitName;
    
    private boolean changed;
    
    public UnitSummary(String unitNumber, String unitName) {
        this.unitNumber = unitNumber;
        this.unitName = unitName;
    }
    
    public String getUnitNumber() {
        return unitNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isChanged() {
        return changed;
    }

    public void compare(PersonnelSummary otherPerson) {
        UnitSummary unit = otherPerson.findUnit(unitNumber);
        changed = (unit == null);
    }
}
