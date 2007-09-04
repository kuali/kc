/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Represents the Proposal Degree <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: lprzybyl $
 * @version $Revision: 1.2 $
 */
public class ProposalPersonDegree extends KraPersistableBusinessObjectBase {
    private Integer proposalPersonNumber;
    private Integer proposalNumber;
    private Date graduationDate;
    private String degreeCode;
    private String degree;
    private String fieldOfStudy;
    private String specialization;
    private String school;
    private String schoolIdCode;
    private String schoolId;

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
    public final Integer getProposalNumber() {
        return this.proposalNumber;
    }
    
    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public final void setProposalNumber(Integer argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }
    
    /**
     * Gets the value of graduationDate
     *
     * @return the value of graduationDate
     */
    public final Date getGraduationDate() {
        return this.graduationDate;
    }
    
    /**
     * Sets the value of graduationDate
     *
     * @param argGraduationDate Value to assign to this.graduationDate
     */
    public final void setGraduationDate(Date argGraduationDate) {
        this.graduationDate = argGraduationDate;
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
    }
    
    /**
     * Gets the value of degree
     *
     * @return the value of degree
     */
    public final String getDegree() {
        return this.degree;
    }
    
    /**
     * Sets the value of degree
     *
     * @param argDegree Value to assign to this.degree
     */
    public final void setDegree(String argDegree) {
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
    
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalPersonNumber", getProposalPersonNumber());
        propMap.put("proposalNumber", getProposalNumber());
        propMap.put("graduationDate", getGraduationDate());
        propMap.put("degreeCode", getDegreeCode());
        propMap.put("degree", getDegree());
        propMap.put("fieldOfStudy", getFieldOfStudy());
        propMap.put("specialization", getSpecialization());
        propMap.put("school", getSchool());
        propMap.put("schoolIdCode", getSchoolIdCode());
        propMap.put("schoolId", getSchoolId());
        return propMap;
    }

}


