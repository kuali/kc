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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@SuppressWarnings("serial")
public class ProtocolExemptStudiesCheckListItem extends KraPersistableBusinessObjectBase {

    private Long protocolExemptCheckListId;

    private Long protocolId;

    private Long submissionIdFk;

    private String protocolNumber;

    private Integer sequenceNumber;

    private Integer submissionNumber;

    private String exemptStudiesCheckListCode;

    private Protocol protocol;

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

    public String getExemptStudiesCheckListCode() {
        return exemptStudiesCheckListCode;
    }

    public void setExemptStudiesCheckListCode(String exemptStudiesCheckListCode) {
        this.exemptStudiesCheckListCode = exemptStudiesCheckListCode;
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

    public void setExemptStudiesCheckListItem(ExemptStudiesCheckListItem exemptStudiesCheckListItem) {
        this.exemptStudiesCheckListItem = exemptStudiesCheckListItem;
    }

    public ExemptStudiesCheckListItem getExemptStudiesCheckListItem() {
        if (exemptStudiesCheckListItem == null && StringUtils.isNotBlank(exemptStudiesCheckListCode)) {
            refreshReferenceObject("exemptStudiesCheckListItem");
        }
        return exemptStudiesCheckListItem;
    }
}
