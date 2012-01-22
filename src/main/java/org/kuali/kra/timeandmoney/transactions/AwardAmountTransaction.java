/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.timeandmoney.transactions;

import java.io.Serializable;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardAmountTransaction extends KraPersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long awardAmountTransactionId;

    private String awardNumber;

    private String documentNumber;

    private Integer transactionTypeCode;

    private Date noticeDate;

    private String comments;

    private AwardTransactionType awardTransactionType;

    public AwardAmountTransaction() {
    }

    public Long getAwardAmountTransactionId() {
        return awardAmountTransactionId;
    }

    public void setAwardAmountTransactionId(Long awardAmountTransactionId) {
        this.awardAmountTransactionId = awardAmountTransactionId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(Integer transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the awardTransactionType attribute. 
     * @return Returns the awardTransactionType.
     */
    public AwardTransactionType getAwardTransactionType() {
        return awardTransactionType;
    }

    /**
     * Sets the awardTransactionType attribute value.
     * @param awardTransactionType The awardTransactionType to set.
     */
    public void setAwardTransactionType(AwardTransactionType awardTransactionType) {
        this.awardTransactionType = awardTransactionType;
    }
}
