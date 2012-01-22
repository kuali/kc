/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.distributionincome;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;

/**
 * 
 */
public class BudgetCostShare extends BudgetDistributionAndIncomeComponent implements HierarchyMaintainable {
    private static final long serialVersionUID = 6199797319981907016L;

    public static final String DOCUMENT_COMPONENT_ID_KEY = "BUDGET_COST_SHARE_KEY";
    
    private Integer projectPeriod;
    private BudgetDecimal shareAmount;
    private BudgetDecimal sharePercentage;
    private String sourceAccount;
    private String sourceUnit;

    private String hierarchyProposalNumber;
    private boolean hiddenInHierarchy;

    /**
     * 
     * Constructs a BudgetCostShare.java.
     */
    public BudgetCostShare() {
        super();
    }
    
    /**
     * 
     * Constructs a BudgetCostShare.java.
     * @param projectPeriod
     * @param shareAmount
     * @param sharePercentage
     * @param sourceAccount
     */
    public BudgetCostShare(Integer projectPeriod, BudgetDecimal shareAmount, BudgetDecimal sharePercentage, String sourceAccount) {
        this();
        this.projectPeriod = projectPeriod;
        this.sharePercentage = sharePercentage;
        this.shareAmount = shareAmount;
        this.sourceAccount = sourceAccount;
    }

    /**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        }
        else if (!projectPeriod.equals(other.projectPeriod))
            return false;
        if (shareAmount == null) {
            if (other.shareAmount != null)
                return false;
        }
        else if (!shareAmount.equals(other.shareAmount))
            return false;
        if (sourceAccount == null) {
            if (other.sourceAccount != null)
                return false;
        }
        else if (!sourceAccount.equals(other.sourceAccount))
            return false;
        return true;
    }

    /**
     * 
     * @see org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeComponent#getDocumentComponentIdKey()
     */
    public String getDocumentComponentIdKey() {
        return DOCUMENT_COMPONENT_ID_KEY;
    }
    
    /**
     * 
     * This method...
     * @return
     */
    public Integer getProjectPeriod() {
        return projectPeriod;
    }

    /**
     * 
     * This method...
     * @return
     */
    public BudgetDecimal getShareAmount() {
         return BudgetDecimal.returnZeroIfNull(shareAmount);
    }

    /**
     * 
     * This method...
     * @return
     */
    public BudgetDecimal getSharePercentage() {
        return BudgetDecimal.returnZeroIfNull(sharePercentage);
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getSourceAccount() {
        return sourceAccount;
    }
    
    

    /**
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((projectPeriod == null) ? 0 : projectPeriod.hashCode());
        result = prime * result + ((shareAmount == null) ? 0 : shareAmount.hashCode());
        result = prime * result + ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
        return result;
    }

    /**
     * 
     * This method...
     * @param projectPeriod
     */
    public void setProjectPeriod(Integer projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    /**
     * 
     * This method...
     * @param shareAmount
     */
    public void setShareAmount(BudgetDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    /**
     * 
     * This method...
     * @param sharePercentage
     */
    public void setSharePercentage(BudgetDecimal sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    /**
     * 
     * This method...
     * @param sourceAcocunt
     */
    public void setSourceAccount(String sourceAcocunt) {
        this.sourceAccount = sourceAcocunt;
    }
    
    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
    }
 
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }
}
