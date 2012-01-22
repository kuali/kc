/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;

/**
 * A collection of this class is maintained on Time And Money Document.  There will be one entry for each Time And
 * Money Document that has award number the same as the root award number for subject awards
 */
public class TimeAndMoneyDocumentHistory implements Serializable{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 786405622918877359L;
    
    private String documentUrl;
    private String timeAndMoneyDocumentDescriptionLine;
    private List<AwardAmountInfoHistory> validAwardAmountInfoHistoryList;
    private TimeAndMoneyDocument timeAndMoneyDocument;
    
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
    
    
    
    

}
