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

import java.sql.Date;

// this is a read-only access BO that will be used in computing the expenditure data for small business contracting reports
public class SubcontractingExpenditureCategoryDetails extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 8329712110358616261L;
    
    private Long id;
    private String awardNumber;
    private ScaleTwoDecimal amount;
    private Date fiscalPeriod;
    
    private boolean largeBusiness;
    private boolean smallBusiness;
    
    private boolean womanOwned;
    private boolean eightADisadvantage; 
    private boolean hubZone;
    private boolean veteranOwned; 
    private boolean serviceDisabledVeteranOwned; 
    private boolean historicalBlackCollege;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAmount(ScaleTwoDecimal amount) {
        this.amount = amount;
    }

    public ScaleTwoDecimal getAmount() {
        return amount;
    }

    public void setFiscalPeriod(Date fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    public Date getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setLargeBusiness(boolean largeBusiness) {
        this.largeBusiness = largeBusiness;
    }

    public boolean isLargeBusiness() {
        return largeBusiness;
    }

    public void setSmallBusiness(boolean smallBusiness) {
        this.smallBusiness = smallBusiness;
    }

    public boolean isSmallBusiness() {
        return smallBusiness;
    }

    public void setWomanOwned(boolean womanOwned) {
        this.womanOwned = womanOwned;
    }

    public boolean isWomanOwned() {
        return womanOwned;
    }

    public void setEightADisadvantage(boolean eightADisadvantage) {
        this.eightADisadvantage = eightADisadvantage;
    }

    public boolean isEightADisadvantage() {
        return eightADisadvantage;
    }

    public void setHubZone(boolean hubZone) {
        this.hubZone = hubZone;
    }

    public boolean isHubZone() {
        return hubZone;
    }

    public void setVeteranOwned(boolean veteranOwned) {
        this.veteranOwned = veteranOwned;
    }

    public boolean isVeteranOwned() {
        return veteranOwned;
    }

    public void setServiceDisabledVeteranOwned(boolean serviceDisabledVeteranOwned) {
        this.serviceDisabledVeteranOwned = serviceDisabledVeteranOwned;
    }

    public boolean isServiceDisabledVeteranOwned() {
        return serviceDisabledVeteranOwned;
    }

    public void setHistoricalBlackCollege(boolean historicalBlackCollege) {
        this.historicalBlackCollege = historicalBlackCollege;
    }

    public boolean isHistoricalBlackCollege() {
        return historicalBlackCollege;
    }

    
}
