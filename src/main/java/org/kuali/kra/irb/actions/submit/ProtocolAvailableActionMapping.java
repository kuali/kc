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
import java.util.List;

import org.kuali.kra.drools.brms.FactBean;

public class ProtocolAvailableActionMapping implements FactBean {
    
    String submissionTypeCode;
    
    List<String> actionTypeCodes;
    
    public ProtocolAvailableActionMapping(String submissionTypeCode) {
        super();
        this.submissionTypeCode = submissionTypeCode;
        this.actionTypeCodes = new ArrayList<String>();
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public List<String> getActionTypeCodes() {
        return actionTypeCodes;
    }

    public void setActionTypeCodes(List<String> actionTypeCodes) {
        this.actionTypeCodes = actionTypeCodes;
    }

}
