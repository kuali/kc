/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.DegreeType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Represents the Proposal Degree <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: gmcgrego $
 * @version $Revision: 1.9 $
 */
@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalPersonDegreeId.class)
@Entity
@Table(name="EPS_PROP_PERSON_DEGREE")
public class ProposalPersonDegree extends KraPersistableBusinessObjectBase {
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @Id
	@Column(name="PROP_PERSON_NUMBER")
	private Integer proposalPersonNumber;
    
    @Id
	@Column(name="DEGREE_SEQUENCE_NUMBER")
	private Integer degreeSequenceNumber;
    
    @Column(name="GRADUATION_YEAR")
	private String graduationYear;
    
    @Column(name="DEGREE_CODE")
	private String degreeCode;
    
    @Column(name="DEGREE")
	private String degree;
    
    @Column(name="FIELD_OF_STUDY")
	private String fieldOfStudy;
    
    @Column(name="SPECIALIZATION")
	private String specialization;
    
    @Column(name="SCHOOL")
	private String school;
    
    @Column(name="SCHOOL_ID_CODE")
	private String schoolIdCode;
    
    @Column(name="SCHOOL_ID")
	private String schoolId;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="DEGREE_CODE", insertable=false, updatable=false)
	private DegreeType degreeType;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="PROP_PERSON_NUMBER", insertable=false, updatable=false)})
    private ProposalPerson proposalPerson;

    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public final Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }
    
    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public final void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }
    
    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public final String getProposalNumber() {
        return this.proposalNumber;
    }
    
    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public final void setProposalNumber(String argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }
    
    /**
     * Gets the value of graduationYear
     *
     * @return the value of graduationYear
     */
    public final String getGraduationYear() {
        return this.graduationYear;
    }
    
    /**
     * Sets the value of graduationYear
     *
     * @param argGraduationYear Value to assign to this.graduationYear
     */
    public final void setGraduationYear(String argGraduationYear) {
        this.graduationYear = argGraduationYear;
    }
    
    /**
     * Gets the value of degreeCode
     *
     * @return the value of degreeCode
     */
    public final String getDegreeCode() {
        return this.degreeCode;
    }
    
    /**
     * Sets the value of degreeCode
     *
     * @param argDegreeCode Value to assign to this.degreeCode
     */
    public final void setDegreeCode(String argDegreeCode) {
        this.degreeCode = argDegreeCode;
        
        if (isBlank(degreeCode)) {
            degree = null;
        }
        else {
            refreshReferenceObject("degreeType");
        }
    }
    
    /**
     * Gets the value of degree
     *
     * @return the value of degree
     */
    public String getDegree() {
        return this.degree;
    }
    
    /**
     * Sets the value of degree
     *
     * @param argDegree Value to assign to this.degree
     */
    public void setDegree(String argDegree) {
        this.degree = argDegree;
    }
    
    /**
     * Gets the value of fieldOfStudy
     *
     * @return the value of fieldOfStudy
     */
    public final String getFieldOfStudy() {
        return this.fieldOfStudy;
    }
    
    /**
     * Sets the value of fieldOfStudy
     *
     * @param argFieldOfStudy Value to assign to this.fieldOfStudy
     */
    public final void setFieldOfStudy(String argFieldOfStudy) {
        this.fieldOfStudy = argFieldOfStudy;
    }
    
    /**
     * Gets the value of specialization
     *
     * @return the value of specialization
     */
    public final String getSpecialization() {
        return this.specialization;
    }

    /**
     * Sets the value of specialization
     *
     * @param argSpecialization Value to assign to this.specialization
     */
    public final void setSpecialization(String argSpecialization) {
        this.specialization = argSpecialization;
    }
    
    /**
     * Gets the value of school
     *
     * @return the value of school
     */
    public final String getSchool() {
        return this.school;
    }
    
    /**
     * Sets the value of school
     *
     * @param argSchool Value to assign to this.school
     */
    public final void setSchool(String argSchool) {
        this.school = argSchool;
    }
    
    /**
     * Gets the value of schoolIdCode
     *
     * @return the value of schoolIdCode
     */
    public final String getSchoolIdCode() {
        return this.schoolIdCode;
    }
    
    /**
     * Sets the value of schoolIdCode
     *
     * @param argSchoolIdCode Value to assign to this.schoolIdCode
     */
    public final void setSchoolIdCode(String argSchoolIdCode) {
        this.schoolIdCode = argSchoolIdCode;
    }
    
    /**
     * Gets the value of schoolId
     *
     * @return the value of schoolId
     */
    public final String getSchoolId() {
        return this.schoolId;
    }
    
    /**
     * Sets the value of schoolId
     *
     * @param argSchoolId Value to assign to this.schoolId
     */
    public final void setSchoolId(String argSchoolId) {
        this.schoolId = argSchoolId;
    }
    
    /**
     * Assign a reference to <code>{@link DegreeType}</code>
     *
     * @param type to reference
     */
    public final void setDegreeType(DegreeType type) {
        degreeType = type;
    }

    /**
     * Retrieve a reference to <code>{@link DegreeType}</code>
     *
     * @return DegreeType
     */
    public final DegreeType getDegreeType() {
        return degreeType;
    }
    
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalPersonNumber", getProposalPersonNumber());
        propMap.put("proposalNumber", getProposalNumber());
        propMap.put("graduationDate", getGraduationYear());
        propMap.put("degreeCode", getDegreeCode());
        propMap.put("degree", getDegree());
        propMap.put("fieldOfStudy", getFieldOfStudy());
        propMap.put("specialization", getSpecialization());
        propMap.put("school", getSchool());
        propMap.put("schoolIdCode", getSchoolIdCode());
        propMap.put("schoolId", getSchoolId());
        propMap.put("degreeType", getDegreeType());
        return propMap;
    }

    public Integer getDegreeSequenceNumber() {
        return degreeSequenceNumber;
    }

    public void setDegreeSequenceNumber(Integer degreeSequenceNumber) {
        this.degreeSequenceNumber = degreeSequenceNumber;
    }

    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }

    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }
}



