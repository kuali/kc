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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.propdev.api.person.ProposalPersonDegreeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.framework.person.attr.DegreeType;

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
public class ProposalPersonDegree extends KcPersistableBusinessObjectBase implements ProposalPersonDegreeContract {

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalPerson proposalPerson;

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

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DEGREE_CODE", referencedColumnName = "DEGREE_CODE", insertable = false, updatable = false)
    private DegreeType degreeType;

    @Override
    public final String getGraduationYear() {
        return this.graduationYear;
    }

    public final void setGraduationYear(String argGraduationYear) {
        this.graduationYear = argGraduationYear;
    }

    public final String getDegreeCode() {
        return this.degreeCode;
    }

    public final void setDegreeCode(String argDegreeCode) {
        this.degreeCode = argDegreeCode;
        if (isBlank(degreeCode)) {
            degree = null;
        } else {
            refreshReferenceObject("degreeType");
        }
    }

    @Override
    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String argDegree) {
        this.degree = argDegree;
    }

    @Override
    public final String getFieldOfStudy() {
        return this.fieldOfStudy;
    }

    public final void setFieldOfStudy(String argFieldOfStudy) {
        this.fieldOfStudy = argFieldOfStudy;
    }

    @Override
    public final String getSpecialization() {
        return this.specialization;
    }

    public final void setSpecialization(String argSpecialization) {
        this.specialization = argSpecialization;
    }

    @Override
    public final String getSchool() {
        return this.school;
    }

    public final void setSchool(String argSchool) {
        this.school = argSchool;
    }

    @Override
    public final String getSchoolIdCode() {
        return this.schoolIdCode;
    }

    public final void setSchoolIdCode(String argSchoolIdCode) {
        this.schoolIdCode = argSchoolIdCode;
    }

    @Override
    public final String getSchoolId() {
        return this.schoolId;
    }

    public final void setSchoolId(String argSchoolId) {
        this.schoolId = argSchoolId;
    }

    public final void setDegreeType(DegreeType type) {
        degreeType = type;
    }

    @Override
    public final DegreeType getDegreeType() {
        return degreeType;
    }

    @Override
    public String getProposalNumber() {
        return getProposalPerson().getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public Integer getProposalPersonNumber() {
        return getProposalPerson().getProposalPersonNumber();
    }

    @Override
    public Integer getDegreeSequenceNumber() {
        return degreeSequenceNumber;
    }

    public void setDegreeSequenceNumber(Integer degreeSequenceNumber) {
        this.degreeSequenceNumber = degreeSequenceNumber;
    }

    public static final class ProposalPersonDegreeId implements Serializable, Comparable<ProposalPersonDegreeId> {

        private ProposalPerson.ProposalPersonId proposalPerson;

        private Integer degreeSequenceNumber;

        public ProposalPerson.ProposalPersonId getProposalPerson() {
			return proposalPerson;
		}

		public void setProposalPerson(ProposalPerson.ProposalPersonId proposalPerson) {
			this.proposalPerson = proposalPerson;
		}

        public Integer getDegreeSequenceNumber() {
            return this.degreeSequenceNumber;
        }

        public void setDegreeSequenceNumber(Integer degreeSequenceNumber) {
            this.degreeSequenceNumber = degreeSequenceNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPerson", this.proposalPerson).append("degreeSequenceNumber", this.degreeSequenceNumber).toString();
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
            return new EqualsBuilder().append(this.proposalPerson, rhs.proposalPerson).append(this.degreeSequenceNumber, rhs.degreeSequenceNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPerson).append(this.degreeSequenceNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonDegreeId other) {
            return new CompareToBuilder().append(this.proposalPerson, other.proposalPerson).append(this.degreeSequenceNumber, other.degreeSequenceNumber).toComparison();
        }
    }

	public ProposalPerson getProposalPerson() {
		return proposalPerson;
	}

	public void setProposalPerson(ProposalPerson proposalPerson) {
		this.proposalPerson = proposalPerson;
	}
}
