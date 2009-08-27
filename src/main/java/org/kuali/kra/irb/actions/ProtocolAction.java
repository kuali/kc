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
package org.kuali.kra.irb.actions;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

@Entity 
@Table(name="PROTOCOL_ACTIONS")
public class ProtocolAction extends ProtocolAssociate { 

    private static final long serialVersionUID = -2148599171919464303L;
    
    private static final String NEXT_ACTION_ID_KEY = "actionId";
    
    @Id 
    @Column(name = "PROTOCOL_ACTION_ID")
    private Long protocolActionId;
    
    @Column(name="ACTION_ID")
    private Integer actionId; 
    
    @Column(name = "SUBMISSION_NUMBER")
    private Integer submissionNumber;
    
    @Column(name = "SUBMISSION_ID_FK")
    private Long submissionIdFk;
    
    @Column(name = "PROTOCOL_ACTION_TYPE_CODE")
    private String protocolActionTypeCode;
    
    @Column(name="COMMENTS")
    private String comments; 

    @Column(name="ACTION_DATE")
    private Timestamp actionDate;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_ID_FK", insertable=false, updatable=false)
    private ProtocolSubmission protocolSubmission;    
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ACTION_TYPE_CODE", insertable=false, updatable=false)
    private ProtocolActionType protocolActionType;
    
    public ProtocolAction() { 

    }
    
    public ProtocolAction(Protocol protocol, ProtocolSubmission protocolSubmission, String protocolActionTypeCode) {
        setProtocolId(protocol.getProtocolId());
        setProtocolNumber(protocol.getProtocolNumber());
        setSequenceNumber(0);
        setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        setActionDate(new Timestamp(System.currentTimeMillis()));
        setProtocolActionTypeCode(protocolActionTypeCode);
        if (protocolSubmission != null) {
            setSubmissionIdFk(protocolSubmission.getSubmissionId());
            setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        }
    }
    
    public Long getProtocolActionId() {
        return protocolActionId;
    }

    public void setProtocolActionId(Long protocolActionId) {
        this.protocolActionId = protocolActionId;
    }
    
    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
    
    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(String protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }
    
    public void setProtocolActionType(ProtocolActionType protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    public ProtocolActionType getProtocolActionType() {
        return protocolActionType;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("protocolActionId", getProtocolActionId());
        hashMap.put("actionId", this.getActionId());
        hashMap.put("protocolActionTypeCode", getProtocolActionTypeCode());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("submissionIdFk", getSubmissionIdFk());
        hashMap.put("comments", this.getComments());
        hashMap.put("actionDate", this.getActionDate());
        return hashMap;
    }

    public void resetPersistenceState() {
        protocolActionId = null;
        submissionIdFk = null;
    }

}