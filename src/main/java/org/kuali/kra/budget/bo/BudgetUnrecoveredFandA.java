/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.bo;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;

public class BudgetUnrecoveredFandA extends BudgetDistributionAndIncomeComponent {
    private static final long serialVersionUID = 6614520585838685080L;

    public static final String DOCUMENT_COMPONENT_ID_KEY = "BUDGET_UNRECOVERED_F_AND_A_KEY";
    
    private BudgetDecimal amount;
    private RateDecimal applicableRate;
    private String onCampusFlag;
    private Integer fiscalYear;
    private String sourceAccount;
    
    public static final String OFF_CAMPUS_RATE_FLAG = "N"; 
    public static final String ON_CAMPUS_RATE_FLAG = "Y";
    
    /**
     * 
     * Constructs a BudgetUnrecoveredFandA.java.
     */
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
    public BudgetUnrecoveredFandA(Integer fiscalYear, BudgetDecimal amount, RateDecimal applicableRate, String campus, String sourceAccount) {
       super();
       this.fiscalYear = fiscalYear;
       this.amount = amount;
       this.applicableRate = applicableRate;
       this.onCampusFlag = campus;
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
        if (!(obj instanceof BudgetUnrecoveredFandA))
            return false;
        final BudgetUnrecoveredFandA other = (BudgetUnrecoveredFandA) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        }
        else if (!amount.equals(other.amount))
            return false;
        if (applicableRate == null) {
            if (other.applicableRate != null)
                return false;
        }
        else if (!applicableRate.equals(other.applicableRate))
            return false;
        if (onCampusFlag == null) {
            if (other.onCampusFlag != null)
                return false;
        }
        else if (!onCampusFlag.equals(other.onCampusFlag))
            return false;
        if (fiscalYear == null) {
            if (other.fiscalYear != null)
                return false;
        }
        else if (!fiscalYear.equals(other.fiscalYear))
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
     * This method...
     * @return
     */
    public BudgetDecimal getAmount() {
        return BudgetDecimal.returnZeroIfNull(amount);
    }

    /**
     * 
     * This method...
     * @return
     */
    public RateDecimal getApplicableRate() {
        return applicableRate;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getOnCampusFlag() {
        return onCampusFlag;
    }

    /**
     * 
     * This method...
     * @return
     */
    public Integer getFiscalYear() {
        return fiscalYear;
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
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((applicableRate == null) ? 0 : applicableRate.hashCode());
        result = prime * result + ((onCampusFlag == null) ? 0 : onCampusFlag.hashCode());
        result = prime * result + ((fiscalYear == null) ? 0 : fiscalYear.hashCode());
        result = prime * result + ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
        return result;
    }

    /**
     * 
     * This method...
     * @param amount
     */
    public void setAmount(BudgetDecimal amount) {
        this.amount = amount;
    }

    /**
     * 
     * This method...
     * @param applicableRate
     */
    public void setApplicableRate(RateDecimal applicableRate) {
        this.applicableRate = applicableRate;
    }

    /**
     * 
     * This method...
     * @param campus
     */
    public void setOnCampusFlag(String campus) {
        this.onCampusFlag = campus;
    }

    /**
     * 
     * This method...
     * @param fiscalYear
     */
    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * 
     * This method...
     * @param sourceAccount
     */
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    /**
     * 
     * @see org.kuali.kra.budget.bo.BudgetDistributionAndIncomeComponent#getDocumentComponentIdKey()
     */
    @Override
    public String getDocumentComponentIdKey() {
        return DOCUMENT_COMPONENT_ID_KEY;
    }
}
