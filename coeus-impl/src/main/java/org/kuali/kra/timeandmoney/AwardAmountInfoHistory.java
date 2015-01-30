/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
