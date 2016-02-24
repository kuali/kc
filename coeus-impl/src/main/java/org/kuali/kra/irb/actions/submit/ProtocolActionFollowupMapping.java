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
