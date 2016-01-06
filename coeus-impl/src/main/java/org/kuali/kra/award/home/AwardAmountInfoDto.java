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
package org.kuali.kra.award.home;

import java.sql.Date;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class AwardAmountInfoDto {

	private Long awardAmountInfoId;
    private Long transactionId;
    private String timeAndMoneyDocumentNumber;
    private ScaleTwoDecimal anticipatedTotalAmount;
    private ScaleTwoDecimal antDistributableAmount;
    private Date finalExpirationDate;
    private Date currentFundEffectiveDate;
    private ScaleTwoDecimal amountObligatedToDate;
    private ScaleTwoDecimal obliDistributableAmount;
    private Date obligationExpirationDate;
    private ScaleTwoDecimal anticipatedChange;
    private ScaleTwoDecimal obligatedChange;
    private ScaleTwoDecimal obligatedChangeDirect;
    private ScaleTwoDecimal obligatedChangeIndirect;
    private ScaleTwoDecimal anticipatedChangeDirect;
    private ScaleTwoDecimal anticipatedChangeIndirect;
    private ScaleTwoDecimal anticipatedTotalDirect;
    private ScaleTwoDecimal anticipatedTotalIndirect;
    private ScaleTwoDecimal obligatedTotalDirect;
    private ScaleTwoDecimal obligatedTotalIndirect;
    private Integer originatingAwardVersion;
    
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getTimeAndMoneyDocumentNumber() {
		return timeAndMoneyDocumentNumber;
	}
	public void setTimeAndMoneyDocumentNumber(String timeAndMoneyDocumentNumber) {
		this.timeAndMoneyDocumentNumber = timeAndMoneyDocumentNumber;
	}
	public ScaleTwoDecimal getAnticipatedTotalAmount() {
		return anticipatedTotalAmount;
	}
	public void setAnticipatedTotalAmount(ScaleTwoDecimal anticipatedTotalAmount) {
		this.anticipatedTotalAmount = anticipatedTotalAmount;
	}
	public ScaleTwoDecimal getAntDistributableAmount() {
		return antDistributableAmount;
	}
	public void setAntDistributableAmount(ScaleTwoDecimal antDistributableAmount) {
		this.antDistributableAmount = antDistributableAmount;
	}
	public Date getFinalExpirationDate() {
		return finalExpirationDate;
	}
	public void setFinalExpirationDate(Date finalExpirationDate) {
		this.finalExpirationDate = finalExpirationDate;
	}
	public Date getCurrentFundEffectiveDate() {
		return currentFundEffectiveDate;
	}
	public void setCurrentFundEffectiveDate(Date currentFundEffectiveDate) {
		this.currentFundEffectiveDate = currentFundEffectiveDate;
	}
	public ScaleTwoDecimal getAmountObligatedToDate() {
		return amountObligatedToDate;
	}
	public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
		this.amountObligatedToDate = amountObligatedToDate;
	}
	public ScaleTwoDecimal getObliDistributableAmount() {
		return obliDistributableAmount;
	}
	public void setObliDistributableAmount(ScaleTwoDecimal obliDistributableAmount) {
		this.obliDistributableAmount = obliDistributableAmount;
	}
	public Date getObligationExpirationDate() {
		return obligationExpirationDate;
	}
	public void setObligationExpirationDate(Date obligationExpirationDate) {
		this.obligationExpirationDate = obligationExpirationDate;
	}
	public ScaleTwoDecimal getAnticipatedChange() {
		return anticipatedChange;
	}
	public void setAnticipatedChange(ScaleTwoDecimal anticipatedChange) {
		this.anticipatedChange = anticipatedChange;
	}
	public ScaleTwoDecimal getObligatedChange() {
		return obligatedChange;
	}
	public void setObligatedChange(ScaleTwoDecimal obligatedChange) {
		this.obligatedChange = obligatedChange;
	}
	public ScaleTwoDecimal getObligatedChangeDirect() {
		return obligatedChangeDirect;
	}
	public void setObligatedChangeDirect(ScaleTwoDecimal obligatedChangeDirect) {
		this.obligatedChangeDirect = obligatedChangeDirect;
	}
	public ScaleTwoDecimal getObligatedChangeIndirect() {
		return obligatedChangeIndirect;
	}
	public void setObligatedChangeIndirect(ScaleTwoDecimal obligatedChangeIndirect) {
		this.obligatedChangeIndirect = obligatedChangeIndirect;
	}
	public ScaleTwoDecimal getAnticipatedChangeDirect() {
		return anticipatedChangeDirect;
	}
	public void setAnticipatedChangeDirect(ScaleTwoDecimal anticipatedChangeDirect) {
		this.anticipatedChangeDirect = anticipatedChangeDirect;
	}
	public ScaleTwoDecimal getAnticipatedChangeIndirect() {
		return anticipatedChangeIndirect;
	}
	public void setAnticipatedChangeIndirect(ScaleTwoDecimal anticipatedChangeIndirect) {
		this.anticipatedChangeIndirect = anticipatedChangeIndirect;
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
	public Integer getOriginatingAwardVersion() {
		return originatingAwardVersion;
	}
	public void setOriginatingAwardVersion(Integer originatingAwardVersion) {
		this.originatingAwardVersion = originatingAwardVersion;
	}
	public Long getAwardAmountInfoId() {
		return awardAmountInfoId;
	}
	public void setAwardAmountInfoId(Long awardAmountInfoId) {
		this.awardAmountInfoId = awardAmountInfoId;
	}
	
}
