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
package org.kuali.coeus.common.budget.framework.income;

import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import java.io.Serializable;

@Entity
@Table(name = "BUDGET_PROJECT_INCOME")
@IdClass(BudgetProjectIncome.BudgetProjectIncomeId.class)
public class BudgetProjectIncome extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, BudgetProjectIncomeContract {

    private static final long serialVersionUID = 8999969227018875501L;

    public static final String DOCUMENT_COMPONENT_ID_KEY = "BUDGET_PROJECT_INCOME_KEY";

    @Id
    @Column(name = "PROJECT_INCOME_ID")
    private Integer documentComponentId;

    @Id
    @Column(name = "BUDGET_ID", insertable = false, updatable = false)
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD_NUMBER", insertable = false, updatable = false)
    private Long budgetPeriodId;

    @ManyToOne
    @JoinColumns(value = {@JoinColumn(name = "BUDGET_PERIOD_NUMBER", referencedColumnName = "BUDGET_PERIOD_NUMBER"),
            @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")})
    private BudgetPeriod budgetPeriod;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriodNumber;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal projectIncome;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @Override
    public Integer getDocumentComponentId() {
        return documentComponentId;
    }

    public void setDocumentComponentId(Integer costShareId) {
        this.documentComponentId = costShareId;
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public Integer getBudgetPeriodNumber() {
        return budgetPeriodNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ScaleTwoDecimal getProjectIncome() {
        return projectIncome;
    }

    public void setBudgetPeriodNumber(Integer budgetPeriodNumber) {
        this.budgetPeriodNumber = (budgetPeriodNumber != null && budgetPeriodNumber.intValue() > 0) ? budgetPeriodNumber : null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectIncome(ScaleTwoDecimal income) {
        this.projectIncome = income;
    }

    public String getDocumentComponentIdKey() {
        return DOCUMENT_COMPONENT_ID_KEY;
    }

    @Override
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
        if (budgetPeriod != null) {
        	budgetPeriodId = budgetPeriod.getBudgetPeriodId();
        } else {
        	budgetPeriodId = null;
        }
    }

    @Override
    public BudgetPeriod getBudgetPeriod() {
        return budgetPeriod;
    }

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }
    @Override
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }
    @Override
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    public static final class BudgetProjectIncomeId implements Serializable, Comparable<BudgetProjectIncomeId> {

        private Integer documentComponentId;

        private Long budgetId;

        public Integer getDocumentComponentId() {
            return documentComponentId;
        }

        public void setDocumentComponentId(Integer documentComponentId) {
            this.documentComponentId = documentComponentId;
        }

        public Long getBudgetId() {
            return budgetId;
        }

        public void setBudgetId(Long budgetId) {
            this.budgetId = budgetId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("budgetId", this.budgetId).append("documentComponentId", this.documentComponentId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final BudgetProjectIncomeId rhs = (BudgetProjectIncomeId) other;
            return new EqualsBuilder().append(this.budgetId, rhs.budgetId).append(this.documentComponentId, rhs.documentComponentId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetId).append(this.documentComponentId).toHashCode();
        }

        @Override
        public int compareTo(BudgetProjectIncomeId other) {
            return new CompareToBuilder().append(this.budgetId, other.budgetId).append(this.documentComponentId, other.documentComponentId).toComparison();
        }
    }
}
