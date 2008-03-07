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

import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;

public class BudgetCostSharing extends BudgetDistributionAndIncomeComponent {
    private static final long serialVersionUID = 1L;
    
    private KualiDecimal sharePercentage;
    private Integer fiscalYear;
    private KualiDecimal shareAmount;
    private String sourceAcocunt;

    public KualiDecimal getSharePercentage() {
        return sharePercentage;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public KualiDecimal getShareAmount() {
        return shareAmount;
    }

    public String getSourceAcocunt() {
        return sourceAcocunt;
    }

    public void setSharePercentage(KualiDecimal sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public void setShareAmount(KualiDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    public void setSourceAcocunt(String sourceAcocunt) {
        this.sourceAcocunt = sourceAcocunt;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("sharePercentage", getSharePercentage());
        hashMap.put("fiscalYear", getFiscalYear());
        hashMap.put("shareAmount", getShareAmount());
        hashMap.put("sourceAcocunt", getSourceAcocunt());
        
        return hashMap;
    }
}
