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
package org.kuali.coeus.common.budget.framework.distribution;

import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.budget.api.distribution.BudgetUnrecoveredFandAContract;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import java.io.Serializable;

@Entity
@Table(name = "EPS_PROP_IDC_RATE")
@IdClass(BudgetUnrecoveredFandA.BudgetUnrecoveredFandAId.class)
public class BudgetUnrecoveredFandA extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, BudgetUnrecoveredFandAContract {

    private static final long serialVersionUID = 6614520585838685080L;

    public static final String DOCUMENT_COMPONENT_ID_KEY = "BUDGET_UNRECOVERED_F_AND_A_KEY";

    public static final String OFF_CAMPUS_RATE_FLAG = "N";

    public static final String ON_CAMPUS_RATE_FLAG = "Y";

    @Id
    @Column(name = "UNRECOVERED_FNA_ID")
    private Integer documentComponentId;

    @Id
    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "UNDERRECOVERY_OF_IDC")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal amount;

    @Column(name = "APPLICABLE_IDC_RATE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal applicableRate;

    @Column(name = "ON_CAMPUS_FLAG")
    private String onCampusFlag;

    @Column(name = "FISCAL_YEAR")
    private Integer fiscalYear;

    @Column(name = "SOURCE_ACCOUNT")
    private String sourceAccount;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    public BudgetUnrecoveredFandA() {
        super();
    }

    /**
     * 
     * Constructs a BudgetUnrecoveredFandA.java.
     * @param fiscalYear
     * @param amount
     * @param applicableRate
     * @param campus
     * @param sourceAccount
     */
    public BudgetUnrecoveredFandA(Integer fiscalYear, ScaleTwoDecimal amount, ScaleTwoDecimal applicableRate, String campus, String sourceAccount) {
        super();
        this.fiscalYear = fiscalYear;
        this.amount = amount;
        this.applicableRate = applicableRate;
        this.onCampusFlag = campus;
        this.sourceAccount = sourceAccount;
    }

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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BudgetUnrecoveredFandA))
            return false;
        final BudgetUnrecoveredFandA other = (BudgetUnrecoveredFandA) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (applicableRate == null) {
            if (other.applicableRate != null)
                return false;
        } else if (!applicableRate.equals(other.applicableRate))
            return false;
        if (onCampusFlag == null) {
            if (other.onCampusFlag != null)
                return false;
        } else if (!onCampusFlag.equals(other.onCampusFlag))
            return false;
        if (fiscalYear == null) {
            if (other.fiscalYear != null)
                return false;
        } else if (!fiscalYear.equals(other.fiscalYear))
            return false;
        if (sourceAccount == null) {
            if (other.sourceAccount != null)
                return false;
        } else if (!sourceAccount.equals(other.sourceAccount))
            return false;
        return true;
    }
    @Override
    public ScaleTwoDecimal getAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(amount);
    }

    @Override
    public ScaleTwoDecimal getApplicableRate() {
        return applicableRate;
    }

    @Override
    public String getOnCampusFlag() {
        return onCampusFlag;
    }

    @Override
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    @Override
    public String getSourceAccount() {
        return sourceAccount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((applicableRate == null) ? 0 : applicableRate.hashCode());
        result = prime * result + ((onCampusFlag == null) ? 0 : onCampusFlag.hashCode());
        result = prime * result + ((fiscalYear == null) ? 0 : fiscalYear.hashCode());
        result = prime * result + ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
        return result;
    }

    public void setAmount(ScaleTwoDecimal amount) {
        this.amount = amount;
    }

    public void setApplicableRate(ScaleTwoDecimal applicableRate) {
        this.applicableRate = applicableRate;
    }

    public void setOnCampusFlag(String campus) {
        this.onCampusFlag = campus;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDocumentComponentIdKey() {
        return DOCUMENT_COMPONENT_ID_KEY;
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

    public static final class BudgetUnrecoveredFandAId implements Serializable, Comparable<BudgetUnrecoveredFandAId> {

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
            final BudgetUnrecoveredFandAId rhs = (BudgetUnrecoveredFandAId) other;
            return new EqualsBuilder().append(this.budgetId, rhs.budgetId).append(this.documentComponentId, rhs.documentComponentId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetId).append(this.documentComponentId).toHashCode();
        }

        @Override
        public int compareTo(BudgetUnrecoveredFandAId other) {
            return new CompareToBuilder().append(this.budgetId, other.budgetId).append(this.documentComponentId, other.documentComponentId).toComparison();
        }
    }
}
