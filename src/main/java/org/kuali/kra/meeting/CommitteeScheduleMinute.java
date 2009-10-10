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
package org.kuali.kra.meeting;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@Entity 
@Table(name="COMM_SCHEDULE_MINUTES")
public class CommitteeScheduleMinute extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = -2294619582524055884L;

    @Id 
    @Column(name="COMM_SCHEDULE_MINUTES_ID")
    private Long commScheduleMinutesId; 

    @Column(name="SCHEDULE_ID_FK")
    private Long scheduleIdFk; 

    @Column(name="ENTRY_NUMBER")
    private Integer entryNumber; 

    @Column(name="MINUTE_ENTRY_TYPE_CODE")
    private String minuteEntryTypeCode; 

    @Column(name="PROTOCOL_CONTINGENCY_CODE")
    private String protocolContingencyCode; 

    @Column(name="PROTOCOL_ID_FK")
    private Integer protocolIdFk; 

 
    @Column(name="SUBMISSION_ID_FK")
    private Integer submissionIdFk; 

    @Type(type="yes_no")
    @Column(name="PRIVATE_COMMENT_FLAG")
    private boolean privateCommentFlag; 

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_CONTINGENCY_CODE", insertable=false, updatable=false)
    private ProtocolContingency protocolContingency;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MINUTE_ENTRY_TYPE_CODE", insertable=false, updatable=false)
    private MinuteEntryType minuteEntryType;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="MINUTE_ENTRY")
    private String minuteEntry; 
    
    // TODO : not sure how this protocols yet.
    @SkipVersioning
    private List<Protocol> protocols;

    // Transient field 
    @Transient
    private boolean generateAttendance = false;
    
    public CommitteeScheduleMinute() { 

    } 
    
    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getMinuteEntryTypeCode() {
        return minuteEntryTypeCode;
    }

    public void setMinuteEntryTypeCode(String minuteEntryTypeCode) {
        this.minuteEntryTypeCode = minuteEntryTypeCode;
    }

    public Integer getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Integer protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
    }


    public Integer getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Integer submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
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
        hashMap.put("commScheduleMinutesId", this.getCommScheduleMinutesId());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("entryNumber", this.getEntryNumber());
        hashMap.put("minuteEntryTypeCode", this.getMinuteEntryTypeCode());
        hashMap.put("protocolIdFk", this.getProtocolIdFk());
        hashMap.put("submissionIdFk", this.getSubmissionIdFk());
        hashMap.put("privateCommentFlag", this.getPrivateCommentFlag());
        hashMap.put("protocolContingencyCode", this.getProtocolContingencyCode());
        hashMap.put("minuteEntry", this.getMinuteEntry());
        return hashMap;
    }

    public Long getCommScheduleMinutesId() {
        return commScheduleMinutesId;
    }

    public void setCommScheduleMinutesId(Long commScheduleMinutesId) {
        this.commScheduleMinutesId = commScheduleMinutesId;
    }

    public ProtocolContingency getProtocolContingency() {
        return protocolContingency;
    }

    public void setProtocolContingency(ProtocolContingency protocolContingency) {
        this.protocolContingency = protocolContingency;
    }

    public MinuteEntryType getMinuteEntryType() {
        return minuteEntryType;
    }

    public void setMinuteEntryType(MinuteEntryType minuteEntryType) {
        this.minuteEntryType = minuteEntryType;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    public boolean isGenerateAttendance() {
        return generateAttendance;
    }

    public void setGenerateAttendance(boolean generateAttendance) {
        this.generateAttendance = generateAttendance;
    }


}