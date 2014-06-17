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

import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardFilesContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.io.Serializable;

@Entity
@Table(name = "BUDGET_SUB_AWARD_FILES")
@IdClass(BudgetSubAwardFiles.BudgetSubAwardFilesId.class)
public class BudgetSubAwardFiles extends KcPersistableBusinessObjectBase implements BudgetSubAwardFilesContract {

    private static final long serialVersionUID = 9212512161341725983L;

    @Column(name = "BUDGET_ID")
    @Id
    private Long budgetId;

    @Id
    @Column(name = "SUB_AWARD_NUMBER")
    private Integer subAwardNumber;

    @Column(name = "SUB_AWARD_XFD_FILE")
    private byte[] subAwardXfdFileData;

    @Column(name = "SUB_AWARD_XFD_FILE_NAME")
    private String subAwardXfdFileName;

    @Column(name = "SUB_AWARD_XML_FILE")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String subAwardXmlFileData;

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    /**
     * Gets the subAwardNumber attribute. 
     * @return Returns the subAwardNumber.
     */
    @Override
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    /**
     * Sets the subAwardNumber attribute value.
     * @param subAwardNumber The subAwardNumber to set.
     */
    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

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

    public static final class BudgetSubAwardFilesId implements Serializable, Comparable<BudgetSubAwardFilesId> {

        private Integer subAwardNumber;

        private Long budgetId;

        public Integer getSubAwardNumber() {
            return subAwardNumber;
        }

        public void setSubAwardNumber(Integer subAwardNumber) {
            this.subAwardNumber = subAwardNumber;
        }

        public Long getBudgetId() {
            return budgetId;
        }

        public void setBudgetId(Long budgetId) {
            this.budgetId = budgetId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("budgetId", this.budgetId).append("subAwardNumber", this.subAwardNumber).toString();
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
            return new EqualsBuilder().append(this.budgetId, rhs.budgetId).append(this.subAwardNumber, rhs.subAwardNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetId).append(this.subAwardNumber).toHashCode();
        }

        @Override
        public int compareTo(BudgetSubAwardFilesId other) {
            return new CompareToBuilder().append(this.budgetId, other.budgetId).append(this.subAwardNumber, other.subAwardNumber).toComparison();
        }
    }
}
