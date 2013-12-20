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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class AwardSubcontractingBudgetedGoals extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5828522679101788693L;
    
    // the primary key for this BO
    private String awardNumber;
    
    private KualiDecimal largeBusinessGoalAmount;
    private KualiDecimal smallBusinessGoalAmount; 
    private KualiDecimal womanOwnedGoalAmount;
    private KualiDecimal eightADisadvantageGoalAmount; 
    private KualiDecimal hubZoneGoalAmount;
    private KualiDecimal veteranOwnedGoalAmount; 
    private KualiDecimal serviceDisabledVeteranOwnedGoalAmount; 
    private KualiDecimal historicalBlackCollegeGoalAmount;
    
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




    public KualiDecimal getLargeBusinessGoalAmount() {
        return largeBusinessGoalAmount;
    }


    public void setLargeBusinessGoalAmount(KualiDecimal largeBusinessGoalAmount) {
        this.largeBusinessGoalAmount = largeBusinessGoalAmount;
    }


    public KualiDecimal getSmallBusinessGoalAmount() {
        return smallBusinessGoalAmount;
    }


    public void setSmallBusinessGoalAmount(KualiDecimal smallBusinessGoalAmount) {
        this.smallBusinessGoalAmount = smallBusinessGoalAmount;
    }


    public KualiDecimal getWomanOwnedGoalAmount() {
        return womanOwnedGoalAmount;
    }


    public void setWomanOwnedGoalAmount(KualiDecimal womanOwnedGoalAmount) {
        this.womanOwnedGoalAmount = womanOwnedGoalAmount;
    }


    public KualiDecimal getEightADisadvantageGoalAmount() {
        return eightADisadvantageGoalAmount;
    }


    public void setEightADisadvantageGoalAmount(KualiDecimal eightADisadvantageGoalAmount) {
        this.eightADisadvantageGoalAmount = eightADisadvantageGoalAmount;
    }


    public KualiDecimal getHubZoneGoalAmount() {
        return hubZoneGoalAmount;
    }


    public void setHubZoneGoalAmount(KualiDecimal hubZoneGoalAmount) {
        this.hubZoneGoalAmount = hubZoneGoalAmount;
    }


    public KualiDecimal getVeteranOwnedGoalAmount() {
        return veteranOwnedGoalAmount;
    }


    public void setVeteranOwnedGoalAmount(KualiDecimal veteranOwnedGoalAmount) {
        this.veteranOwnedGoalAmount = veteranOwnedGoalAmount;
    }


    public KualiDecimal getServiceDisabledVeteranOwnedGoalAmount() {
        return serviceDisabledVeteranOwnedGoalAmount;
    }


    public void setServiceDisabledVeteranOwnedGoalAmount(KualiDecimal serviceDisabledVeteranOwnedGoalAmount) {
        this.serviceDisabledVeteranOwnedGoalAmount = serviceDisabledVeteranOwnedGoalAmount;
    }


    public KualiDecimal getHistoricalBlackCollegeGoalAmount() {
        return historicalBlackCollegeGoalAmount;
    }


    public void setHistoricalBlackCollegeGoalAmount(KualiDecimal historicalBlackCollegeGoalAmount) {
        this.historicalBlackCollegeGoalAmount = historicalBlackCollegeGoalAmount;
    }
    
    /**
     * This method calculates the total amount for small and large business goal amounts
     * @return The total value
     */
    public KualiDecimal getTotalBusinessGoalAmount() {
        KualiDecimal returnVal = new KualiDecimal(0.00);
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
    
    
    
    private KualiDecimal replaceNullWithZero(KualiDecimal amount) {
        KualiDecimal retVal = new KualiDecimal(0.00);
        if(amount != null) {
            retVal = amount;
        }
        return retVal;
    }

}
