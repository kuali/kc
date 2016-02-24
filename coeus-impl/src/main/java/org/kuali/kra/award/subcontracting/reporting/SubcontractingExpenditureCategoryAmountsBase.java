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
