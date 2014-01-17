/*
 * Copyright 2005-2013 The Kuali Foundation
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
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 * This class is a Unit
 */
@Entity
@Table(name = "UNIT")
public class Unit extends KraPersistableBusinessObjectBase implements MutableInactivatable {

    private static final long serialVersionUID = 7170184898996866958L;

    @Id
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;

    @Column(name = "PARENT_UNIT_NUMBER")
    private String parentUnitNumber;

    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "UNIT_NAME")
    private String unitName;

    //maps to Campus-code 
    @Transient
    private String code;

    @Column(name = "ACTIVE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @ManyToOne(targetEntity = Unit.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PARENT_UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit parentUnit;

    @OneToMany(mappedBy = "unit")
    @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private List<UnitAdministrator> unitAdministrators;

    @ManyToOne(targetEntity = Organization.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        //KRACOEUS-5499 - Sort the List for better grouping. 
        if (unitAdministrators != null) {
            Collections.sort(unitAdministrators);
        }
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

    /**
     * Determine whether the given unit is a parent (or grandparent, etc) of this unit.
     * @param parentCandidate
     * @return boolean
     */
    public boolean isParentUnit(Unit parentCandidate) {
        if (this.getParentUnitNumber() != null) {
            if (this.getParentUnitNumber().equals(parentCandidate.getUnitNumber())) {
                return true;
            } else {
                return this.getParentUnit().isParentUnit(parentCandidate);
            }
        }
        return false;
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
