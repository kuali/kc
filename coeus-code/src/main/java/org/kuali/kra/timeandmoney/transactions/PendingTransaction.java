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
package org.kuali.kra.timeandmoney.transactions;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class PendingTransaction extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 8626352352644718112L;

    private Long transactionId;

    private String documentNumber;

    private String sourceAwardNumber;

    private String destinationAwardNumber;

    private KualiDecimal obligatedAmount;

    private KualiDecimal obligatedDirectAmount;

    private KualiDecimal obligatedIndirectAmount;

    private KualiDecimal anticipatedAmount;

    private KualiDecimal anticipatedDirectAmount;

    private KualiDecimal anticipatedIndirectAmount;

    private String comments;

    private Boolean processedFlag = Boolean.FALSE;

    // is this a transaction resulting from a change to the current values?
    boolean singleNodeTransaction = false;
    

    public PendingTransaction() {
        setObligatedAmount(new KualiDecimal(0));
        setAnticipatedAmount(new KualiDecimal(0));
        setObligatedDirectAmount(new KualiDecimal(0));
        setAnticipatedDirectAmount(new KualiDecimal(0));
        setObligatedIndirectAmount(new KualiDecimal(0));
        setAnticipatedIndirectAmount(new KualiDecimal(0));
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
    public KualiDecimal getObligatedAmount() {
        //return obligatedAmount;
        return cleanKualiDecimal(obligatedAmount);
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(KualiDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    /**
     * Gets the anticipatedAmount attribute. 
     * @return Returns the anticipatedAmount.
     */
    public KualiDecimal getAnticipatedAmount() {
        //return anticipatedAmount;
        return cleanKualiDecimal(anticipatedAmount);
    }

    /**
     * Sets the anticipatedAmount attribute value.
     * @param anticipatedAmount The anticipatedAmount to set.
     */
    public void setAnticipatedAmount(KualiDecimal anticipatedAmount) {
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
    public KualiDecimal getObligatedDirectAmount() {
        //return obligatedDirectAmount;
        return cleanKualiDecimal(obligatedDirectAmount);
    }

    /**
     * Sets the obligatedDirectAmount attribute value.
     * @param obligatedDirectAmount The obligatedDirectAmount to set.
     */
    public void setObligatedDirectAmount(KualiDecimal obligatedDirectAmount) {
        this.obligatedDirectAmount = obligatedDirectAmount;
    }

    /**
     * Gets the obligatedIndirectAmount attribute. 
     * @return Returns the obligatedIndirectAmount.
     */
    public KualiDecimal getObligatedIndirectAmount() {
        //return obligatedIndirectAmount;
        return cleanKualiDecimal(obligatedIndirectAmount);
    }

    /**
     * Sets the obligatedIndirectAmount attribute value.
     * @param obligatedIndirectAmount The obligatedIndirectAmount to set.
     */
    public void setObligatedIndirectAmount(KualiDecimal obligatedIndirectAmount) {
        this.obligatedIndirectAmount = obligatedIndirectAmount;
    }

    /**
     * Gets the anticipatedDirectAmount attribute. 
     * @return Returns the anticipatedDirectAmount.
     */
    public KualiDecimal getAnticipatedDirectAmount() {
        //return anticipatedDirectAmount;
        return cleanKualiDecimal(anticipatedDirectAmount);
    }

    /**
     * Sets the anticipatedDirectAmount attribute value.
     * @param anticipatedDirectAmount The anticipatedDirectAmount to set.
     */
    public void setAnticipatedDirectAmount(KualiDecimal anticipatedDirectAmount) {
        this.anticipatedDirectAmount = anticipatedDirectAmount;
    }

    /**
     * Gets the anticipatedIndirectAmount attribute. 
     * @return Returns the anticipatedIndirectAmount.
     */
    public KualiDecimal getAnticipatedIndirectAmount() {
        //return anticipatedIndirectAmount;
        return cleanKualiDecimal(anticipatedIndirectAmount);
    }

    /**
     * Sets the anticipatedIndirectAmount attribute value.
     * @param anticipatedIndirectAmount The anticipatedIndirectAmount to set.
     */
    public void setAnticipatedIndirectAmount(KualiDecimal anticipatedIndirectAmount) {
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
    
    protected KualiDecimal cleanKualiDecimal(KualiDecimal kd) {
        if (kd == null) {
            return new KualiDecimal(0);
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
