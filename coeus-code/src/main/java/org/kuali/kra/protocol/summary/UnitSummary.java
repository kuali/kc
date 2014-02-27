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
