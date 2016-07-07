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
import org.kuali.kra.bo.CostShareType;

import java.sql.Date;

public class AwardCostShareDto {

    private Long awardCostShareId;

    private String projectPeriod;

    private ScaleTwoDecimal costSharePercentage;

    private Integer costShareTypeCode;

    private Date verificationDate;

    private ScaleTwoDecimal costShareMet;

    private String source;

    private String destination;

    private ScaleTwoDecimal commitmentAmount;

    private CostShareType costShareType;

    public Long getAwardCostShareId() {
        return awardCostShareId;
    }

    public void setAwardCostShareId(Long awardCostShareId) {
        this.awardCostShareId = awardCostShareId;
    }

    public String getProjectPeriod() {
        return projectPeriod;
    }

    public void setProjectPeriod(String projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    public ScaleTwoDecimal getCostSharePercentage() {
        return costSharePercentage;
    }

    public void setCostSharePercentage(ScaleTwoDecimal costSharePercentage) {
        this.costSharePercentage = costSharePercentage;
    }

    public Integer getCostShareTypeCode() {
        return costShareTypeCode;
    }

    public void setCostShareTypeCode(Integer costShareTypeCode) {
        this.costShareTypeCode = costShareTypeCode;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public ScaleTwoDecimal getCostShareMet() {
        return costShareMet;
    }

    public void setCostShareMet(ScaleTwoDecimal costShareMet) {
        this.costShareMet = costShareMet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ScaleTwoDecimal getCommitmentAmount() {
        return commitmentAmount;
    }

    public void setCommitmentAmount(ScaleTwoDecimal commitmentAmount) {
        this.commitmentAmount = commitmentAmount;
    }

    public CostShareType getCostShareType() {
        return costShareType;
    }

    public void setCostShareType(CostShareType costShareType) {
        this.costShareType = costShareType;
    }
}
