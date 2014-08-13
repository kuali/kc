/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
