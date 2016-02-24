/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.protocol.drools.brms.FactBean;

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
