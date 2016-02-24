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
