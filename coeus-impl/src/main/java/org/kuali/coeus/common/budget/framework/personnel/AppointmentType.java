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
package org.kuali.coeus.common.budget.framework.personnel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.coeus.common.budget.api.personnel.AppointmentTypeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Class representation of the Appointment Type Business Object
 *
 * AppointmentType.java
 */
@Entity
@Table(name = "APPOINTMENT_TYPE")
public class AppointmentType extends KcPersistableBusinessObjectBase implements AppointmentTypeContract {

    @Id
    @Column(name = "APPOINTMENT_TYPE_CODE")
    private String appointmentTypeCode;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppointmentTypeCode() {
        return appointmentTypeCode;
    }

    public void setAppointmentTypeCode(String appointmentTypeCode) {
        this.appointmentTypeCode = appointmentTypeCode;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String getCode() {
        return getAppointmentTypeCode();
    }
}
