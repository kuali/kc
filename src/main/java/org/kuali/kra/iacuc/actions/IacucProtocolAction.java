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
package org.kuali.kra.iacuc.actions;

import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class IacucProtocolAction extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8406562215380399942L;
    private Long iacucProtocolActionsId; 
    private String protocolNumber; 
    private Integer sequenceNumber; 
    private Integer actionId; 
    private Integer protocolActionTypeCode; 
    private Integer submissionNumber; 
    private String comments; 
    private Date actionDate; 
    
    private IacucProtocolActionType iacucProtocolActionType; 
    
    public IacucProtocolAction() { 

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

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(Integer protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Long getIacucProtocolActionsId() {
        return iacucProtocolActionsId;
    }

    public void setIacucProtocolActionsId(Long iacucProtocolActionsId) {
        this.iacucProtocolActionsId = iacucProtocolActionsId;
    }

    public IacucProtocolActionType getIacucProtocolActionType() {
        return iacucProtocolActionType;
    }

    public void setIacucProtocolActionType(IacucProtocolActionType iacucProtocolActionType) {
        this.iacucProtocolActionType = iacucProtocolActionType;
    }

    
}