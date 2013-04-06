/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.rule.event;

import java.sql.Date;

import org.kuali.kra.common.committee.rules.CommitteeActionGenerateBatchCorrespondenceRuleBase;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.rice.krad.document.Document;

public abstract class CommitteeActionGenerateBatchCorrespondenceEventBase extends CommitteeActionsEventBase<CommitteeActionGenerateBatchCorrespondenceRuleBase> {

    private static final String MSG = "generate batch correspondence";
    
    private String batchCorrespondenceTypeCode;
    private Date startDate;
    private Date endDate;
    private String committeeId;
    
    public CommitteeActionGenerateBatchCorrespondenceEventBase(String errorPathPrefix, Document document, String batchCorrespondenceTypeCode,
            Date startDate, Date endDate, String committeeId) {
        super(MSG + getDocumentId(document), errorPathPrefix, document);
        setBatchCorrespondenceTypeCode(batchCorrespondenceTypeCode);
        setStartDate(startDate);
        setEndDate(endDate);
        setCommitteeId(committeeId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BusinessRuleInterface getRule() {
        
// TODO *********commented the code below during IACUC refactoring*********         
//        return new CommitteeActionGenerateBatchCorrespondenceRuleBase();
        
        return getNewCommitteeActionGenerateBatchCorrespondenceRuleInstanceHook();
    }

    protected abstract CommitteeActionGenerateBatchCorrespondenceRuleBase getNewCommitteeActionGenerateBatchCorrespondenceRuleInstanceHook();
    

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

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

}
