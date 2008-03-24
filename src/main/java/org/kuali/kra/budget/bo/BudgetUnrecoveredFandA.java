/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
    
    public BudgetUnrecoveredFandA() {
        super();
    }

    public BudgetUnrecoveredFandA(Integer fiscalYear, BudgetDecimal amount, RateDecimal applicableRate, String campus, String sourceAccount) {
       super();
       this.fiscalYear = fiscalYear;
       this.amount = amount;
       this.applicableRate = applicableRate;
       this.onCampusFlag = campus;
       this.sourceAccount = sourceAccount;
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

    public BudgetDecimal getAmount() {
        return amount;
    }

    public RateDecimal getApplicableRate() {
        return applicableRate;
    }

    public String getOnCampusFlag() {
        return onCampusFlag;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

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

    public void setAmount(BudgetDecimal amount) {
        this.amount = amount;
    }

    public void setApplicableRate(RateDecimal applicableRate) {
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
}
