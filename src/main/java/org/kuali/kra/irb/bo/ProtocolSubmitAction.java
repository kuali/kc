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
package org.kuali.kra.irb.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.kns.bo.BusinessObjectBase;

import edu.emory.mathcs.backport.java.util.Collections;

@SuppressWarnings("serial")
public class ProtocolSubmitAction extends BusinessObjectBase {

    private String submissionTypeCode = "";
    private String protocolReviewTypeCode = "";
    private String submissionQualifierTypeCode = "";
    private String committeeId = "";
    private String scheduleId = "";
    private List<ProtocolReviewer> reviewers = new ArrayList<ProtocolReviewer>();
    
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
    
    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }
    
    public String getCommitteeId() {
        return committeeId;
    }
    
    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }
    
    public String getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    @SuppressWarnings("unchecked")
    public List<ProtocolReviewer> getReviewers() {
        return Collections.unmodifiableList(reviewers);
    }
    
    public void addReviewer(ProtocolReviewer reviewer) {
        reviewers.add(reviewer);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("submissionTypeCode", getSubmissionTypeCode());
        map.put("protocolReviewTypeCode", getProtocolReviewTypeCode());
        map.put("submissionQualifierTypeCode", getSubmissionQualifierTypeCode());
        map.put("committeeId", getCommitteeId());
        map.put("scheduleId", getScheduleId());
        return map;
    }

    public void refresh() {
        // do nothing
    } 
}
