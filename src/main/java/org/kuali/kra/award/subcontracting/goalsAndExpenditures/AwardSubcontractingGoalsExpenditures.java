/*
 * Copyright 2005-2010 The Kuali Foundation
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

public class AwardSubcontractingGoalsExpenditures extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5828522679101788693L;
    
    // the primary key for this BO
    private String awardNumber;
    
    private KualiDecimal largeBusinessExpenditureAmount;
    private KualiDecimal smallBusinessExpenditureAmount;
    private KualiDecimal womanOwnedExpenditureAmount;
    private KualiDecimal a8DisadvantageExpenditureAmount; 
    private KualiDecimal hubZoneExpenditureAmount;
    private KualiDecimal veteranOwnedExpenditureAmount; 
    private KualiDecimal serviceDisabledVeteranOwnedExpenditureAmount; 
    private KualiDecimal historicalBlackCollegeExpenditureAmount;

    private KualiDecimal largeBusinessGoalAmount;
    private KualiDecimal smallBusinessGoalAmount; 
    private KualiDecimal womanOwnedGoalAmount;
    private KualiDecimal a8DisadvantageGoalAmount; 
    private KualiDecimal hubZoneGoalAmount;
    private KualiDecimal veteranOwnedGoalAmount; 
    private KualiDecimal serviceDisabledVeteranOwnedGoalAmount; 
    private KualiDecimal historicalBlackCollegeGoalAmount;
    
    private String comments;
    
    // transient 'fresh' flag to indicate if this BO represents an existing record or freshly created
    private transient boolean fresh = false;
    
    public AwardSubcontractingGoalsExpenditures() {
        super();
    }
    
    // constructor used by service to instantiate 'fresh' BOs i.e. those not previously stored in the db
    public AwardSubcontractingGoalsExpenditures(String awardNumber) {
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


    public KualiDecimal getLargeBusinessExpenditureAmount() {
        return largeBusinessExpenditureAmount;
    }


    public void setLargeBusinessExpenditureAmount(KualiDecimal largeBusinessExpenditureAmount) {
        this.largeBusinessExpenditureAmount = largeBusinessExpenditureAmount;
    }


    public KualiDecimal getSmallBusinessExpenditureAmount() {
        return smallBusinessExpenditureAmount;
    }


    public void setSmallBusinessExpenditureAmount(KualiDecimal smallBusinessExpenditureAmount) {
        this.smallBusinessExpenditureAmount = smallBusinessExpenditureAmount;
    }


    public KualiDecimal getWomanOwnedExpenditureAmount() {
        return womanOwnedExpenditureAmount;
    }


    public void setWomanOwnedExpenditureAmount(KualiDecimal womanOwnedExpenditureAmount) {
        this.womanOwnedExpenditureAmount = womanOwnedExpenditureAmount;
    }


    public KualiDecimal getA8DisadvantageExpenditureAmount() {
        return a8DisadvantageExpenditureAmount;
    }


    public void setA8DisadvantageExpenditureAmount(KualiDecimal a8DisadvantageExpenditureAmount) {
        this.a8DisadvantageExpenditureAmount = a8DisadvantageExpenditureAmount;
    }


    public KualiDecimal getHubZoneExpenditureAmount() {
        return hubZoneExpenditureAmount;
    }


    public void setHubZoneExpenditureAmount(KualiDecimal hubZoneExpenditureAmount) {
        this.hubZoneExpenditureAmount = hubZoneExpenditureAmount;
    }


    public KualiDecimal getVeteranOwnedExpenditureAmount() {
        return veteranOwnedExpenditureAmount;
    }


    public void setVeteranOwnedExpenditureAmount(KualiDecimal veteranOwnedExpenditureAmount) {
        this.veteranOwnedExpenditureAmount = veteranOwnedExpenditureAmount;
    }


    public KualiDecimal getServiceDisabledVeteranOwnedExpenditureAmount() {
        return serviceDisabledVeteranOwnedExpenditureAmount;
    }


    public void setServiceDisabledVeteranOwnedExpenditureAmount(KualiDecimal serviceDisabledVeteranOwnedExpenditureAmount) {
        this.serviceDisabledVeteranOwnedExpenditureAmount = serviceDisabledVeteranOwnedExpenditureAmount;
    }


    public KualiDecimal getHistoricalBlackCollegeExpenditureAmount() {
        return historicalBlackCollegeExpenditureAmount;
    }


    public void setHistoricalBlackCollegeExpenditureAmount(KualiDecimal historicalBlackCollegeExpenditureAmount) {
        this.historicalBlackCollegeExpenditureAmount = historicalBlackCollegeExpenditureAmount;
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


    public KualiDecimal getA8DisadvantageGoalAmount() {
        return a8DisadvantageGoalAmount;
    }


    public void setA8DisadvantageGoalAmount(KualiDecimal a8DisadvantageGoalAmount) {
        this.a8DisadvantageGoalAmount = a8DisadvantageGoalAmount;
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
        if(getSmallBusinessGoalAmount() != null) {
            returnVal = returnVal.add(getSmallBusinessGoalAmount());
        }
        if(getLargeBusinessGoalAmount() != null) {
            returnVal = returnVal.add(getLargeBusinessGoalAmount());
        }
        return returnVal;
    }

    /**
     * This method calculates the expenditure amount for small and large business goal amounts
     * @return The total value
     */
    public KualiDecimal getTotalBusinessExpenditureAmount() {
        KualiDecimal returnVal = new KualiDecimal(0.00);
        if(getSmallBusinessExpenditureAmount() != null) {
            returnVal = returnVal.add(getSmallBusinessExpenditureAmount());
        }
        if(getLargeBusinessExpenditureAmount() != null) {
            returnVal = returnVal.add(getLargeBusinessExpenditureAmount());
        }
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
    

}
