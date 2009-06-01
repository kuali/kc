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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.drools.brms.FactBean;
import org.kuali.kra.irb.Protocol;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolActionMapping implements FactBean {
    
    private BusinessObjectService businessObjectService;
    
    String submissionStatusCode;
    
    String submissionTypeCode;
    
    String protocolReviewTypeCode;
    
    String actionTypeCode;
    
    String protocolStatusCode;
    
    String scheduleId;
    
    Protocol protocol;
    
    Integer submissionNumber;

    boolean allowed = false;

    public ProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super();
        this.actionTypeCode = actionTypeCode;
        this.submissionStatusCode = submissionStatusCode;        
        this.submissionTypeCode = submissionTypeCode;
        this.protocolReviewTypeCode = protocolReviewTypeCode;
        this.protocolStatusCode = protocolStatusCode;
        this.scheduleId = scheduleId;
        this.submissionNumber = submissionNumber;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    public boolean getMinutesCount() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocol.getProtocolNumber());
        fieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
        int count = businessObjectService.countMatching(CommitteeScheduleMinutes.class, fieldValues);
        boolean flag = false;
        if(count > 0)
            flag = true;
        return flag;
    }
        
    public boolean getSubmissionCount() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
        positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<String> ors = new ArrayList<String>();
        ors.add("101");
        ors.add("101"); 
        ors.add("102");
        positiveFieldValues.put("submissionStatusCode", ors);
        
        Map<String, Object> negativeFieldValues = new HashMap<String, Object>();        
        negativeFieldValues.put("submissionTypeCode", submissionTypeCode);
        
        int count = businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues);
        boolean flag = false;
        if(count > 0)
            flag = true;
        return flag;
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
}
