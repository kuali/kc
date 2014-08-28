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
package org.kuali.coeus.propdev.impl.budget.subaward;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardFilesContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

@Entity
@Table(name = "BUDGET_SUB_AWARD_FILES")
@IdClass(BudgetSubAwardFiles.BudgetSubAwardFilesId.class)
public class BudgetSubAwardFiles extends KcPersistableBusinessObjectBase implements BudgetSubAwardFilesContract {

    private static final long serialVersionUID = 9212512161341725983L;

    @Column(name = "SUB_AWARD_XFD_FILE")
    private byte[] subAwardXfdFileData;

    @Column(name = "SUB_AWARD_XFD_FILE_NAME")
    private String subAwardXfdFileName;

    @Column(name = "SUB_AWARD_XML_FILE")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String subAwardXmlFileData;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), @JoinColumn(name = "SUB_AWARD_NUMBER", referencedColumnName = "SUB_AWARD_NUMBER") })
    private BudgetSubAwards budgetSubAward;    

    /**
     * Gets the subAwardXfdFileData attribute. 
     * @return Returns the subAwardXfdFileData.
     */
    @Override
    public byte[] getSubAwardXfdFileData() {
        return subAwardXfdFileData;
    }

    /**
     * Sets the subAwardXfdFileData attribute value.
     * @param subAwardXfdFileData The subAwardXfdFileData to set.
     */
    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        this.subAwardXfdFileData = subAwardXfdFileData;
    }

    /**
     * Gets the subAwardXfdFileName attribute. 
     * @return Returns the subAwardXfdFileName.
     */
    @Override
    public String getSubAwardXfdFileName() {
        return subAwardXfdFileName;
    }

    /**
     * Sets the subAwardXfdFileName attribute value.
     * @param subAwardXfdFileName The subAwardXfdFileName to set.
     */
    public void setSubAwardXfdFileName(String subAwardXfdFileName) {
        this.subAwardXfdFileName = subAwardXfdFileName;
    }

    /**
     * Gets the subAwardXmlFileData attribute. 
     * @return Returns the subAwardXmlFileData.
     */
    @Override
    public String getSubAwardXmlFileData() {
        return subAwardXmlFileData;
    }

    /**
     * Sets the subAwardXmlFileData attribute value.
     * @param subAwardXmlFileData The subAwardXmlFileData to set.
     */
    public void setSubAwardXmlFileData(String subAwardXmlFileData) {
        this.subAwardXmlFileData = subAwardXmlFileData;
    }

	public BudgetSubAwards getBudgetSubAward() {
		return budgetSubAward;
	}

	public void setBudgetSubAward(BudgetSubAwards budgetSubAward) {
		this.budgetSubAward = budgetSubAward;
	}

	@Override
	public Integer getSubAwardNumber() {
		if (budgetSubAward != null) {
			return budgetSubAward.getSubAwardNumber();
		} else {
			return null;
		}
	}

	@Override
	public Long getBudgetId() {
		if (budgetSubAward != null) {
			return budgetSubAward.getBudgetId();
		} else {
			return null;
		}		
	}
	
    public static final class BudgetSubAwardFilesId implements Serializable, Comparable<BudgetSubAwardFilesId> {

        private BudgetSubAwards.BudgetSubAwardsId budgetSubAward;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("budgetSubAward", this.budgetSubAward).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final BudgetSubAwardFilesId rhs = (BudgetSubAwardFilesId) other;
            return new EqualsBuilder().append(this.budgetSubAward, rhs.budgetSubAward).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetSubAward).toHashCode();
        }

        @Override
        public int compareTo(BudgetSubAwardFilesId other) {
            return new CompareToBuilder().append(this.budgetSubAward, other.budgetSubAward).toComparison();
        }

		public BudgetSubAwards.BudgetSubAwardsId getBudgetSubAward() {
			return budgetSubAward;
		}

		public void setBudgetSubAward(BudgetSubAwards.BudgetSubAwardsId budgetSubAward) {
			this.budgetSubAward = budgetSubAward;
		}
    }	
}
