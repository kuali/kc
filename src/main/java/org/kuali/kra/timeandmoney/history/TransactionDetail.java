/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.history;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kns.util.KualiDecimal;

public class TransactionDetail extends KraPersistableBusinessObjectBase {
    
    private Long transactionDetailId;
    private String awardNumber;
    private Integer sequenceNumber;
    private Long transactionId;
    private String timeAndMoneyDocumentNumber;
    private String comments;
    private String sourceAwardNumber;
    private String destinationAwardNumber;
    private KualiDecimal obligatedAmount;
    private KualiDecimal anticipatedAmount;
    
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {        
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("transactionDetailId", this.getAwardNumber());
        hashMap.put("awardNumber", this.getAwardNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("transactionId", this.getTransactionId());
        hashMap.put("timeAndMoneyDocumentNumber", this.getTimeAndMoneyDocumentNumber());        
        hashMap.put("comments", this.getComments());
        hashMap.put("sourceAwardNumber", this.getSourceAwardNumber());
        hashMap.put("destinationAwardNumber", this.getDestinationAwardNumber());
        hashMap.put("obligatedAmount", this.getObligatedAmount());        
        hashMap.put("anticipatedAmount", this.getAnticipatedAmount());        
        return hashMap;
    }

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
    public KualiDecimal getObligatedAmount() {
        return obligatedAmount;
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligated The obligatedAmount to set.
     */
    public void setObligatedAmount(KualiDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    /**
     * Gets the anticipatedAmount attribute. 
     * @return Returns the anticipatedAmount.
     */
    public KualiDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }

    /**
     * Sets the anticipatedAmount attribute value.
     * @param anticipatedAmount The anticipatedAmount to set.
     */
    public void setAnticipatedAmount(KualiDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
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

}
