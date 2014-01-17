/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@Entity
@Table(name = "EPS_PROP_SCIENCE_KEYWORD")
@IdClass(PropScienceKeyword.PropScienceKeywordId.class)
public class PropScienceKeyword extends KraPersistableBusinessObjectBase implements HierarchyMaintainable {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "SCIENCE_KEYWORD_CODE")
    private String scienceKeywordCode;

    @ManyToOne(targetEntity = ScienceKeyword.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SCIENCE_KEYWORD_CODE", referencedColumnName = "SCIENCE_KEYWORD_CODE", insertable = false, updatable = false)
    private ScienceKeyword scienceKeyword;

    @Transient
    private Boolean selectKeyword = false;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    /**
     * Default constructor.
     */
    public PropScienceKeyword() {
    }

    /**
     * Constructs a PropScienceKeyword.
     * @param proposalNumber
     * @param scienceKeyword
     */
    public PropScienceKeyword(String proposalNumber, ScienceKeyword scienceKeyword) {
        this.proposalNumber = proposalNumber;

        this.scienceKeywordCode = scienceKeyword.getScienceKeywordCode();
        this.scienceKeyword = scienceKeyword;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getScienceKeywordCode() {
        return scienceKeywordCode;
    }

    public void setScienceKeywordCode(String scienceKeywordCode) {
        this.scienceKeywordCode = scienceKeywordCode;
    }

    public ScienceKeyword getScienceKeyword() {
        return scienceKeyword;
    }

    public void setScienceKeyword(ScienceKeyword scienceKeyword) {
        this.scienceKeyword = scienceKeyword;
    }

    public Boolean getSelectKeyword() {
        return selectKeyword;
    }

    public void setSelectKeyword(Boolean selectKeyword) {
        this.selectKeyword = selectKeyword;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((proposalNumber == null) ? 0 : proposalNumber.hashCode());
        result = prime * result + ((scienceKeywordCode == null) ? 0 : scienceKeywordCode.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PropScienceKeyword other = (PropScienceKeyword) obj;
        if (proposalNumber == null) {
            if (other.proposalNumber != null)
                return false;
        } else if (!proposalNumber.equals(other.proposalNumber))
            return false;
        if (scienceKeywordCode == null) {
            if (other.scienceKeywordCode != null)
                return false;
        } else if (!scienceKeywordCode.equals(other.scienceKeywordCode))
            return false;
        return true;
    }

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    public static final class PropScienceKeywordId implements Serializable, Comparable<PropScienceKeywordId> {

        private String proposalNumber;

        private String scienceKeywordCode;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public String getScienceKeywordCode() {
            return this.scienceKeywordCode;
        }

        public void setScienceKeywordCode(String scienceKeywordCode) {
            this.scienceKeywordCode = scienceKeywordCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("scienceKeywordCode", this.scienceKeywordCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final PropScienceKeywordId rhs = (PropScienceKeywordId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.scienceKeywordCode, rhs.scienceKeywordCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.scienceKeywordCode).toHashCode();
        }

        @Override
        public int compareTo(PropScienceKeywordId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.scienceKeywordCode, other.scienceKeywordCode).toComparison();
        }
    }
}
