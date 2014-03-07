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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.protocol.drools.brms.FactBean;

/**
 * Defines the Drools mapping for determining whether a certain action should be opened as a followup action to a previously submitted action.
 */
public class ProtocolActionFollowupMapping implements FactBean {
    
    private String protocolActionTypeCode;
    
    private String committeeDecisionMotionTypeCode;
    
    private boolean isOpenForFollowup;
    
    /**
     * Constructs a ProtocolActionFollowupMapping.
     * @param protocolActionTypeCode The code for the protocol action
     * @param committeeDecisionMotionTypeCode The code for the committee decision motion type
     */
    public ProtocolActionFollowupMapping(String protocolActionTypeCode, String committeeDecisionMotionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
        this.committeeDecisionMotionTypeCode = committeeDecisionMotionTypeCode;
    }

    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(String protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public String getCommitteeDecisionMotionTypeCode() {
        return committeeDecisionMotionTypeCode;
    }

    public void setCommitteeDecisionMotionTypeCode(String committeeDecisionMotionTypeCode) {
        this.committeeDecisionMotionTypeCode = committeeDecisionMotionTypeCode;
    }

    public boolean getIsOpenForFollowup() {
        return isOpenForFollowup;
    }

    public void setIsOpenForFollowup(boolean isOpenForFollowup) {
        this.isOpenForFollowup = isOpenForFollowup;
    }

}