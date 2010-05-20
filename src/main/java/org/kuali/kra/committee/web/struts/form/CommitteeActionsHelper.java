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
package org.kuali.kra.committee.web.struts.form;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.rice.kns.util.WebUtils;

/**
 * 
 * Form helper class for Committee Actions.
 */
public class CommitteeActionsHelper implements Serializable {
        
    private static final long serialVersionUID = -3016492957198123463L;

    private static final String BATCH_CORRESPONDENCE_PANEL_TITLE = "Batch Correspondence";

    private String generateBatchCorrespondenceTypeCode;
    private Date generateStartDate;
    private Date generateEndDate;
    private CommitteeBatchCorrespondence generateBatchCorrespondence;
    
    private String historyBatchCorrespondenceTypeCode;
    private Date historyStartDate;
    private Date historyEndDate;
    private List<CommitteeBatchCorrespondence> batchCorrespondenceHistory;
    
    private Boolean printRooster;
    private Boolean printFutureScheduledMeeting;

    /**
     * 
     * Constructs a CommitteeActionsHelper.java.
     */
    public CommitteeActionsHelper() {
        setGenerateBatchCorrespondence(new CommitteeBatchCorrespondence());
        setBatchCorrespondenceHistory(new ArrayList<CommitteeBatchCorrespondence>());
    }
    
    public String getGenerateBatchCorrespondenceTypeCode() {
        return generateBatchCorrespondenceTypeCode;
    }

    public void setGenerateBatchCorrespondenceTypeCode(String generateBatchCorrespondenceTypeCode) {
        this.generateBatchCorrespondenceTypeCode = generateBatchCorrespondenceTypeCode;
    }

    public Date getGenerateStartDate() {
        return generateStartDate;
    }

    public void setGenerateStartDate(Date generateStartDate) {
        this.generateStartDate = generateStartDate;
    }

    public Date getGenerateEndDate() {
        return generateEndDate;
    }

    public void setGenerateEndDate(Date generateEndDate) {
        this.generateEndDate = generateEndDate;
    }

    public String getHistoryBatchCorrespondenceTypeCode() {
        return historyBatchCorrespondenceTypeCode;
    }

    public void setHistoryBatchCorrespondenceTypeCode(String historyBatchCorrespondenceTypeCode) {
        this.historyBatchCorrespondenceTypeCode = historyBatchCorrespondenceTypeCode;
    }

    public Date getHistoryStartDate() {
        return historyStartDate;
    }

    public void setHistoryStartDate(Date historyStartDate) {
        this.historyStartDate = historyStartDate;
    }

    public Date getHistoryEndDate() {
        return historyEndDate;
    }

    public void setHistoryEndDate(Date historyEndDate) {
        this.historyEndDate = historyEndDate;
    }

    public CommitteeBatchCorrespondence getGenerateBatchCorrespondence() {
        return generateBatchCorrespondence;
    }

    public void setGenerateBatchCorrespondence(CommitteeBatchCorrespondence generateBatchCorrespondence) {
        this.generateBatchCorrespondence = generateBatchCorrespondence;
    }

    public List<CommitteeBatchCorrespondence> getBatchCorrespondenceHistory() {
        return batchCorrespondenceHistory;
    }

    public void setBatchCorrespondenceHistory(List<CommitteeBatchCorrespondence> batchCorrespondenceHistory) {
        this.batchCorrespondenceHistory = batchCorrespondenceHistory;
    }

    public Boolean getPrintRooster() {
        return printRooster;
    }

    public void setPrintRooster(Boolean printRooster) {
        this.printRooster = printRooster;
    }
    
    public Boolean getPrintFutureScheduledMeeting() {
        return printFutureScheduledMeeting;
    }

    public void setPrintFutureScheduledMeeting(Boolean printFutureScheduledMeeting) {
        this.printFutureScheduledMeeting = printFutureScheduledMeeting;
    }
    
    /**
     * 
     * This method resets the Batch Correspondence history. 
     * This involves clearing the previous displayed history information and collapsing all panel content with 
     * the exception of the Batch Correspondence panel.
     * 
     * @param committeeForm the CommitteeForm
     */
    public void resetBatchCorrespondenceHistory(CommitteeForm committeeForm) {
        setBatchCorrespondenceHistory(null);
        committeeForm.setTabStates(new HashMap<String, String>());
        committeeForm.getTabStates().put(WebUtils.generateTabKey(BATCH_CORRESPONDENCE_PANEL_TITLE), "OPEN");
    }
}
