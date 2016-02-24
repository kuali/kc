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
