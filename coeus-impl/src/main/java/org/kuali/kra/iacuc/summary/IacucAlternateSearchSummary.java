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
package org.kuali.kra.iacuc.summary;

import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucProtocolAlternateSearchDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IacucAlternateSearchSummary implements Serializable {

    private static final long serialVersionUID = -6996989015653936693L;

    private Integer iacucAltSearchId;
    private String searchDate;
    private List<String> databases = new ArrayList<String>();
    private String yearsSearched;
    private String keywords;
    private String comments;
    
    private boolean searchDateChanged;
    private boolean databasesChanged;
    private boolean yearsSearchedChanged;
    private boolean keywordsChanged;
    private boolean commentsChanged;

    private SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public IacucAlternateSearchSummary(IacucAlternateSearch alternateSearch) {
        this.iacucAltSearchId = alternateSearch.getIacucAltSearchId();
        this.searchDate = df.format(alternateSearch.getSearchDate());
        for (IacucProtocolAlternateSearchDatabase db:alternateSearch.getDatabases()) {
            databases.add(db.getAlternateSearchDatabaseName());
        }
        this.yearsSearched = alternateSearch.getYearsSearched();
        this.keywords = alternateSearch.getKeywords();
        this.comments = alternateSearch.getComments();
    }

    public Integer getIacucAltSearchId() {
        return iacucAltSearchId;
    }

    public void setIacucAltSearchId(Integer iacucAltSearchId) {
        this.iacucAltSearchId = iacucAltSearchId;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
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

    public boolean isSearchDateChanged() {
        return searchDateChanged;
    }

    public void setSearchDateChanged(boolean searchDateChanged) {
        this.searchDateChanged = searchDateChanged;
    }

    public boolean isDatabasesChanged() {
        return databasesChanged;
    }

    public void setDatabasesChanged(boolean databasesChanged) {
        this.databasesChanged = databasesChanged;
    }

    public boolean isYearsSearchedChanged() {
        return yearsSearchedChanged;
    }

    public void setYearsSearchedChanged(boolean yearsSearchedChanged) {
        this.yearsSearchedChanged = yearsSearchedChanged;
    }

    public boolean isKeywordsChanged() {
        return keywordsChanged;
    }

    public void setKeywordsChanged(boolean keywordsChanged) {
        this.keywordsChanged = keywordsChanged;
    }

    public boolean isCommentsChanged() {
        return commentsChanged;
    }

    public void setCommentsChanged(boolean commentsChanged) {
        this.commentsChanged = commentsChanged;
    }

    public String getDatabaseList() {
        StringBuffer dbSearches = new StringBuffer();
        for (String db:databases) {
            if (dbSearches.length() > 0) {
                dbSearches.append(", ");
            }
            dbSearches.append(db);
        }
        return dbSearches.toString();
    }

}
