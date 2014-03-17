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
package org.kuali.kra.timeandmoney;

import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.timeandmoney.history.TransactionDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AwardAmountInfoHistory implements Serializable {


    private static final long serialVersionUID = -3741486339602358742L;
    
    private AwardAmountInfo awardAmountInfo;
    private String transactionType;
    private String dateFieldChanged;
    private TransactionDetail primaryDetail;
    private List<TransactionDetail> intermediateDetails;
    
    public AwardAmountInfoHistory() {
        intermediateDetails = new ArrayList<TransactionDetail>();
    }
    
    /**
     * Gets the awardAmountInfo attribute. 
     * @return Returns the awardAmountInfo.
     */
    public AwardAmountInfo getAwardAmountInfo() {
        return awardAmountInfo;
    }
    /**
     * Sets the awardAmountInfo attribute value.
     * @param awardAmountInfo The awardAmountInfo to set.
     */
    public void setAwardAmountInfo(AwardAmountInfo awardAmountInfo) {
        this.awardAmountInfo = awardAmountInfo;
    }
    /**
     * Gets the transactionType attribute. 
     * @return Returns the transactionType.
     */
    public String getTransactionType() {
        return transactionType;
    }
    /**
     * Sets the transactionType attribute value.
     * @param transactionType The transactionType to set.
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    /**
     * Gets the dateFieldChanged attribute. 
     * @return Returns the dateFieldChanged.
     */
    public String getDateFieldChanged() {
        return dateFieldChanged;
    }
    /**
     * Sets the dateFieldChanged attribute value.
     * @param dateFieldChanged The dateFieldChanged to set.
     */
    public void setDateFieldChanged(String dateFieldChanged) {
        this.dateFieldChanged = dateFieldChanged;
    }

    /**
     * Gets the primaryDetail attribute. 
     * @return Returns the primaryDetail.
     */
    public TransactionDetail getPrimaryDetail() {
        return primaryDetail;
    }

    /**
     * Sets the primaryDetail attribute value.
     * @param primaryDetail The primaryDetail to set.
     */
    public void setPrimaryDetail(TransactionDetail primaryDetail) {
        this.primaryDetail = primaryDetail;
    }

    /**
     * Gets the intermediateDetails attribute. 
     * @return Returns the intermediateDetails.
     */
    public List<TransactionDetail> getIntermediateDetails() {
        return intermediateDetails;
    }

    /**
     * Sets the intermediateDetails attribute value.
     * @param intermediateDetails The intermediateDetails to set.
     */
    public void setIntermediateDetails(List<TransactionDetail> intermediateDetails) {
        this.intermediateDetails = intermediateDetails;
    }
    
    public String getTransactionDetailTableSize() {
        int returnValue = intermediateDetails.size() + 1;
        return Integer.toString(returnValue);
    }
    
    
    
    
    
}
