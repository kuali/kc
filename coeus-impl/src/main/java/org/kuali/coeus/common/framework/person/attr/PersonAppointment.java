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
package org.kuali.coeus.common.framework.person.attr;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.personnel.AppointmentType;
import org.kuali.coeus.common.budget.framework.personnel.JobCode;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Business Object representation of a PersonDegree. A <code>{@link Person}</code> may have many degrees. This represents the relationship
 * of a <code>{@link Person}</code> to a degree as well as the degree itself.
 * 
 * @see org.kuali.kra.bo.Person
 */
@Entity
@Table(name= "PERSON_APPOINTMENT")
public class PersonAppointment extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -672222601606024412L;

    @PortableSequenceGenerator(name = "SEQ_PERSON_APPOINTMENT")
    @GeneratedValue(generator = "SEQ_PERSON_APPOINTMENT")
    @Id
    @Column(name = "APPOINTMENT_ID")
    private Integer appointmentId;

    @Column(name= "PERSON_ID")
    private String personId;

    @Column(name= "UNIT_NUMBER")
    private String unitNumber;

    @Column(name= "APPOINTMENT_START_DATE")
    private Date startDate;

    @Column(name= "APPOINTMENT_END_DATE")
    private Date endDate;

    @Column(name= "APPOINTMENT_TYPE_CODE")
    private String typeCode;

    @Column(name= "JOB_TITLE")
    private String jobTitle;

    @Column(name= "PREFERED_JOB_TITLE")
    private String preferedJobTitle;

    @Column(name= "JOB_CODE")
    private String jobCode;

    @Column(name= "SALARY")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal salary;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "APPOINTMENT_TYPE_CODE", referencedColumnName = "APPOINTMENT_TYPE_CODE", insertable = false, updatable = false)
    private AppointmentType appointmentType;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE", insertable = false, updatable = false)
    private JobCode jobCodeRef;

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

    public ScaleTwoDecimal getSalary() {
        return salary;
    }

    public void setSalary(ScaleTwoDecimal salary) {
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
