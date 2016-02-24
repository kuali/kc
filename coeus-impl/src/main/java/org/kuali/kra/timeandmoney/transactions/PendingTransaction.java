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
package org.kuali.kra.timeandmoney.transactions;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class PendingTransaction extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 8626352352644718112L;

    private Long transactionId;

    private String documentNumber;

    private String sourceAwardNumber;

    private String destinationAwardNumber;

    private ScaleTwoDecimal obligatedAmount;

    private ScaleTwoDecimal obligatedDirectAmount;

    private ScaleTwoDecimal obligatedIndirectAmount;

    private ScaleTwoDecimal anticipatedAmount;

    private ScaleTwoDecimal anticipatedDirectAmount;

    private ScaleTwoDecimal anticipatedIndirectAmount;

    private String comments;

    private Boolean processedFlag = Boolean.FALSE;

    // is this a transaction resulting from a change to the current values?
    boolean singleNodeTransaction = false;
    

    public PendingTransaction() {
        setObligatedAmount(new ScaleTwoDecimal(0));
        setAnticipatedAmount(new ScaleTwoDecimal(0));
        setObligatedDirectAmount(new ScaleTwoDecimal(0));
        setAnticipatedDirectAmount(new ScaleTwoDecimal(0));
        setObligatedIndirectAmount(new ScaleTwoDecimal(0));
        setAnticipatedIndirectAmount(new ScaleTwoDecimal(0));
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
        //return obligatedAmount;
        return cleanScaleTwoDecimal(obligatedAmount);
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    /**
     * Gets the anticipatedAmount attribute. 
     * @return Returns the anticipatedAmount.
     */
    public ScaleTwoDecimal getAnticipatedAmount() {
        //return anticipatedAmount;
        return cleanScaleTwoDecimal(anticipatedAmount);
    }

    /**
     * Sets the anticipatedAmount attribute value.
     * @param anticipatedAmount The anticipatedAmount to set.
     */
    public void setAnticipatedAmount(ScaleTwoDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
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
     * Gets the documentNumber attribute. 
     * @return Returns the documentNumber.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the documentNumber attribute value.
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Gets the obligatedDirectAmount attribute. 
     * @return Returns the obligatedDirectAmount.
     */
    public ScaleTwoDecimal getObligatedDirectAmount() {
        //return obligatedDirectAmount;
        return cleanScaleTwoDecimal(obligatedDirectAmount);
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
        //return obligatedIndirectAmount;
        return cleanScaleTwoDecimal(obligatedIndirectAmount);
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
        //return anticipatedDirectAmount;
        return cleanScaleTwoDecimal(anticipatedDirectAmount);
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
        //return anticipatedIndirectAmount;
        return cleanScaleTwoDecimal(anticipatedIndirectAmount);
    }

    /**
     * Sets the anticipatedIndirectAmount attribute value.
     * @param anticipatedIndirectAmount The anticipatedIndirectAmount to set.
     */
    public void setAnticipatedIndirectAmount(ScaleTwoDecimal anticipatedIndirectAmount) {
        this.anticipatedIndirectAmount = anticipatedIndirectAmount;
    }

    /**
     * Gets the processedFlag attribute. 
     * @return Returns the processedFlag.
     */
    public Boolean getProcessedFlag() {
        return processedFlag;
    }

    /**
     * Sets the processedFlag attribute value.
     * @param processedFlag The processedFlag to set.
     */
    public void setProcessedFlag(Boolean processedFlag) {
        this.processedFlag = processedFlag;
    }
    
    protected ScaleTwoDecimal cleanScaleTwoDecimal(ScaleTwoDecimal kd) {
        if (kd == null) {
            return new ScaleTwoDecimal(0);
        } else {
            return kd;
        }
    }

    public boolean isSingleNodeTransaction() {
        return singleNodeTransaction;
    }

    public void setSingleNodeTransaction(boolean singleNodeTransaction) {
        this.singleNodeTransaction = singleNodeTransaction;
    }
}
