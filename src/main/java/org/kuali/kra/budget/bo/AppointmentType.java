/*
 * Copyright 2006-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Class representation of the Appointment Type Business Object
 *
 * AppointmentType.java
 */
public class AppointmentType extends KraPersistableBusinessObjectBase {
	
	private String appointmentTypeCode;
    private Integer duration;
	private String description;
	
    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
	public String getDescription() {
		return description;
	}
    
    /**
     * Assigns the description attribute
     *
     * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("appointmentTypeCode", this.getAppointmentTypeCode());
		propMap.put("description", this.getDescription());
		propMap.put("duration", this.getDuration());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}

    /**
     * Gets the appointmentTypeCode attribute. 
     * @return Returns the appointmentTypeCode.
     */
    public String getAppointmentTypeCode() {
        return appointmentTypeCode;
    }

    /**
     * Sets the appointmentTypeCode attribute value.
     * @param appointmentTypeCode The appointmentTypeCode to set.
     */
    public void setAppointmentTypeCode(String appointmentTypeCode) {
        this.appointmentTypeCode = appointmentTypeCode;
    }

    /**
     * Gets the duration attribute. 
     * @return Returns the duration.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the duration attribute value.
     * @param duration The duration to set.
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
