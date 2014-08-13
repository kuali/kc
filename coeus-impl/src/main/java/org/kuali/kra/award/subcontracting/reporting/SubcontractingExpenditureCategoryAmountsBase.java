/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public abstract class SubcontractingExpenditureCategoryAmountsBase extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 8538626710923020370L;

    // the primary key field
    private String awardNumber;
    
    private ScaleTwoDecimal largeBusinessExpenditureAmount;
    private ScaleTwoDecimal smallBusinessExpenditureAmount;
    private ScaleTwoDecimal womanOwnedExpenditureAmount;
    private ScaleTwoDecimal eightADisadvantageExpenditureAmount;
    private ScaleTwoDecimal hubZoneExpenditureAmount;
    private ScaleTwoDecimal veteranOwnedExpenditureAmount;
    private ScaleTwoDecimal serviceDisabledVeteranOwnedExpenditureAmount;
    private ScaleTwoDecimal historicalBlackCollegeExpenditureAmount;
    
    public SubcontractingExpenditureCategoryAmountsBase() {
        // initialize all amounts to zero to avoid NPEs
        this.largeBusinessExpenditureAmount = new ScaleTwoDecimal(0.00);
        this.smallBusinessExpenditureAmount = new ScaleTwoDecimal(0.00);
        this.womanOwnedExpenditureAmount = new ScaleTwoDecimal(0.00);
        this.setEightADisadvantageExpenditureAmount(new ScaleTwoDecimal(0.00));
        this.hubZoneExpenditureAmount = new ScaleTwoDecimal(0.00);
        this.veteranOwnedExpenditureAmount = new ScaleTwoDecimal(0.00);
        this.serviceDisabledVeteranOwnedExpenditureAmount = new ScaleTwoDecimal(0.00);
        this.historicalBlackCollegeExpenditureAmount = new ScaleTwoDecimal(0.00);
    }


    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    

    public ScaleTwoDecimal getLargeBusinessExpenditureAmount() {
        return largeBusinessExpenditureAmount;
    }

    public void setLargeBusinessExpenditureAmount(ScaleTwoDecimal largeBusinessExpenditureAmount) {
        this.largeBusinessExpenditureAmount = largeBusinessExpenditureAmount;
    }

    public void addToLargeBusinessExpenditureAmount(ScaleTwoDecimal amount) {
        this.largeBusinessExpenditureAmount = largeBusinessExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public ScaleTwoDecimal getSmallBusinessExpenditureAmount() {
        return smallBusinessExpenditureAmount;
    }

    public void setSmallBusinessExpenditureAmount(ScaleTwoDecimal smallBusinessExpenditureAmount) {
        this.smallBusinessExpenditureAmount = smallBusinessExpenditureAmount;
    }

    public void addToSmallBusinessExpenditureAmount(ScaleTwoDecimal amount) {
        this.smallBusinessExpenditureAmount = smallBusinessExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public ScaleTwoDecimal getWomanOwnedExpenditureAmount() {
        return womanOwnedExpenditureAmount;
    }

    public void setWomanOwnedExpenditureAmount(ScaleTwoDecimal womanOwnedExpenditureAmount) {
        this.womanOwnedExpenditureAmount = womanOwnedExpenditureAmount;
    }
    
    public void addToWomanOwnedExpenditureAmount(ScaleTwoDecimal amount) {
        this.womanOwnedExpenditureAmount = womanOwnedExpenditureAmount.add(replaceNullWithZero(amount));
    }
    
    
    
    public ScaleTwoDecimal getEightADisadvantageExpenditureAmount() {
        return eightADisadvantageExpenditureAmount;
    }
    
    public void setEightADisadvantageExpenditureAmount(ScaleTwoDecimal eightADisadvantageExpenditureAmount) {
        this.eightADisadvantageExpenditureAmount = eightADisadvantageExpenditureAmount;
    }
    
    public void addToEightADisadvantageExpenditureAmount(ScaleTwoDecimal amount) {
        this.eightADisadvantageExpenditureAmount = eightADisadvantageExpenditureAmount.add(replaceNullWithZero(amount));
    }
   
    


    public ScaleTwoDecimal getHubZoneExpenditureAmount() {
        return hubZoneExpenditureAmount;
    }

    public void setHubZoneExpenditureAmount(ScaleTwoDecimal hubZoneExpenditureAmount) {
        this.hubZoneExpenditureAmount = hubZoneExpenditureAmount;
    }

    public void addToHubZoneExpenditureAmount(ScaleTwoDecimal amount) {
        this.hubZoneExpenditureAmount = hubZoneExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public ScaleTwoDecimal getVeteranOwnedExpenditureAmount() {
        return veteranOwnedExpenditureAmount;
    }

    public void setVeteranOwnedExpenditureAmount(ScaleTwoDecimal veteranOwnedExpenditureAmount) {
        this.veteranOwnedExpenditureAmount = veteranOwnedExpenditureAmount;
    }

    public void addToVeteranOwnedExpenditureAmount(ScaleTwoDecimal amount) {
        this.veteranOwnedExpenditureAmount = veteranOwnedExpenditureAmount.add(replaceNullWithZero(amount));
    }

    
    
    public ScaleTwoDecimal getServiceDisabledVeteranOwnedExpenditureAmount() {
        return serviceDisabledVeteranOwnedExpenditureAmount;
    }

    public void setServiceDisabledVeteranOwnedExpenditureAmount(ScaleTwoDecimal serviceDisabledVeteranOwnedExpenditureAmount) {
        this.serviceDisabledVeteranOwnedExpenditureAmount = serviceDisabledVeteranOwnedExpenditureAmount;
    }

    public void addToServiceDisabledVeteranOwnedExpenditureAmount(ScaleTwoDecimal amount) {
        this.serviceDisabledVeteranOwnedExpenditureAmount = serviceDisabledVeteranOwnedExpenditureAmount.add(replaceNullWithZero(amount));
    }
    

    
    public ScaleTwoDecimal getHistoricalBlackCollegeExpenditureAmount() {
        return historicalBlackCollegeExpenditureAmount;
    }

    public void setHistoricalBlackCollegeExpenditureAmount(ScaleTwoDecimal historicalBlackCollegeExpenditureAmount) {
        this.historicalBlackCollegeExpenditureAmount = historicalBlackCollegeExpenditureAmount;
    }

    public void addToHistoricalBlackCollegeExpenditureAmount(ScaleTwoDecimal amount) {
        this.historicalBlackCollegeExpenditureAmount = historicalBlackCollegeExpenditureAmount.add(replaceNullWithZero(amount));
    }
    
    
    /**
     * This method calculates the total amount for small and large business expenditure amounts
     * @return The total value
     */
    public ScaleTwoDecimal getTotalBusinessExpenditureAmount() {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        returnVal = returnVal.add(replaceNullWithZero(getSmallBusinessExpenditureAmount()));
        returnVal = returnVal.add(replaceNullWithZero(getLargeBusinessExpenditureAmount()));
        return returnVal;
    }

    

    private ScaleTwoDecimal replaceNullWithZero(ScaleTwoDecimal amount) {
        ScaleTwoDecimal retVal = new ScaleTwoDecimal(0.00);
        if(amount != null) {
            retVal = amount;
        }
        return retVal;
    }

    
}
