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
package org.kuali.coeus.propdev.impl.budget.editable;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.propdev.api.budget.editable.BudgetChangedDataContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BUDGET_CHANGED_DATA")
@IdClass(BudgetChangedData.BudgetChangedDataId.class)
public class BudgetChangedData extends KcPersistableBusinessObjectBase implements BudgetChangedDataContract {

    @Id
    @Column(name = "CHANGE_NUMBER")
    private Integer changeNumber;

    @Id
    @Column(name = "COLUMN_NAME")
    private String columnName;

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

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "COLUMN_NAME", referencedColumnName = "COLUMN_NAME", insertable = false, updatable = false)
    private BudgetColumnsToAlter editableColumn;

    @Transient
    private String attributeName;

    @Override
    public Integer getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(Integer changeNumber) {
        this.changeNumber = changeNumber;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getChangedValue() {
        return changedValue;
    }

    public void setChangedValue(String changedValue) {
        this.changedValue = changedValue;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String getOldDisplayValue() {
        return oldDisplayValue;
    }

    public void setOldDisplayValue(String oldDisplayValue) {
        this.oldDisplayValue = oldDisplayValue;
    }

    @Override
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
