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


public class TransactionDetailDto {

    private Long transactionDetailId;
    private String awardNumber;
    private Integer sequenceNumber;
    private Long transactionId;
    private String timeAndMoneyDocumentNumber;
    private String comments;
    private String sourceAwardNumber;
    private String destinationAwardNumber;
    private String transactionDetailType;
    private ScaleTwoDecimal obligatedAmount;
    private ScaleTwoDecimal obligatedDirectAmount;
    private ScaleTwoDecimal obligatedIndirectAmount;
    private ScaleTwoDecimal anticipatedAmount;
    private ScaleTwoDecimal anticipatedDirectAmount;
    private ScaleTwoDecimal anticipatedIndirectAmount;

    public Long getTransactionDetailId() {
        return transactionDetailId;
    }

    public void setTransactionDetailId(Long transactionDetailId) {
        this.transactionDetailId = transactionDetailId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSourceAwardNumber() {
        return sourceAwardNumber;
    }

    public void setSourceAwardNumber(String sourceAwardNumber) {
        this.sourceAwardNumber = sourceAwardNumber;
    }

    public String getDestinationAwardNumber() {
        return destinationAwardNumber;
    }

    public void setDestinationAwardNumber(String destinationAwardNumber) {
        this.destinationAwardNumber = destinationAwardNumber;
    }

    public String getTransactionDetailType() {
        return transactionDetailType;
    }

    public void setTransactionDetailType(String transactionDetailType) {
        this.transactionDetailType = transactionDetailType;
    }

    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount;
    }

    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    public ScaleTwoDecimal getObligatedDirectAmount() {
        return obligatedDirectAmount;
    }

    public void setObligatedDirectAmount(ScaleTwoDecimal obligatedDirectAmount) {
        this.obligatedDirectAmount = obligatedDirectAmount;
    }

    public ScaleTwoDecimal getObligatedIndirectAmount() {
        return obligatedIndirectAmount;
    }

    public void setObligatedIndirectAmount(ScaleTwoDecimal obligatedIndirectAmount) {
        this.obligatedIndirectAmount = obligatedIndirectAmount;
    }

    public ScaleTwoDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }

    public void setAnticipatedAmount(ScaleTwoDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
    }

    public ScaleTwoDecimal getAnticipatedDirectAmount() {
        return anticipatedDirectAmount;
    }

    public void setAnticipatedDirectAmount(ScaleTwoDecimal anticipatedDirectAmount) {
        this.anticipatedDirectAmount = anticipatedDirectAmount;
    }

    public ScaleTwoDecimal getAnticipatedIndirectAmount() {
        return anticipatedIndirectAmount;
    }

    public void setAnticipatedIndirectAmount(ScaleTwoDecimal anticipatedIndirectAmount) {
        this.anticipatedIndirectAmount = anticipatedIndirectAmount;
    }
}
