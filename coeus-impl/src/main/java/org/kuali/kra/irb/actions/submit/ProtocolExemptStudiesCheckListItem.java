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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.ProtocolAssociateBase;

@SuppressWarnings("serial")
public class ProtocolExemptStudiesCheckListItem extends ProtocolAssociateBase {

    private Long protocolExemptCheckListId;

    private Long submissionIdFk;

    private Integer submissionNumber;

    private String exemptStudiesCheckListCode;


    private ProtocolSubmission protocolSubmission;

    private ExemptStudiesCheckListItem exemptStudiesCheckListItem;

    public ProtocolExemptStudiesCheckListItem() {
    }

    public Long getProtocolExemptCheckListId() {
        return protocolExemptCheckListId;
    }

    public void setProtocolExemptCheckListId(Long protocolExemptCheckListId) {
        this.protocolExemptCheckListId = protocolExemptCheckListId;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getExemptStudiesCheckListCode() {
        return exemptStudiesCheckListCode;
    }

    public void setExemptStudiesCheckListCode(String exemptStudiesCheckListCode) {
        this.exemptStudiesCheckListCode = exemptStudiesCheckListCode;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public void setExemptStudiesCheckListItem(ExemptStudiesCheckListItem exemptStudiesCheckListItem) {
        this.exemptStudiesCheckListItem = exemptStudiesCheckListItem;
    }

    public ExemptStudiesCheckListItem getExemptStudiesCheckListItem() {
        if (exemptStudiesCheckListItem == null && StringUtils.isNotBlank(exemptStudiesCheckListCode)) {
            refreshReferenceObject("exemptStudiesCheckListItem");
        }
        return exemptStudiesCheckListItem;
    }

    @Override
    public void resetPersistenceState() {
    	protocolExemptCheckListId = null;
    }

}
