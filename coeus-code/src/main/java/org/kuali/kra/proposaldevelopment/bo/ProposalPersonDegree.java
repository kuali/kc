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
package org.kuali.kra.proposaldevelopment.bo;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.bo.DegreeType;

import javax.persistence.*;
import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Represents the Proposal Degree <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument
 * @author $Author: gmcgrego $
 * @version $Revision: 1.9 $
 */
@Entity
@Table(name = "EPS_PROP_PERSON_DEGREE")
@IdClass(ProposalPersonDegree.ProposalPersonDegreeId.class)
public class ProposalPersonDegree extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "DEGREE_SEQUENCE_NUMBER")
    private Integer degreeSequenceNumber;

    @Column(name = "GRADUATION_YEAR")
    private String graduationYear;

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

    @ManyToOne(targetEntity = DegreeType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DEGREE_CODE", referencedColumnName = "DEGREE_CODE", insertable = false, updatable = false)
    private DegreeType degreeType;

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
        } else {
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

    public Integer getDegreeSequenceNumber() {
        return degreeSequenceNumber;
    }

    public void setDegreeSequenceNumber(Integer degreeSequenceNumber) {
        this.degreeSequenceNumber = degreeSequenceNumber;
    }

    public static final class ProposalPersonDegreeId implements Serializable, Comparable<ProposalPersonDegreeId> {

        private String proposalNumber;

        private Integer proposalPersonNumber;

        private Integer degreeSequenceNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getProposalPersonNumber() {
            return this.proposalPersonNumber;
        }

        public void setProposalPersonNumber(Integer proposalPersonNumber) {
            this.proposalPersonNumber = proposalPersonNumber;
        }

        public Integer getDegreeSequenceNumber() {
            return this.degreeSequenceNumber;
        }

        public void setDegreeSequenceNumber(Integer degreeSequenceNumber) {
            this.degreeSequenceNumber = degreeSequenceNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("proposalPersonNumber", this.proposalPersonNumber).append("degreeSequenceNumber", this.degreeSequenceNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalPersonDegreeId rhs = (ProposalPersonDegreeId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.proposalPersonNumber, rhs.proposalPersonNumber).append(this.degreeSequenceNumber, rhs.degreeSequenceNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.proposalPersonNumber).append(this.degreeSequenceNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonDegreeId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.proposalPersonNumber, other.proposalPersonNumber).append(this.degreeSequenceNumber, other.degreeSequenceNumber).toComparison();
        }
    }
}
