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

/*
 * This is the condition attributes to decide whether user has permission to perform an action
 */
public class ActionRightMapping implements FactBean {
    
    private String actionTypeCode;
    private String rightId;
    private String unitIndicator;
    private String committeeId;
    private String scheduleId;  
    private boolean allowed;
    
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }
    public String getRightId() {
        return rightId;
    }
    public void setRightId(String rightId) {
        this.rightId = rightId;
    }
    public String getUnitIndicator() {
        return unitIndicator;
    }
    public void setUnitIndicator(String unitIndicator) {
        this.unitIndicator = unitIndicator;
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
    public boolean isAllowed() {
        return allowed;
    }
    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }    
}
