/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.HashMap;
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

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.hibernate.annotations.Type;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This is BO class for committee schedule minute. 
 */
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
    private Long protocolIdFk; 

 
    @Column(name="SUBMISSION_ID_FK")
    private Long submissionIdFk; 

    @Type(type="yes_no")
    @Column(name="PRIVATE_COMMENT_FLAG")
    private boolean privateCommentFlag; 
    
    @Type(type="yes_no")
    @Column(name="FINAL_FLAG")
    private boolean finalFlag; 
    
    @Column(name="REVIEWER_ID_FK")
    private Long protocolReviewerIdFk;

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_CONTINGENCY_CODE", insertable=false, updatable=false)
    private ProtocolContingency protocolContingency;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MINUTE_ENTRY_TYPE_CODE", insertable=false, updatable=false)
    private MinuteEntryType minuteEntryType;
    
    
    private ProtocolReviewer protocolReviewer;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="MINUTE_ENTRY")
    private String minuteEntry; 
    
    // TODO : not sure how this protocols yet.
    @SkipVersioning
    private List<Protocol> protocols;

    @SkipVersioning
    private Protocol protocol;

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

    public Long getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Long protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
    }


    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
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
        if (!StringUtils.isBlank(protocolContingencyCode) && getProtocolContingency() != null) {
            setMinuteEntry(getProtocolContingency().getDescription());
        }
    }

    public String getMinuteEntry() {
        return minuteEntry;
    }

    public void setMinuteEntry(String minuteEntry) {
        this.minuteEntry = minuteEntry;
    }
    
    @Override
    public String toString(){
        StringBuffer retVal = new StringBuffer(50);
        LinkedHashMap hm = toStringMapper();
        for(Object key : hm.keySet()){
            retVal.append(key.toString()).append(" : ");
            try{
                retVal.append(hm.get(key).toString());
            }catch(Exception e){
                retVal.append("NPE problem");
            }
            retVal.append("\n");
        }
        return retVal.toString();
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
        hashMap.put("finalFlag", this.isFinalFlag());
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
        if (StringUtils.isBlank(protocolContingencyCode)) {
            protocolContingency = null;
        }
        else if (protocolContingency == null || 
                 !StringUtils.equals(protocolContingencyCode, protocolContingency.getProtocolContingencyCode())) {
            refreshReferenceObject("protocolContingency");
        }
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

    public boolean isFinalFlag() {
        return finalFlag;
    }

    public void setFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Long getProtocolReviewerIdFk() {
        return protocolReviewerIdFk;
    }

    public void setProtocolReviewerIdFk(Long protocolReviewerIdFk) {
        this.protocolReviewerIdFk = protocolReviewerIdFk;
    }

    public ProtocolReviewer getProtocolReviewer() {
        return protocolReviewer;
    }

    public void setProtocolReviewer(ProtocolReviewer protocolReviewer) {
        this.protocolReviewer = protocolReviewer;
    }

    /**
     * Equality is based on minute id, minute entry value, entry number(order position)
     * and whether or not it is private.
     * This function is used to determine if a minute needs to be updated on the DB.
     * @param o a CommitteeScheduleMinute object
     * @return boolean if the passed in minute is the same as THIS minute.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        CommitteeScheduleMinute csm = (CommitteeScheduleMinute) o;
        return this.commScheduleMinutesId.equals(csm.getCommScheduleMinutesId()) 
            && this.getMinuteEntry().equals(csm.getMinuteEntry()) 
            && this.getEntryNumber().equals(csm.getEntryNumber()) 
            && this.getPrivateCommentFlag() == csm.getPrivateCommentFlag();
    }

    @Override
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        setUpdateUserIfModified();
        super.beforeUpdate(persistenceBroker);
    }
    
    private void setUpdateUserIfModified() {
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
        if (getCommScheduleMinutesId() != null) {
            HashMap <String, String> pkMap = new HashMap<String, String>();
            pkMap.put("commScheduleMinutesId", getCommScheduleMinutesId().toString());
            CommitteeScheduleMinute committeeScheduleMinute = (CommitteeScheduleMinute)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(this.getClass(), pkMap);
            if (!updateUser.equals(committeeScheduleMinute.getUpdateUser())) {
                if (!minuteEntry.equals(committeeScheduleMinute.getMinuteEntry())
                        || privateCommentFlag != committeeScheduleMinute.getPrivateCommentFlag()
                        || finalFlag != committeeScheduleMinute.isFinalFlag()
                        || isProtocolFieldChanged(committeeScheduleMinute)
                        ) {
                    this.setUpdateUser(updateUser);
                }                                    
            }
        } else {
            this.setUpdateUser(updateUser);            
        }
        setUpdateUserSet(true);
    }
    
    private boolean isProtocolFieldChanged(CommitteeScheduleMinute committeeScheduleMinute) {
        boolean isChanged = false;
        if (protocolIdFk != null && committeeScheduleMinute.getProtocolIdFk() != null) {
            if (protocolContingencyCode != null && committeeScheduleMinute.getProtocolContingencyCode() !=null
                    && !protocolContingencyCode.equals(committeeScheduleMinute.getProtocolContingencyCode())) {
                isChanged = true;
            } else if (!(protocolContingencyCode == null && committeeScheduleMinute.getProtocolContingencyCode() == null)) {
                isChanged = true;
            }
            
        } else if (!(protocolIdFk == null && committeeScheduleMinute.getProtocolIdFk() == null)) {
            isChanged = true;
        }
        return isChanged;
    }
    
    /**
     * 
     * This method returns true if the object has been saved to the database, and returns false if it has not.
     * @return a boolean
     */
    public boolean isPersisted() {
        return this.commScheduleMinutesId != null;
    }
    
    public Long getProtocolId() {
        if (this.protocol != null) {
            return this.protocol.getProtocolId();
        } else {
            return null;
        }
    }
}
