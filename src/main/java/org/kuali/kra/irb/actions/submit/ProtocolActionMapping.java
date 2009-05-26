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

import org.kuali.kra.drools.brms.FactBean;

public class ProtocolActionMapping implements FactBean {
    
    String submissionStatusCode;
    
    String submissionTypeCode;
    
    String protocolReviewTypeCode;
    
    String actionTypeCode;
    
    String protocolStatusCode;

    boolean allowed = false;

    public ProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode) {
        super();
        this.actionTypeCode = actionTypeCode;
        this.submissionStatusCode = submissionStatusCode;        
        this.submissionTypeCode = submissionTypeCode;
        this.protocolReviewTypeCode = protocolReviewTypeCode;
        this.protocolStatusCode = protocolStatusCode;
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
    
    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
