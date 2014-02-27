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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.rules.CommitteeActionFilterBatchCorrespondenceHistoryRule;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.rice.krad.document.Document;

import java.sql.Date;

/**
 * 
 * This class implements the CommitteeActionFilterBatchCorrespondenceHistoryEven which is used
 * to view a specific set of batch correspondence that has been generated in the past.
 */
public class CommitteeActionFilterBatchCorrespondenceHistoryEvent extends CommitteeActionsEventBase<CommitteeActionFilterBatchCorrespondenceHistoryRule> {
    private static final String MSG = "filter batch correspondence history";
    
    private String batchCorrespondenceTypeCode;
    private Date startDate;
    private Date endDate;
    
    /**
     * 
     * Constructs a CommitteeActionFilterBatchCorrespondenceHistoryEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param batchCorrespondenceTypeCode
     * @param startDate
     * @param endDate
     */
    public CommitteeActionFilterBatchCorrespondenceHistoryEvent(String errorPathPrefix, Document document, String batchCorrespondenceTypeCode,
            Date startDate, Date endDate) {
        super(MSG + getDocumentId(document), errorPathPrefix, document);
        setBatchCorrespondenceTypeCode(batchCorrespondenceTypeCode);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new CommitteeActionFilterBatchCorrespondenceHistoryRule();
    }

    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
