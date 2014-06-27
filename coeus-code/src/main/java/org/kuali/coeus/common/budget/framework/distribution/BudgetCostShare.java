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
import org.kuali.coeus.common.budget.api.distribution.BudgetCostShareContract;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import java.io.Serializable;

@Entity
@Table(name = "EPS_PROP_COST_SHARING")
@IdClass(BudgetCostShare.BudgetCostShareId.class)
public class BudgetCostShare extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, BudgetCostShareContract {

    private static final long serialVersionUID = 6199797319981907016L;

    public static final String DOCUMENT_COMPONENT_ID_KEY = "BUDGET_COST_SHARE_KEY";

    @Id
    @Column(name = "COST_SHARE_ID")
    private Integer documentComponentId;

    @Id
    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "PROJECT_PERIOD")
    private Integer projectPeriod;

    @Column(name = "AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal shareAmount;

    @Column(name = "COST_SHARING_PERCENTAGE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal sharePercentage;

    @Column(name = "SOURCE_ACCOUNT")
    private String sourceAccount;

    @Transient
    private String sourceUnit;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    public BudgetCostShare() {
        super();
    }

    public BudgetCostShare(Integer projectPeriod, ScaleTwoDecimal shareAmount, ScaleTwoDecimal sharePercentage, String sourceAccount) {
        this();
        this.projectPeriod = projectPeriod;
        this.sharePercentage = sharePercentage;
        this.shareAmount = shareAmount;
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
        if (!(obj instanceof BudgetCostShare))
            return false;
        final BudgetCostShare other = (BudgetCostShare) obj;
        if (projectPeriod == null) {
            if (other.projectPeriod != null)
                return false;
        } else if (!projectPeriod.equals(other.projectPeriod))
            return false;
        if (shareAmount == null) {
            if (other.shareAmount != null)
                return false;
        } else if (!shareAmount.equals(other.shareAmount))
            return false;
        if (sourceAccount == null) {
            if (other.sourceAccount != null)
                return false;
        } else if (!sourceAccount.equals(other.sourceAccount))
            return false;
        return true;
    }

    public String getDocumentComponentIdKey() {
        return DOCUMENT_COMPONENT_ID_KEY;
    }

    @Override
    public Integer getProjectPeriod() {
        return projectPeriod;
    }

    @Override
    public ScaleTwoDecimal getShareAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(shareAmount);
    }

    @Override
    public ScaleTwoDecimal getSharePercentage() {
        return ScaleTwoDecimal.returnZeroIfNull(sharePercentage);
    }

    @Override
    public String getSourceAccount() {
        return sourceAccount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((projectPeriod == null) ? 0 : projectPeriod.hashCode());
        result = prime * result + ((shareAmount == null) ? 0 : shareAmount.hashCode());
        result = prime * result + ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
        return result;
    }

    public void setProjectPeriod(Integer projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    public void setShareAmount(ScaleTwoDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    public void setSharePercentage(ScaleTwoDecimal sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public void setSourceAccount(String sourceAcocunt) {
        this.sourceAccount = sourceAcocunt;
    }

    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
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

    public static final class BudgetCostShareId implements Serializable, Comparable<BudgetCostShareId> {

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
            final BudgetCostShareId rhs = (BudgetCostShareId) other;
            return new EqualsBuilder().append(this.budgetId, rhs.budgetId).append(this.documentComponentId, rhs.documentComponentId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetId).append(this.documentComponentId).toHashCode();
        }

        @Override
        public int compareTo(BudgetCostShareId other) {
            return new CompareToBuilder().append(this.budgetId, other.budgetId).append(this.documentComponentId, other.documentComponentId).toComparison();
        }
    }
}
