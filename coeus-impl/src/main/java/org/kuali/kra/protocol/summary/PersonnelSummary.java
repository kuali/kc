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
