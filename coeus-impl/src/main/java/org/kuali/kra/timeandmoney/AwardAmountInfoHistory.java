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
    private TransactionDetail dateDetail;
    private TransactionDetail primaryDetail;
    private List<TransactionDetail> intermediateDetails;
    
    public AwardAmountInfoHistory() {
        intermediateDetails = new ArrayList<TransactionDetail>();
    }

    public AwardAmountInfo getAwardAmountInfo() {
        return awardAmountInfo;
    }

    public void setAwardAmountInfo(AwardAmountInfo awardAmountInfo) {
        this.awardAmountInfo = awardAmountInfo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDateFieldChanged() {
        return dateFieldChanged;
    }

    public void setDateFieldChanged(String dateFieldChanged) {
        this.dateFieldChanged = dateFieldChanged;
    }

    public TransactionDetail getDateDetail() {
        return dateDetail;
    }

    public void setDateDetail(TransactionDetail dateDetail) {
        this.dateDetail = dateDetail;
    }

    public TransactionDetail getPrimaryDetail() {
        return primaryDetail;
    }

    public void setPrimaryDetail(TransactionDetail primaryDetail) {
        this.primaryDetail = primaryDetail;
    }

    public List<TransactionDetail> getIntermediateDetails() {
        return intermediateDetails;
    }

    public void setIntermediateDetails(List<TransactionDetail> intermediateDetails) {
        this.intermediateDetails = intermediateDetails;
    }
    
    public String getTransactionDetailTableSize() {
        int returnValue = intermediateDetails.size() + 1;
        return Integer.toString(returnValue);
    }
}
