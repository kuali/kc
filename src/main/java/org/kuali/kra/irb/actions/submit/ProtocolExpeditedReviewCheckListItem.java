/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@SuppressWarnings("serial")
public class ProtocolExpeditedReviewCheckListItem extends KraPersistableBusinessObjectBase {

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
        return expeditedReviewCheckListItem;
    }

    public void setExpeditedReviewCheckListItem(ExpeditedReviewCheckListItem expeditedReviewCheckListItem) {
        this.expeditedReviewCheckListItem = expeditedReviewCheckListItem;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("protocolExemptCheckListId", getProtocolExpeditedCheckListId());
        map.put("protocolId", getProtocolId());
        map.put("submissionIdFk", getSubmissionIdFk());
        map.put("protocolNumber", getProtocolNumber());
        map.put("sequenceNumber", getSequenceNumber());
        map.put("submissionNumber", getSubmissionNumber());
        map.put("expeditedReviewCheckListCode", getExpeditedReviewCheckListCode());
        return map;
    }
}
