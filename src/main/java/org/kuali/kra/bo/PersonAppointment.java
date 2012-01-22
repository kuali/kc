/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.sql.Date;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.personnel.AppointmentType;
import org.kuali.kra.budget.personnel.JobCode;

/**
 * Business Object representation of a PersonDegree. A <code>{@link Person}</code> may have many degrees. This represents the relationship
 * of a <code>{@link Person}</code> to a degree as well as the degree itself.
 * 
 * @see org.kuali.kra.bo.Person
 */
public class PersonAppointment extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -672222601606024412L;

    private Integer appointmentId;

    private String personId;

    private String unitNumber;

    private Unit unit;

    private Date startDate;

    private Date endDate;

    private String typeCode;

    private AppointmentType appointmentType;

    private String jobTitle;

    private String preferedJobTitle;

    private String jobCode;

    private JobCode jobCodeRef;

    private BudgetDecimal salary;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPreferedJobTitle() {
        return preferedJobTitle;
    }

    public void setPreferedJobTitle(String preferedJobTitle) {
        this.preferedJobTitle = preferedJobTitle;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public JobCode getJobCodeRef() {
        return jobCodeRef;
    }

    public void setJobCodeRef(JobCode jobCodeRef) {
        this.jobCodeRef = jobCodeRef;
    }

    public BudgetDecimal getSalary() {
        return salary;
    }

    public void setSalary(BudgetDecimal salary) {
        this.salary = salary;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
}
