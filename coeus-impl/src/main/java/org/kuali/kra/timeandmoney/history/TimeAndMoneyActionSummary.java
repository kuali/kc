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

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.io.Serializable;
import java.sql.Date;

public class TimeAndMoneyActionSummary implements Serializable{
    
    private Date noticeDate;
    private String transactionType;
    private Date obligationStartDate;
    private Date obligationEndDate;
    private ScaleTwoDecimal obligationCumulative;
    private ScaleTwoDecimal changeAmount;
    
    
    /**
     * Gets the noticeDate attribute. 
     * @return Returns the noticeDate.
     */
    public Date getNoticeDate() {
        return noticeDate;
    }
    /**
     * Sets the noticeDate attribute value.
     * @param noticeDate The noticeDate to set.
     */
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
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
     * Gets the obligationStartDate attribute. 
     * @return Returns the obligationStartDate.
     */
    public Date getObligationStartDate() {
        return obligationStartDate;
    }
    /**
     * Sets the obligationStartDate attribute value.
     * @param obligationStartDate The obligationStartDate to set.
     */
    public void setObligationStartDate(Date obligationStartDate) {
        this.obligationStartDate = obligationStartDate;
    }
    /**
     * Gets the obligationEndDate attribute. 
     * @return Returns the obligationEndDate.
     */
    public Date getObligationEndDate() {
        return obligationEndDate;
    }
    /**
     * Sets the obligationEndDate attribute value.
     * @param obligationEndDate The obligationEndDate to set.
     */
    public void setObligationEndDate(Date obligationEndDate) {
        this.obligationEndDate = obligationEndDate;
    }
    /**
     * Gets the obligationCumulative attribute. 
     * @return Returns the obligationCumulative.
     */
    public ScaleTwoDecimal getObligationCumulative() {
        return obligationCumulative;
    }
    /**
     * Sets the obligationCumulative attribute value.
     * @param obligationCumulative The obligationCumulative to set.
     */
    public void setObligationCumulative(ScaleTwoDecimal obligationCumulative) {
        this.obligationCumulative = obligationCumulative;
    }
    /**
     * Gets the changeAmount attribute. 
     * @return Returns the changeAmount.
     */
    public ScaleTwoDecimal getChangeAmount() {
        return changeAmount;
    }
    /**
     * Sets the changeAmount attribute value.
     * @param changeAmount The changeAmount to set.
     */
    public void setChangeAmount(ScaleTwoDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

}
