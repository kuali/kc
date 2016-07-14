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

import com.codiform.moo.annotation.Property;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.sql.Date;

public class AwardAmountTransactionDto {

    private Long awardAmountTransactionId;
    private String awardNumber;
    private String documentNumber;
    private Date noticeDate;
    private String comments;
    private Integer transactionTypeCode;
    @JsonIgnore
    @Property(translate = true)
    private AwardTransactionTypeDto awardTransactionType;



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

    @JsonProperty
    public AwardTransactionTypeDto getAwardTransactionType() {
        return awardTransactionType;
    }
    @JsonIgnore
    public void setAwardTransactionType(AwardTransactionTypeDto awardTransactionType) {
        this.awardTransactionType = awardTransactionType;
    }
}
