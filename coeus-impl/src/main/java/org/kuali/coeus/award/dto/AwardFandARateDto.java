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
package org.kuali.coeus.award.dto;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class AwardFandARateDto {

    private Long awardFandaRateId;

    private ScaleTwoDecimal applicableFandaRate;

    private String fandaRateTypeCode;

    private String fiscalYear;

    private String onCampusFlag;

    private ScaleTwoDecimal underrecoveryOfIndirectCost;

    private String sourceAccount;

    private String destinationAccount;

    private Date startDate;

    private Date endDate;


    public Long getAwardFandaRateId() {
        return awardFandaRateId;
    }

    public void setAwardFandaRateId(Long awardFandaRateId) {
        this.awardFandaRateId = awardFandaRateId;
    }

    public ScaleTwoDecimal getApplicableFandaRate() {
        return applicableFandaRate;
    }

    public void setApplicableFandaRate(ScaleTwoDecimal applicableFandaRate) {
        this.applicableFandaRate = applicableFandaRate;
    }

    public String getFandaRateTypeCode() {
        return fandaRateTypeCode;
    }

    public void setFandaRateTypeCode(String fandaRateTypeCode) {
        this.fandaRateTypeCode = fandaRateTypeCode;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getOnCampusFlag() {
        return onCampusFlag;
    }

    public void setOnCampusFlag(String onCampusFlag) {
        this.onCampusFlag = onCampusFlag;
    }

    public ScaleTwoDecimal getUnderrecoveryOfIndirectCost() {
        return underrecoveryOfIndirectCost;
    }

    public void setUnderrecoveryOfIndirectCost(ScaleTwoDecimal underrecoveryOfIndirectCost) {
        this.underrecoveryOfIndirectCost = underrecoveryOfIndirectCost;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
