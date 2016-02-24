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
