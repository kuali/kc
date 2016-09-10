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

import com.codiform.moo.annotation.CollectionProperty;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class TimeAndMoneyDto {

    private String awardId;
    private String timeAndMoneyDocumentNbr;
    private String timeAndMoneyDocumentStatus;

    @JsonProperty(value="awardAmountTransactions")
    @CollectionProperty(source="awardAmountTransactions", itemClass= AwardAmountTransactionDto.class)
    private List<AwardAmountTransactionDto> awardAmountTransactions;

    @JsonProperty(value="transactionDetails")
    @CollectionProperty(source="transactionDetails", itemClass= AwardAmountTransactionDto.class)
    private List<TransactionDetailDto> transactionDetails;

    public List<AwardAmountTransactionDto> getAwardAmountTransactions() {
        return awardAmountTransactions;
    }

    public void setAwardAmountTransactions(List<AwardAmountTransactionDto> awardAmountTransactions) {
        this.awardAmountTransactions = awardAmountTransactions;
    }

    public List<TransactionDetailDto> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetailDto> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

   public String getTimeAndMoneyDocumentNbr() {
        return timeAndMoneyDocumentNbr;
    }

    public void setTimeAndMoneyDocumentNbr(String documentNbr) {
        this.timeAndMoneyDocumentNbr = documentNbr;
    }

    public String getTimeAndMoneyDocumentStatus() {
        return timeAndMoneyDocumentStatus;
    }

    public void setTimeAndMoneyDocumentStatus(String documentStatus) {
        this.timeAndMoneyDocumentStatus = documentStatus;
    }
}
