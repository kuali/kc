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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.rules.CommitteeActionGenerateBatchCorrespondenceRuleBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.rice.krad.document.Document;

import java.sql.Date;

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
    public KcBusinessRule getRule() {

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
