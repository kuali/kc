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

package org.kuali.coeus.award.finance.timeAndMoney.dto;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class AwardHierarchyNodeDto {
    private String awardNumber;
    private Long awardId;
    private String title;
    private Date currentFundEffectiveDate;
    private Date obligationExpirationDate;
    private Date finalExpirationDate;
    private Date projectStartDate;
    private ScaleTwoDecimal anticipatedTotalAmount;
    private ScaleTwoDecimal anticipatedTotalDirect;
    private ScaleTwoDecimal anticipatedTotalIndirect;
    private ScaleTwoDecimal antDistributableAmount;
    private ScaleTwoDecimal amountObligatedToDate;
    private ScaleTwoDecimal obligatedTotalDirect;
    private ScaleTwoDecimal obligatedTotalIndirect;
    private ScaleTwoDecimal obliDistributableAmount;
    private String leadUnitName;
    private String principalInvestigatorName;
    private String accountNumber;
    private Integer awardStatusCode;
    private Boolean awardDocumentFinalStatus;
    private String awardDocumentNumber;
    private Boolean hasChildren;

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Date getCurrentFundEffectiveDate() {
        return currentFundEffectiveDate;
    }

    public void setCurrentFundEffectiveDate(Date currentFundEffectiveDate) {
        this.currentFundEffectiveDate = currentFundEffectiveDate;
    }

    public Date getObligationExpirationDate() {
        return obligationExpirationDate;
    }

    public void setObligationExpirationDate(Date obligationExpirationDate) {
        this.obligationExpirationDate = obligationExpirationDate;
    }

    public Date getFinalExpirationDate() {
        return finalExpirationDate;
    }

    public void setFinalExpirationDate(Date finalExpirationDate) {
        this.finalExpirationDate = finalExpirationDate;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public ScaleTwoDecimal getAnticipatedTotalAmount() {
        return anticipatedTotalAmount;
    }

    public void setAnticipatedTotalAmount(ScaleTwoDecimal anticipatedTotalAmount) {
        this.anticipatedTotalAmount = anticipatedTotalAmount;
    }

    public ScaleTwoDecimal getAnticipatedTotalDirect() {
        return anticipatedTotalDirect;
    }

    public void setAnticipatedTotalDirect(ScaleTwoDecimal anticipatedTotalDirect) {
        this.anticipatedTotalDirect = anticipatedTotalDirect;
    }

    public ScaleTwoDecimal getAnticipatedTotalIndirect() {
        return anticipatedTotalIndirect;
    }

    public void setAnticipatedTotalIndirect(ScaleTwoDecimal anticipatedTotalIndirect) {
        this.anticipatedTotalIndirect = anticipatedTotalIndirect;
    }

    public ScaleTwoDecimal getAntDistributableAmount() {
        return antDistributableAmount;
    }

    public void setAntDistributableAmount(ScaleTwoDecimal antDistributableAmount) {
        this.antDistributableAmount = antDistributableAmount;
    }

    public ScaleTwoDecimal getAmountObligatedToDate() {
        return amountObligatedToDate;
    }

    public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
        this.amountObligatedToDate = amountObligatedToDate;
    }

    public ScaleTwoDecimal getObligatedTotalDirect() {
        return obligatedTotalDirect;
    }

    public void setObligatedTotalDirect(ScaleTwoDecimal obligatedTotalDirect) {
        this.obligatedTotalDirect = obligatedTotalDirect;
    }

    public ScaleTwoDecimal getObligatedTotalIndirect() {
        return obligatedTotalIndirect;
    }

    public void setObligatedTotalIndirect(ScaleTwoDecimal obligatedTotalIndirect) {
        this.obligatedTotalIndirect = obligatedTotalIndirect;
    }

    public ScaleTwoDecimal getObliDistributableAmount() {
        return obliDistributableAmount;
    }

    public void setObliDistributableAmount(ScaleTwoDecimal obliDistributableAmount) {
        this.obliDistributableAmount = obliDistributableAmount;
    }

    public String getLeadUnitName() {
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }

    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }

    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAwardStatusCode() {
        return awardStatusCode;
    }

    public void setAwardStatusCode(Integer awardStatusCode) {
        this.awardStatusCode = awardStatusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public Boolean getAwardDocumentFinalStatus() {
        return awardDocumentFinalStatus;
    }

    public void setAwardDocumentFinalStatus(Boolean awardDocumentFinalStatus) {
        this.awardDocumentFinalStatus = awardDocumentFinalStatus;
    }

    public String getAwardDocumentNumber() {
        return awardDocumentNumber;
    }

    public void setAwardDocumentNumber(String awardDocumentNumber) {
        this.awardDocumentNumber = awardDocumentNumber;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
