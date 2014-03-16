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

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonnelSummary implements Serializable {

    private static final long serialVersionUID = 5043509130587736483L;
    
    private String personId;
    private String name;
    private String roleName;
    private String affiliation;
    private List<UnitSummary> units = new ArrayList<UnitSummary>();
    
    private boolean nameChanged;
    private boolean roleNameChanged;
    private boolean affiliationChanged;
    
    public PersonnelSummary() {
        
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getPersonId() {
        return personId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public List<UnitSummary> getUnits() {
        return units;
    }

    public void addUnit(String unitNumber, String unitName) {
        units.add(new UnitSummary(unitNumber, unitName));
    }

    public void compare(ProtocolSummary other) {
        PersonnelSummary otherPerson = other.findPerson(personId);
        if (otherPerson == null) {
            nameChanged = true;
            roleNameChanged = true;
            affiliationChanged = true;
            for (UnitSummary unit : units) {
                unit.setChanged(true);
            }
        }
        else {
            nameChanged = !StringUtils.equals(name, otherPerson.name);
            roleNameChanged = !StringUtils.equals(roleName, otherPerson.roleName);
            affiliationChanged = !StringUtils.equals(affiliation, otherPerson.affiliation);
            for (UnitSummary unit : units) {
                unit.compare(otherPerson);
            }
        }
    }

    public boolean isNameChanged() {
        return nameChanged;
    }

    public boolean isRoleNameChanged() {
        return roleNameChanged;
    }

    public boolean isAffiliationChanged() {
        return affiliationChanged;
    }

    public UnitSummary findUnit(String unitNumber) {
        for (UnitSummary unit : units) {
            if (StringUtils.equals(unit.getUnitNumber(), unitNumber)) {
                return unit;
            }
        }
        return null;
    }
}
