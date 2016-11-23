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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionType;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A collection of this class is maintained on Time And Money Document.  There will be one entry for each Time And
 * Money Document that has award number the same as the root award number for subject awards
 */
public class TimeAndMoneyDocumentHistory implements Serializable{


    private static final long serialVersionUID = 786405622918877359L;
    public static final String SORT_TIME_AND_MONEY_TRANSACTIONS_DESCENDING = "SORT_TIME_AND_MONEY_TRANSACTIONS_DESCENDING";

    private String documentUrl;
    private String timeAndMoneyDocumentDescriptionLine;
    private List<AwardAmountInfoHistory> validAwardAmountInfoHistoryList;
    private TimeAndMoneyDocument timeAndMoneyDocument;
    private transient ParameterService parameterService;

    public TimeAndMoneyDocumentHistory () {
        validAwardAmountInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();
    }
    
    

    /**
     * Gets the documentUrl attribute. 
     * @return Returns the documentUrl.
     */
    public String getDocumentUrl() {
        return documentUrl;
    }



    /**
     * Sets the documentUrl attribute value.
     * @param documentUrl The documentUrl to set.
     */
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }



    /**
     * Gets the timeAndMoneyDocumentDescriptionLine attribute. 
     * @return Returns the timeAndMoneyDocumentDescriptionLine.
     */
    public String getTimeAndMoneyDocumentDescriptionLine() {
        return timeAndMoneyDocumentDescriptionLine;
    }



    /**
     * Sets the timeAndMoneyDocumentDescriptionLine attribute value.
     * @param timeAndMoneyDocumentDescriptionLine The timeAndMoneyDocumentDescriptionLine to set.
     */
    public void setTimeAndMoneyDocumentDescriptionLine(String timeAndMoneyDocumentDescriptionLine) {
        this.timeAndMoneyDocumentDescriptionLine = timeAndMoneyDocumentDescriptionLine;
    }


    

    /**
     * Gets the validAwardAmountInfoHistoryList attribute. 
     * @return Returns the validAwardAmountInfoHistoryList.
     */
    public List<AwardAmountInfoHistory> getValidAwardAmountInfoHistoryList() {
        if(isParameterOn(SORT_TIME_AND_MONEY_TRANSACTIONS_DESCENDING, Constants.MODULE_NAMESPACE_AWARD, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE)) {
            AwardAmountInfoHistory initialTransaction = validAwardAmountInfoHistoryList.stream().filter(
                    validAwardAmountInfoHistoryItem -> validAwardAmountInfoHistoryItem.getTransactionType().equalsIgnoreCase(TransactionType.INITIAL.toString())
            ).findFirst().get();
            validAwardAmountInfoHistoryList.remove(initialTransaction);
            Collections.reverse(validAwardAmountInfoHistoryList);
            validAwardAmountInfoHistoryList.add(initialTransaction);
        }
        return validAwardAmountInfoHistoryList;
    }



    /**
     * Sets the validAwardAmountInfoHistoryList attribute value.
     * @param validAwardAmountInfoHistoryList The validAwardAmountInfoHistoryList to set.
     */
    public void setValidAwardAmountInfoHistoryList(List<AwardAmountInfoHistory> validAwardAmountInfoHistoryList) {
        this.validAwardAmountInfoHistoryList = validAwardAmountInfoHistoryList;
    }



    /**
     * Gets the timeAndMoneyDocument attribute. 
     * @return Returns the timeAndMoneyDocument.
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return timeAndMoneyDocument;
    }

    /**
     * Sets the timeAndMoneyDocument attribute value.
     * @param timeAndMoneyDocument The timeAndMoneyDocument to set.
     */
    public void setTimeAndMoneyDocument(TimeAndMoneyDocument timeAndMoneyDocument) {
        this.timeAndMoneyDocument = timeAndMoneyDocument;
    }

    public boolean isParameterOn(String sortTimeAndMoneyTransactionsDescending, String moduleNamespaceAward, String kcAllParameterDetailTypeCode) {
        return getParameterService().getParameterValueAsBoolean(moduleNamespaceAward,
                kcAllParameterDetailTypeCode, sortTimeAndMoneyTransactionsDescending);
    }

    public ParameterService getParameterService() {
        if(parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

}
