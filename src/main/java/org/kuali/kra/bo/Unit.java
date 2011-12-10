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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This class is a Unit
 */
public class Unit extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2473668965878807557L;
    
    private String unitNumber;
    private String parentUnitNumber;
    private String organizationId;
    private String unitName;
    private String campusCode;
 
    
    private Unit parentUnit;
    private List<UnitAdministrator> unitAdministrators;

    private Organization organization;

    public Unit() {
        super();
        unitAdministrators = new ArrayList<UnitAdministrator>();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }


    public String getParentUnitNumber() {
        return parentUnitNumber;
    }

    public void setParentUnitNumber(String parentUnitNumber) {
        this.parentUnitNumber = parentUnitNumber;
    }

    /**
     * Returns the organization id.  If no organization id is found, recurses up the hierarchy until a valid organization id is found.
     * 
     * @return an organization id in the hierarchy
     */
    public String getOrganizationId() {
        if (organizationId == null && this.getParentUnit() != null && this.getParentUnit().getUnitNumber() != null) {
            return this.getParentUnit().getOrganizationId();
        }
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("unitNumber", getUnitNumber());
        hashMap.put("parentUnitNumber", getParentUnitNumber());
        hashMap.put("organizationId", getOrganizationId());
        hashMap.put("unitName", getUnitName());
        hashMap.put("campusCode", getCampusCode());
        return hashMap;
    }

    /**
     * Returns the organization.  If no organization is found, recurses up the hierarchy until a valid organization is found.
     * 
     * @return an organization in the hierarchy
     */
    public Organization getOrganization() {
        if (organization == null && this.getParentUnit() != null && this.getParentUnit().getUnitNumber() != null) {
            //will recurse up hierarchy until an Organization is found
            return this.getParentUnit().getOrganization();
        }
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Unit getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(Unit parentUnit) {
        this.parentUnit = parentUnit;
    }

    public List<UnitAdministrator> getUnitAdministrators() {
        return unitAdministrators;
    }

    public void setUnitAdministrators(List<UnitAdministrator> unitAdministrators) {
        this.unitAdministrators = unitAdministrators;
    }
    

    /**
     * Gets the organizationIdForMaintenance attribute. 
     * @return Returns the organizationIdForMaintenance.
     */
    public String getOrganizationIdForMaintenance() {
        return organizationId;
    }

    /**
     * Sets the organizationIdForMaintenance attribute value.
     * @param organizationIdForMaintenance The organizationIdForMaintenance to set.
     */
    public void setOrganizationIdForMaintenance(String organizationIdForMaintenance) {
        this.organizationId = organizationIdForMaintenance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        // TODO : need this ?
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getUnitAdministrators());
        return managedLists;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((unitNumber == null) ? 0 : unitNumber.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) obj;
        if (unitNumber == null) {
            if (other.unitNumber != null) {
                return false;
            }
        } else if (!unitNumber.equals(other.unitNumber)) {
            return false;
        }
        return true;
    }

}
