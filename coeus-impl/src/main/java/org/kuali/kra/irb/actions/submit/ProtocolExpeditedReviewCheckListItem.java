/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

@SuppressWarnings("serial")
public class ProtocolExpeditedReviewCheckListItem extends KcPersistableBusinessObjectBase {

    private Long protocolExpeditedCheckListId;

    private Long protocolId;

    private Long submissionIdFk;

    private String protocolNumber;

    private Integer sequenceNumber;

    private Integer submissionNumber;

    private String expeditedReviewCheckListCode;

    private Protocol protocol;

    private ProtocolSubmission protocolSubmission;

    private ExpeditedReviewCheckListItem expeditedReviewCheckListItem;

    public ProtocolExpeditedReviewCheckListItem() {
    }

    public Long getProtocolExpeditedCheckListId() {
        return protocolExpeditedCheckListId;
    }

    public void setProtocolExpeditedCheckListId(Long protocolExpeditedCheckListId) {
        this.protocolExpeditedCheckListId = protocolExpeditedCheckListId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getExpeditedReviewCheckListCode() {
        return expeditedReviewCheckListCode;
    }

    public void setExpeditedReviewCheckListCode(String expeditedReviewCheckListCode) {
        this.expeditedReviewCheckListCode = expeditedReviewCheckListCode;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public ExpeditedReviewCheckListItem getExpeditedReviewCheckListItem() {
        if (expeditedReviewCheckListItem == null && StringUtils.isNotBlank(expeditedReviewCheckListCode)) {
            refreshReferenceObject("expeditedReviewCheckListItem");
        }
        return expeditedReviewCheckListItem;
    }

    public void setExpeditedReviewCheckListItem(ExpeditedReviewCheckListItem expeditedReviewCheckListItem) {
        this.expeditedReviewCheckListItem = expeditedReviewCheckListItem;
    }
}
