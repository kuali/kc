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

import org.kuali.kra.award.home.Award;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of this class is maintained on Time And Money Document.  There will be one entry for each version
 * of the current Award on Time And Money Document.
 */
public class AwardVersionHistory implements Serializable{


    private static final long serialVersionUID = -1282330144911723521L;
    
    private String documentUrl;
    private String awardDescriptionLine;
    private List<TimeAndMoneyDocumentHistory> timeAndMoneyDocumentHistoryList;
    private Award awardParent;
    
    public AwardVersionHistory(Award parent) {
        timeAndMoneyDocumentHistoryList = new ArrayList<TimeAndMoneyDocumentHistory>();
        awardParent = parent;
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
     * Gets the awardDescriptionLine attribute. 
     * @return Returns the awardDescriptionLine.
     */
    public String getAwardDescriptionLine() {
        return awardDescriptionLine;
    }
    /**
     * Sets the awardDescriptionLine attribute value.
     * @param awardDescriptionLine The awardDescriptionLine to set.
     */
    public void setAwardDescriptionLine(String awardDescriptionLine) {
        this.awardDescriptionLine = awardDescriptionLine;
    }
    /**
     * Gets the timeAndMoneyDocumentHistoryList attribute. 
     * @return Returns the timeAndMoneyDocumentHistoryList.
     */
    public List<TimeAndMoneyDocumentHistory> getTimeAndMoneyDocumentHistoryList() {
        return timeAndMoneyDocumentHistoryList;
    }
    /**
     * Sets the timeAndMoneyDocumentHistoryList attribute value.
     * @param timeAndMoneyDocumentHistoryList The timeAndMoneyDocumentHistoryList to set.
     */
    public void setTimeAndMoneyDocumentHistoryList(List<TimeAndMoneyDocumentHistory> timeAndMoneyDocumentHistoryList) {
        this.timeAndMoneyDocumentHistoryList = timeAndMoneyDocumentHistoryList;
    }
    
    public Award getAwardParent() {
        return awardParent;
    }
    
}
