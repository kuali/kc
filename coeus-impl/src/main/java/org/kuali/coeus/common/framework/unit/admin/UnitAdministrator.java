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
package org.kuali.coeus.common.framework.unit.admin;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.api.unit.admin.UnitAdministratorContract;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UNIT_ADMINISTRATOR")
@IdClass(UnitAdministrator.UnitAdministratorId.class)
public class UnitAdministrator extends KcPersistableBusinessObjectBase implements AbstractUnitAdministrator, Comparable<UnitAdministrator>, UnitAdministratorContract {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UnitAdministrator.class);

    @Id
    @Column(name = "PERSON_ID")
    private String personId;

    @Id
    @Column(name = "UNIT_ADMINISTRATOR_TYPE_CODE")
    private String unitAdministratorTypeCode;

    @Id
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;

    @ManyToOne
    @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "UNIT_ADMINISTRATOR_TYPE_CODE", referencedColumnName = "UNIT_ADMINISTRATOR_TYPE_CODE", insertable = false, updatable = false)
    private UnitAdministratorType unitAdministratorType;

    @Transient
    private transient KcPersonService kcPersonService;

    @Override
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUnitAdministratorTypeCode() {
        return unitAdministratorTypeCode;
    }

    public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
        this.unitAdministratorTypeCode = unitAdministratorTypeCode;
    }

    @Override
    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public KcPerson getPerson() {
        KcPerson kcPerson = null;
        try {
            if (personId !=null) {
                return getKcPersonService().getKcPersonByPersonId(personId);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.info("getPerson(): ignoring missing person/entity: " + personId);
        }
        return kcPerson;
    }

    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    @Override
    public UnitAdministratorType getUnitAdministratorType() {
        return unitAdministratorType;
    }

    public void setUnitAdministratorType(UnitAdministratorType unitAdministratorType) {
        this.unitAdministratorType = unitAdministratorType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    //KRACOEUS-5499 Implemented Comparable interface. 
    @Override
    public int compareTo(UnitAdministrator unitAdmin) {
        int result = 0;
        if (unitAdmin == null) {
            result = 1;
        } else {
            if (!getUnitAdministratorTypeCode().equalsIgnoreCase(unitAdmin.getUnitAdministratorTypeCode())) {
                result = getUnitAdministratorTypeCode().compareTo(unitAdmin.getUnitAdministratorTypeCode());
            } else {
                if (getPerson() != null && StringUtils.isNotEmpty(getPerson().getLastName()) && unitAdmin.getPerson() != null && StringUtils.isNotEmpty(unitAdmin.getPerson().getLastName())) {
                    result = getPerson().getLastName().compareTo(unitAdmin.getPerson().getLastName());
                }
            }
        }
        return result;
    }

    public static final class UnitAdministratorId implements Serializable, Comparable<UnitAdministratorId> {

        private String unitNumber;

        private String personId;

        private String unitAdministratorTypeCode;

        public String getUnitNumber() {
            return this.unitNumber;
        }

        public void setUnitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
        }

        public String getPersonId() {
            return this.personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getUnitAdministratorTypeCode() {
            return this.unitAdministratorTypeCode;
        }

        public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
            this.unitAdministratorTypeCode = unitAdministratorTypeCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("unitNumber", this.unitNumber).append("personId", this.personId).append("unitAdministratorTypeCode", this.unitAdministratorTypeCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final UnitAdministratorId rhs = (UnitAdministratorId) other;
            return new EqualsBuilder().append(this.unitNumber, rhs.unitNumber).append(this.personId, rhs.personId).append(this.unitAdministratorTypeCode, rhs.unitAdministratorTypeCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.unitNumber).append(this.personId).append(this.unitAdministratorTypeCode).toHashCode();
        }

        @Override
        public int compareTo(UnitAdministratorId other) {
            return new CompareToBuilder().append(this.unitNumber, other.unitNumber).append(this.personId, other.personId).append(this.unitAdministratorTypeCode, other.unitAdministratorTypeCode).toComparison();
        }
    }
}
