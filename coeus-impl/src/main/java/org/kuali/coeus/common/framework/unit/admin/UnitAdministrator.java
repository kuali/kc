/*
 * Copyright 2005-2014 The Kuali Foundation
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
        return getKcPersonService().getKcPersonByPersonId(personId);
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
