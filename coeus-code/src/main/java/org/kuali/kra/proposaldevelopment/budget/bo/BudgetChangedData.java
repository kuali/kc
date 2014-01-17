/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.budget.bo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetColumnsToAlter;

@Entity
@Table(name = "BUDGET_CHANGED_DATA")
@IdClass(BudgetChangedData.BudgetChangedDataId.class)
public class BudgetChangedData extends KraPersistableBusinessObjectBase {

    @Id
    @Column(name = "CHANGE_NUMBER")
    private Integer changeNumber;

    @Id
    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Transient
    private String attributeName;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "CHANGED_VALUE")
    private String changedValue;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "DISPLAY_VALUE")
    private String displayValue;

    @Column(name = "OLD_DISPLAY_VALUE")
    private String oldDisplayValue;

    @ManyToOne(targetEntity = BudgetColumnsToAlter.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "COLUMN_NAME", referencedColumnName = "COLUMN_NAME", insertable = false, updatable = false)
    private BudgetColumnsToAlter editableColumn;

    public BudgetChangedData() {
        super();
    }

    public Integer getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(Integer changeNumber) {
        this.changeNumber = changeNumber;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getChangedValue() {
        return changedValue;
    }

    public void setChangedValue(String changedValue) {
        this.changedValue = changedValue;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getOldDisplayValue() {
        return oldDisplayValue;
    }

    public void setOldDisplayValue(String oldDisplayValue) {
        this.oldDisplayValue = oldDisplayValue;
    }

    public BudgetColumnsToAlter getEditableColumn() {
        return editableColumn;
    }

    public void setEditableColumn(BudgetColumnsToAlter editableColumn) {
        this.editableColumn = editableColumn;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public static final class BudgetChangedDataId implements Serializable, Comparable<BudgetChangedDataId> {

        private String proposalNumber;

        private String columnName;

        private Integer changeNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public String getColumnName() {
            return this.columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Integer getChangeNumber() {
            return this.changeNumber;
        }

        public void setChangeNumber(Integer changeNumber) {
            this.changeNumber = changeNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("columnName", this.columnName).append("changeNumber", this.changeNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final BudgetChangedDataId rhs = (BudgetChangedDataId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.columnName, rhs.columnName).append(this.changeNumber, rhs.changeNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.columnName).append(this.changeNumber).toHashCode();
        }

        @Override
        public int compareTo(BudgetChangedDataId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.columnName, other.columnName).append(this.changeNumber, other.changeNumber).toComparison();
        }
    }
}
