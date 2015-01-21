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


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

/**
 * Business Object representation of a PersonDegree. A <code>{@link Person}</code> may have many degrees. This represents the relationship
 * of a <code>{@link Person}</code> to a degree as well as the degree itself.
 * 
 * @see org.kuali.kra.bo.Person
 */
@Entity
@Table(name = "PERSON_DEGREE")
public class PersonDegree extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 586782856382134862L;

    @PortableSequenceGenerator(name = "SEQ_PERSON_DEGREE")
    @GeneratedValue(generator = "SEQ_PERSON_DEGREE")
    @Id
    @Column(name = "DEGREE_ID")
    private Integer degreeId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "DEGREE_CODE")
    private String degreeCode;

    @Column(name = "DEGREE")
    private String degree;

    @Column(name = "FIELD_OF_STUDY")
    private String fieldOfStudy;

    @Column(name = "SPECIALIZATION")
    private String specialization;

    @Column(name = "SCHOOL")
    private String school;

    @Column(name = "SCHOOL_ID_CODE")
    private String schoolIdCode;

    @Column(name = "SCHOOL_ID")
    private String schoolId;

    @Column(name = "GRADUATION_YEAR")
    private String graduationYear;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DEGREE_CODE", referencedColumnName = "DEGREE_CODE", insertable = false, updatable = false)
    private DegreeType degreeType;

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolIdCode() {
        return schoolIdCode;
    }

    public void setSchoolIdCode(String schoolIdCode) {
        this.schoolIdCode = schoolIdCode;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }
}
