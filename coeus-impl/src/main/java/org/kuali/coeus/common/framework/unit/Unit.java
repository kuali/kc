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
package org.kuali.coeus.common.framework.unit;

import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Unit
 */
@Entity
@Table(name = "UNIT")
public class Unit extends KcPersistableBusinessObjectBase implements MutableInactivatable, UnitContract {

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

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PARENT_UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit parentUnit;

    @OneToMany(mappedBy = "unit")
    @OrderBy("unitNumber")
    private List<UnitAdministrator> unitAdministrators;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    public Unit() {
        super();
        unitAdministrators = new ArrayList<UnitAdministrator>();
    }

    @Override
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
    @Override
    public String getOrganizationId() {
        if (organizationId == null && this.getParentUnit() != null && this.getParentUnit().getUnitNumber() != null) {
            return this.getParentUnit().getOrganizationId();
        }
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
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

    @Override
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

    @Override
    public Unit getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(Unit parentUnit) {
        this.parentUnit = parentUnit;
    }

    @Override
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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((unitNumber == null) ? 0 : unitNumber.hashCode());
        return result;
    }

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
