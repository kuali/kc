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
package org.kuali.kra.award.subcontracting.reporting;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public abstract class SubcontractingExpenditureCategoryAmountsBase extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8538626710923020370L;

    // the primary key field
    private String awardNumber;
    
    private KualiDecimal largeBusinessExpenditureAmount;
    private KualiDecimal smallBusinessExpenditureAmount;
    private KualiDecimal womanOwnedExpenditureAmount;
    private KualiDecimal eightADisadvantageExpenditureAmount; 
    private KualiDecimal hubZoneExpenditureAmount;
    private KualiDecimal veteranOwnedExpenditureAmount; 
    private KualiDecimal serviceDisabledVeteranOwnedExpenditureAmount; 
    private KualiDecimal historicalBlackCollegeExpenditureAmount;
    
    public SubcontractingExpenditureCategoryAmountsBase() {
        // initialize all amounts to zero to avoid NPEs
        this.largeBusinessExpenditureAmount = new KualiDecimal(0.00);
        this.smallBusinessExpenditureAmount = new KualiDecimal(0.00);
        this.womanOwnedExpenditureAmount = new KualiDecimal(0.00);
        this.setEightADisadvantageExpenditureAmount(new KualiDecimal(0.00));
        this.hubZoneExpenditureAmount = new KualiDecimal(0.00);
        this.veteranOwnedExpenditureAmount = new KualiDecimal(0.00);
        this.serviceDisabledVeteranOwnedExpenditureAmount = new KualiDecimal(0.00);
        this.historicalBlackCollegeExpenditureAmount = new KualiDecimal(0.00);
    }


    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    

    public KualiDecimal getLargeBusinessExpenditureAmount() {
        return largeBusinessExpenditureAmount;
    }

    public void setLargeBusinessExpenditureAmount(KualiDecimal largeBusinessExpenditureAmount) {
        this.largeBusinessExpenditureAmount = largeBusinessExpenditureAmount;
    }

    public void addToLargeBusinessExpenditureAmount(KualiDecimal amount) {
        this.largeBusinessExpenditureAmount = largeBusinessExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public KualiDecimal getSmallBusinessExpenditureAmount() {
        return smallBusinessExpenditureAmount;
    }

    public void setSmallBusinessExpenditureAmount(KualiDecimal smallBusinessExpenditureAmount) {
        this.smallBusinessExpenditureAmount = smallBusinessExpenditureAmount;
    }

    public void addToSmallBusinessExpenditureAmount(KualiDecimal amount) {
        this.smallBusinessExpenditureAmount = smallBusinessExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public KualiDecimal getWomanOwnedExpenditureAmount() {
        return womanOwnedExpenditureAmount;
    }

    public void setWomanOwnedExpenditureAmount(KualiDecimal womanOwnedExpenditureAmount) {
        this.womanOwnedExpenditureAmount = womanOwnedExpenditureAmount;
    }
    
    public void addToWomanOwnedExpenditureAmount(KualiDecimal amount) {
        this.womanOwnedExpenditureAmount = womanOwnedExpenditureAmount.add(replaceNullWithZero(amount));
    }
    
    
    
    public KualiDecimal getEightADisadvantageExpenditureAmount() {
        return eightADisadvantageExpenditureAmount;
    }
    
    public void setEightADisadvantageExpenditureAmount(KualiDecimal eightADisadvantageExpenditureAmount) {
        this.eightADisadvantageExpenditureAmount = eightADisadvantageExpenditureAmount;
    }
    
    public void addToEightADisadvantageExpenditureAmount(KualiDecimal amount) {
        this.eightADisadvantageExpenditureAmount = eightADisadvantageExpenditureAmount.add(replaceNullWithZero(amount));
    }
   
    


    public KualiDecimal getHubZoneExpenditureAmount() {
        return hubZoneExpenditureAmount;
    }

    public void setHubZoneExpenditureAmount(KualiDecimal hubZoneExpenditureAmount) {
        this.hubZoneExpenditureAmount = hubZoneExpenditureAmount;
    }

    public void addToHubZoneExpenditureAmount(KualiDecimal amount) {
        this.hubZoneExpenditureAmount = hubZoneExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public KualiDecimal getVeteranOwnedExpenditureAmount() {
        return veteranOwnedExpenditureAmount;
    }

    public void setVeteranOwnedExpenditureAmount(KualiDecimal veteranOwnedExpenditureAmount) {
        this.veteranOwnedExpenditureAmount = veteranOwnedExpenditureAmount;
    }

    public void addToVeteranOwnedExpenditureAmount(KualiDecimal amount) {
        this.veteranOwnedExpenditureAmount = veteranOwnedExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public KualiDecimal getServiceDisabledVeteranOwnedExpenditureAmount() {
        return serviceDisabledVeteranOwnedExpenditureAmount;
    }

    public void setServiceDisabledVeteranOwnedExpenditureAmount(KualiDecimal serviceDisabledVeteranOwnedExpenditureAmount) {
        this.serviceDisabledVeteranOwnedExpenditureAmount = serviceDisabledVeteranOwnedExpenditureAmount;
    }

    public void addToServiceDisabledVeteranOwnedExpenditureAmount(KualiDecimal amount) {
        this.serviceDisabledVeteranOwnedExpenditureAmount = serviceDisabledVeteranOwnedExpenditureAmount.add(replaceNullWithZero(amount));
    }
    

    
    public KualiDecimal getHistoricalBlackCollegeExpenditureAmount() {
        return historicalBlackCollegeExpenditureAmount;
    }

    public void setHistoricalBlackCollegeExpenditureAmount(KualiDecimal historicalBlackCollegeExpenditureAmount) {
        this.historicalBlackCollegeExpenditureAmount = historicalBlackCollegeExpenditureAmount;
    }

    public void addToHistoricalBlackCollegeExpenditureAmount(KualiDecimal amount) {
        this.historicalBlackCollegeExpenditureAmount = historicalBlackCollegeExpenditureAmount.add(replaceNullWithZero(amount));
    }
    
    
    /**
     * This method calculates the total amount for small and large business expenditure amounts
     * @return The total value
     */
    public KualiDecimal getTotalBusinessExpenditureAmount() {
        KualiDecimal returnVal = new KualiDecimal(0.00);
        returnVal = returnVal.add(replaceNullWithZero(getSmallBusinessExpenditureAmount()));
        returnVal = returnVal.add(replaceNullWithZero(getLargeBusinessExpenditureAmount()));
        return returnVal;
    }

    

    private KualiDecimal replaceNullWithZero(KualiDecimal amount) {
        KualiDecimal retVal = new KualiDecimal(0.00);
        if(amount != null) {
            retVal = amount;
        }
        return retVal;
    }

    
}
