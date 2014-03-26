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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class AwardSubcontractingBudgetedGoals extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 5828522679101788693L;
    
    // the primary key for this BO
    private String awardNumber;
    
    private ScaleTwoDecimal largeBusinessGoalAmount;
    private ScaleTwoDecimal smallBusinessGoalAmount;
    private ScaleTwoDecimal womanOwnedGoalAmount;
    private ScaleTwoDecimal eightADisadvantageGoalAmount;
    private ScaleTwoDecimal hubZoneGoalAmount;
    private ScaleTwoDecimal veteranOwnedGoalAmount;
    private ScaleTwoDecimal serviceDisabledVeteranOwnedGoalAmount;
    private ScaleTwoDecimal historicalBlackCollegeGoalAmount;
    
    private String comments;
    
    // transient 'fresh' flag to indicate if this BO represents an existing record or freshly created
    private transient boolean fresh = false;
    
    public AwardSubcontractingBudgetedGoals() {
        super();
    }
    
    // constructor used by service to instantiate 'fresh' BOs i.e. those not previously stored in the db
    public AwardSubcontractingBudgetedGoals(String awardNumber) {
        this();
        // set the PK
        this.setAwardNumber(awardNumber);
        // set the transient fresh flag
        this.setFresh(true);
    }


    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }


    public String getAwardNumber() {
        return awardNumber;
    }




    public ScaleTwoDecimal getLargeBusinessGoalAmount() {
        return largeBusinessGoalAmount;
    }


    public void setLargeBusinessGoalAmount(ScaleTwoDecimal largeBusinessGoalAmount) {
        this.largeBusinessGoalAmount = largeBusinessGoalAmount;
    }


    public ScaleTwoDecimal getSmallBusinessGoalAmount() {
        return smallBusinessGoalAmount;
    }


    public void setSmallBusinessGoalAmount(ScaleTwoDecimal smallBusinessGoalAmount) {
        this.smallBusinessGoalAmount = smallBusinessGoalAmount;
    }


    public ScaleTwoDecimal getWomanOwnedGoalAmount() {
        return womanOwnedGoalAmount;
    }


    public void setWomanOwnedGoalAmount(ScaleTwoDecimal womanOwnedGoalAmount) {
        this.womanOwnedGoalAmount = womanOwnedGoalAmount;
    }


    public ScaleTwoDecimal getEightADisadvantageGoalAmount() {
        return eightADisadvantageGoalAmount;
    }


    public void setEightADisadvantageGoalAmount(ScaleTwoDecimal eightADisadvantageGoalAmount) {
        this.eightADisadvantageGoalAmount = eightADisadvantageGoalAmount;
    }


    public ScaleTwoDecimal getHubZoneGoalAmount() {
        return hubZoneGoalAmount;
    }


    public void setHubZoneGoalAmount(ScaleTwoDecimal hubZoneGoalAmount) {
        this.hubZoneGoalAmount = hubZoneGoalAmount;
    }


    public ScaleTwoDecimal getVeteranOwnedGoalAmount() {
        return veteranOwnedGoalAmount;
    }


    public void setVeteranOwnedGoalAmount(ScaleTwoDecimal veteranOwnedGoalAmount) {
        this.veteranOwnedGoalAmount = veteranOwnedGoalAmount;
    }


    public ScaleTwoDecimal getServiceDisabledVeteranOwnedGoalAmount() {
        return serviceDisabledVeteranOwnedGoalAmount;
    }


    public void setServiceDisabledVeteranOwnedGoalAmount(ScaleTwoDecimal serviceDisabledVeteranOwnedGoalAmount) {
        this.serviceDisabledVeteranOwnedGoalAmount = serviceDisabledVeteranOwnedGoalAmount;
    }


    public ScaleTwoDecimal getHistoricalBlackCollegeGoalAmount() {
        return historicalBlackCollegeGoalAmount;
    }


    public void setHistoricalBlackCollegeGoalAmount(ScaleTwoDecimal historicalBlackCollegeGoalAmount) {
        this.historicalBlackCollegeGoalAmount = historicalBlackCollegeGoalAmount;
    }
    
    /**
     * This method calculates the total amount for small and large business goal amounts
     * @return The total value
     */
    public ScaleTwoDecimal getTotalBusinessGoalAmount() {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        returnVal = returnVal.add(replaceNullWithZero(getSmallBusinessGoalAmount()));
        returnVal = returnVal.add(replaceNullWithZero(getLargeBusinessGoalAmount()));
        return returnVal;
    }


    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }  
    
    
    
    private ScaleTwoDecimal replaceNullWithZero(ScaleTwoDecimal amount) {
        ScaleTwoDecimal retVal = new ScaleTwoDecimal(0.00);
        if(amount != null) {
            retVal = amount;
        }
        return retVal;
    }

}
