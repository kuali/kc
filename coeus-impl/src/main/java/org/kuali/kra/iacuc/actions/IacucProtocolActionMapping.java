/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.kra.protocol.drools.brms.FactBean;
import org.kuali.kra.protocol.ProtocolBase;

public class IacucProtocolActionMapping implements FactBean {
        
    String submissionStatusCode;
    
    String submissionTypeCode;
    
    String protocolReviewTypeCode;
    
    String actionTypeCode;
    
    String protocolStatusCode;
    
    String scheduleId;
    
    ProtocolBase protocol;
    
    Integer submissionNumber;

    boolean allowed = false;

    public IacucProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super();
        this.actionTypeCode = actionTypeCode;
        this.submissionStatusCode = submissionStatusCode;        
        this.submissionTypeCode = submissionTypeCode;
        this.protocolReviewTypeCode = protocolReviewTypeCode;
        this.protocolStatusCode = protocolStatusCode;
        this.scheduleId = scheduleId;
        this.submissionNumber = submissionNumber;
    }
    
    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }
    
    
    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getSubmissionStatusCode() {
        return submissionStatusCode;
    }

    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    
    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }    
    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }
    

}
