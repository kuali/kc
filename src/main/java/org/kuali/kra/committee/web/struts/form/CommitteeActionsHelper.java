/*
 * Copyright 2006-2010 The Kuali Foundation
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

public class CommitteeActionsHelper implements Serializable {
    
    private static final long serialVersionUID = -3016492957198123463L;

    private String generateBatchCorrespondenceTypeCode;
    private Date generateStartDate;
    private Date generateEndDate;
    
    private String historyBatchCorrespondenceTypeCode;
    private Date historyStartDate;
    private Date historyEndDate;
    
    private String reportType;

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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
