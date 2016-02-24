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
package org.kuali.kra.timeandmoney.history;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class TransactionDetail extends KcPersistableBusinessObjectBase {

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

    /**
     * Gets the transactionDetailId attribute. 
     * @return Returns the transactionDetailId.
     */
    public Long getTransactionDetailId() {
        return transactionDetailId;
    }

    /**
     * Sets the transactionDetailId attribute value.
     * @param transactionDetailId The transactionDetailId to set.
     */
    public void setTransactionDetailId(Long transactionDetailId) {
        this.transactionDetailId = transactionDetailId;
    }

    /**
     * Gets the awardNumber attribute. 
     * @return Returns the awardNumber.
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * Sets the awardNumber attribute value.
     * @param awardNumber The awardNumber to set.
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * Gets the sequenceNumber attribute. 
     * @return Returns the sequenceNumber.
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the transactionId attribute. 
     * @return Returns the transactionId.
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transactionId attribute value.
     * @param transactionId The transactionId to set.
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the comments attribute. 
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments attribute value.
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the sourceAwardNumber attribute. 
     * @return Returns the sourceAwardNumber.
     */
    public String getSourceAwardNumber() {
        return sourceAwardNumber;
    }

    /**
     * Sets the sourceAwardNumber attribute value.
     * @param sourceAwardNumber The sourceAwardNumber to set.
     */
    public void setSourceAwardNumber(String sourceAwardNumber) {
        this.sourceAwardNumber = sourceAwardNumber;
    }

    /**
     * Gets the destinationAwardNumber attribute. 
     * @return Returns the destinationAwardNumber.
     */
    public String getDestinationAwardNumber() {
        return destinationAwardNumber;
    }

    /**
     * Sets the destinationAwardNumber attribute value.
     * @param destinationAwardNumber The destinationAwardNumber to set.
     */
    public void setDestinationAwardNumber(String destinationAwardNumber) {
        this.destinationAwardNumber = destinationAwardNumber;
    }

    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount;
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligated The obligatedAmount to set.
     */
    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    /**
     * Gets the anticipatedAmount attribute. 
     * @return Returns the anticipatedAmount.
     */
    public ScaleTwoDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }

    /**
     * Sets the anticipatedAmount attribute value.
     * @param anticipatedAmount The anticipatedAmount to set.
     */
    public void setAnticipatedAmount(ScaleTwoDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
    }

    /**
     * Gets the transactionDetailType attribute. 
     * @return Returns the transactionDetailType.
     */
    public String getTransactionDetailType() {
        return transactionDetailType;
    }

    /**
     * Sets the transactionDetailType attribute value.
     * @param transactionDetailType The transactionDetailType to set.
     */
    public void setTransactionDetailType(String transactionDetailType) {
        this.transactionDetailType = transactionDetailType;
    }

    /**
     * Gets the timeAndMoneyDocumentNumber attribute. 
     * @return Returns the timeAndMoneyDocumentNumber.
     */
    public String getTimeAndMoneyDocumentNumber() {
        return timeAndMoneyDocumentNumber;
    }

    /**
     * Sets the timeAndMoneyDocumentNumber attribute value.
     * @param timeAndMoneyDocumentNumber The timeAndMoneyDocumentNumber to set.
     */
    public void setTimeAndMoneyDocumentNumber(String timeAndMoneyDocumentNumber) {
        this.timeAndMoneyDocumentNumber = timeAndMoneyDocumentNumber;
    }

    /**
     * Gets the obligatedDirectAmount attribute. 
     * @return Returns the obligatedDirectAmount.
     */
    public ScaleTwoDecimal getObligatedDirectAmount() {
        return obligatedDirectAmount;
    }

    /**
     * Sets the obligatedDirectAmount attribute value.
     * @param obligatedDirectAmount The obligatedDirectAmount to set.
     */
    public void setObligatedDirectAmount(ScaleTwoDecimal obligatedDirectAmount) {
        this.obligatedDirectAmount = obligatedDirectAmount;
    }

    /**
     * Gets the obligatedIndirectAmount attribute. 
     * @return Returns the obligatedIndirectAmount.
     */
    public ScaleTwoDecimal getObligatedIndirectAmount() {
        return obligatedIndirectAmount;
    }

    /**
     * Sets the obligatedIndirectAmount attribute value.
     * @param obligatedIndirectAmount The obligatedIndirectAmount to set.
     */
    public void setObligatedIndirectAmount(ScaleTwoDecimal obligatedIndirectAmount) {
        this.obligatedIndirectAmount = obligatedIndirectAmount;
    }

    /**
     * Gets the anticipatedDirectAmount attribute. 
     * @return Returns the anticipatedDirectAmount.
     */
    public ScaleTwoDecimal getAnticipatedDirectAmount() {
        return anticipatedDirectAmount;
    }

    /**
     * Sets the anticipatedDirectAmount attribute value.
     * @param anticipatedDirectAmount The anticipatedDirectAmount to set.
     */
    public void setAnticipatedDirectAmount(ScaleTwoDecimal anticipatedDirectAmount) {
        this.anticipatedDirectAmount = anticipatedDirectAmount;
    }

    /**
     * Gets the anticipatedIndirectAmount attribute. 
     * @return Returns the anticipatedIndirectAmount.
     */
    public ScaleTwoDecimal getAnticipatedIndirectAmount() {
        return anticipatedIndirectAmount;
    }

    /**
     * Sets the anticipatedIndirectAmount attribute value.
     * @param anticipatedIndirectAmount The anticipatedIndirectAmount to set.
     */
    public void setAnticipatedIndirectAmount(ScaleTwoDecimal anticipatedIndirectAmount) {
        this.anticipatedIndirectAmount = anticipatedIndirectAmount;
    }
}
