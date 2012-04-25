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
package org.kuali.kra.iacuc.threers;

import java.util.Date;
import java.util.SortedSet;

import org.kuali.kra.protocol.ProtocolAssociate;

public class IacucAlternativeSearch extends ProtocolAssociate {

    private static final long serialVersionUID = -7711644839097962481L;
    
    private Long iacucAltSearchId;
    private boolean searchRequired;
    private Date searchDate;
    private SortedSet<String> databases;
    private String yearsSearched;
    private String keywords;
    private String comments;

    public Long getIacucAltSearchId() {
        return iacucAltSearchId;
    }

    public void setIacucAltSearchId(Long iacucAltSearchId) {
        this.iacucAltSearchId = iacucAltSearchId;
    }

    public boolean isSearchRequired() {
        return searchRequired;
    }

    public void setSearchRequired(boolean searchRequired) {
        this.searchRequired = searchRequired;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public SortedSet<String> getDatabases() {
        return databases;
    }

    public void setDatabases(SortedSet<String> databases) {
        this.databases = databases;
    }

    public String getYearsSearched() {
        return yearsSearched;
    }

    public void setYearsSearched(String yearsSearched) {
        this.yearsSearched = yearsSearched;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public void resetPersistenceState() {
        this.setIacucAltSearchId(null);        
    }
    
}
