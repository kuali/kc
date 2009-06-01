/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.submit;

import java.util.LinkedHashMap;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity 
@Table(name="COMM_SCHEDULE_MINUTES")
public class CommitteeScheduleMinutes extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = -2294619582524055884L;

    @Id 
    @Column(name="ID")
    private Integer id; 

    @Column(name="SCHEDULE_ID_FK")
    private Integer scheduleIdFk; 

    @Column(name="SCHEDULE_ID")
    private String scheduleId; 

    @Column(name="ENTRY_NUMBER")
    private Integer entryNumber; 

    @Column(name="MINUTE_ENTRY_TYPE_CODE")
    private Integer minuteEntryTypeCode; 

    @Column(name="PROTOCOL_ID_FK")
    private Integer protocolIdFk; 

    @Column(name="PROTOCOL_NUMBER")
    private String protocolNumber; 

    @Column(name="SEQUENCE_NUMBER")
    private Integer sequenceNumber; 

    @Column(name="SUBMISSION_ID_FK")
    private Integer submissionIdFk; 

    @Column(name="SUBMISSION_NUMBER")
    private Integer submissionNumber; 

    @Type(type="yes_no")
    @Column(name="PRIVATE_COMMENT_FLAG")
    private boolean privateCommentFlag; 

    @Column(name="PROTOCOL_CONTINGENCY_CODE")
    private String protocolContingencyCode; 

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="MINUTE_ENTRY")
    private String minuteEntry; 

    public CommitteeScheduleMinutes() { 

    } 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Integer scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public Integer getMinuteEntryTypeCode() {
        return minuteEntryTypeCode;
    }

    public void setMinuteEntryTypeCode(Integer minuteEntryTypeCode) {
        this.minuteEntryTypeCode = minuteEntryTypeCode;
    }

    public Integer getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Integer protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
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

    public Integer getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Integer submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public boolean getPrivateCommentFlag() {
        return privateCommentFlag;
    }

    public void setPrivateCommentFlag(boolean privateCommentFlag) {
        this.privateCommentFlag = privateCommentFlag;
    }

    public String getProtocolContingencyCode() {
        return protocolContingencyCode;
    }

    public void setProtocolContingencyCode(String protocolContingencyCode) {
        this.protocolContingencyCode = protocolContingencyCode;
    }

    public String getMinuteEntry() {
        return minuteEntry;
    }

    public void setMinuteEntry(String minuteEntry) {
        this.minuteEntry = minuteEntry;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("id", this.getId());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("scheduleId", this.getScheduleId());
        hashMap.put("entryNumber", this.getEntryNumber());
        hashMap.put("minuteEntryTypeCode", this.getMinuteEntryTypeCode());
        hashMap.put("protocolIdFk", this.getProtocolIdFk());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("submissionIdFk", this.getSubmissionIdFk());
        hashMap.put("submissionNumber", this.getSubmissionNumber());
        hashMap.put("privateCommentFlag", this.getPrivateCommentFlag());
        hashMap.put("protocolContingencyCode", this.getProtocolContingencyCode());
        hashMap.put("minuteEntry", this.getMinuteEntry());
        return hashMap;
    }
    
}