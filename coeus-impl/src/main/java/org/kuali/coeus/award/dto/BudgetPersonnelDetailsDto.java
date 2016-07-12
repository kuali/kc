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

public class BudgetPersonnelDetailsDto {

    private Long budgetPersonnelLineItemId;
    private Long budgetLineItemId;
    private Integer lineItemNumber;
    private Boolean onOffCampusFlag;
    private Date endDate;
    private Date startDate;
    private String budgetJustification;
    private ScaleTwoDecimal costSharingAmount;
    private String lineItemDescription;
    private Boolean applyInRateFlag;
    private Integer personNumber;
    private ScaleTwoDecimal costSharingPercent;
    private String jobCode;
    private ScaleTwoDecimal percentCharged;
    private ScaleTwoDecimal percentEffort = ScaleTwoDecimal.ZERO;
    private String periodTypeCode;
    private String personId;
    private ScaleTwoDecimal salaryRequested = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal obligatedAmount;
    private Integer sequenceNumber;
    private Long budgetPeriodId;
    private Integer personSequenceNumber;
    private ScaleTwoDecimal underrecoveryAmount = ScaleTwoDecimal.ZERO;

    public Long getBudgetPersonnelLineItemId() {
        return budgetPersonnelLineItemId;
    }

    public void setBudgetPersonnelLineItemId(Long budgetPersonnelLineItemId) {
        this.budgetPersonnelLineItemId = budgetPersonnelLineItemId;
    }

    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    public Integer getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(Integer lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

    public Boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getBudgetJustification() {
        return budgetJustification;
    }

    public void setBudgetJustification(String budgetJustification) {
        this.budgetJustification = budgetJustification;
    }

    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public String getLineItemDescription() {
        return lineItemDescription;
    }

    public void setLineItemDescription(String lineItemDescription) {
        this.lineItemDescription = lineItemDescription;
    }

    public Boolean getApplyInRateFlag() {
        return applyInRateFlag;
    }

    public void setApplyInRateFlag(Boolean applyInRateFlag) {
        this.applyInRateFlag = applyInRateFlag;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    public ScaleTwoDecimal getCostSharingPercent() {
        return costSharingPercent;
    }

    public void setCostSharingPercent(ScaleTwoDecimal costSharingPercent) {
        this.costSharingPercent = costSharingPercent;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public ScaleTwoDecimal getPercentCharged() {
        return percentCharged;
    }

    public void setPercentCharged(ScaleTwoDecimal percentCharged) {
        this.percentCharged = percentCharged;
    }

    public ScaleTwoDecimal getPercentEffort() {
        return percentEffort;
    }

    public void setPercentEffort(ScaleTwoDecimal percentEffort) {
        this.percentEffort = percentEffort;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public ScaleTwoDecimal getSalaryRequested() {
        return salaryRequested;
    }

    public void setSalaryRequested(ScaleTwoDecimal salaryRequested) {
        this.salaryRequested = salaryRequested;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount;
    }

    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
}
