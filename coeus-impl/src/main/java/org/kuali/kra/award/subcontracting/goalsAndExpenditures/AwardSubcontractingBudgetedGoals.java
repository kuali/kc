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
