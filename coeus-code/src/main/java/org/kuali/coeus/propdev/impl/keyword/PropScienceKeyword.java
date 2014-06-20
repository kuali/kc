/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.keyword;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.propdev.api.keyword.PropScienceKeywordContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "EPS_PROP_SCIENCE_KEYWORD")
@IdClass(PropScienceKeyword.PropScienceKeywordId.class)
public class PropScienceKeyword extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, PropScienceKeywordContract {

	@Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", insertable = true, updatable = true)
    private DevelopmentProposal developmentProposal;

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SCIENCE_KEYWORD_CODE", insertable = true, updatable = true)
    private ScienceKeyword scienceKeyword;

    @Transient
    private Boolean selectKeyword = false;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    public PropScienceKeyword() { }
    
    public PropScienceKeyword(DevelopmentProposal developmentProposal2, ScienceKeyword scienceKeyword) {
        this.developmentProposal = developmentProposal2;
        this.scienceKeyword = scienceKeyword;
    }

    @Override
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

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    @Override
    public String getProposalNumber() {
        return getDevelopmentProposal().getProposalNumber();
    }

    public static final class PropScienceKeywordId implements Serializable, Comparable<PropScienceKeywordId> {

        private String developmentProposal;

        private String scienceKeyword;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("developmentProposal", this.developmentProposal).append("scienceKeyword", this.scienceKeyword).toString();
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
            return new EqualsBuilder().append(this.developmentProposal, rhs.developmentProposal).append(this.scienceKeyword, rhs.scienceKeyword).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.developmentProposal).append(this.scienceKeyword).toHashCode();
        }

        @Override
        public int compareTo(PropScienceKeywordId other) {
            return new CompareToBuilder().append(this.developmentProposal, other.developmentProposal).append(this.scienceKeyword, other.scienceKeyword).toComparison();
        }

		public String getDevelopmentProposal() {
			return developmentProposal;
		}

		public void setDevelopmentProposal(String developmentProposal) {
			this.developmentProposal = developmentProposal;
		}

		public String getScienceKeyword() {
			return scienceKeyword;
		}

		public void setScienceKeyword(String scienceKeyword) {
			this.scienceKeyword = scienceKeyword;
		}
    }

	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}
	
    @Override
    /**
     * current workaround to fix KRAD/Spring selecting to multiselect options for the Many-To-Many relationship.
     */
    public String toString() {
        if (scienceKeyword != null) { 
        	return scienceKeyword.getCode();
        } else {
        	return super.toString();
        }
    }
}
