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
package org.kuali.kra.irb.actions.assigncmtsched;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;

import java.io.Serializable;

/**
 * This class is really just a "form" for assigning a protocol
 * to a committee and schedule.
 */
public class ProtocolAssignCmtSchedBean extends ProtocolActionBean implements Serializable {
    
    private static final long serialVersionUID = 4765501993140678114L;
    
    private String committeeId = "";
    private String scheduleId = "";
    
    private String newCommitteeId = "";
    private String newScheduleId = "";
    
    /**
     * Constructs a ProtocolAssignCmtSchedBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolAssignCmtSchedBean(ActionHelper actionHelper) {
        super(actionHelper);
    }
    
    public void init() {
        String committeeId = getProtocolAssignCmtSchedService().getAssignedCommitteeId(getProtocol());
        if (committeeId != null) {
            this.newCommitteeId = committeeId;
            this.committeeId = committeeId;
            String scheduleId = getProtocolAssignCmtSchedService().getAssignedScheduleId(getProtocol());
            if (scheduleId != null) {
                this.newScheduleId = scheduleId;
                this.scheduleId = scheduleId;
            }
        }
    }
    
    private ProtocolAssignCmtSchedService getProtocolAssignCmtSchedService() {
        return KcServiceLocator.getService(ProtocolAssignCmtSchedService.class);
    }
    
    /**
     * Prepare the Assign to Committee and Schedule for rendering with JSP.
     */
    public void prepareView() {
        /*
         * The Assign to Committee and Schedule has to work with and without JavaScript.
         * When JavaScript is enabled, the newly selected committee and schedule
         * are what we want to continue to display.  When JavaScript is disabled,
         * we have to change the schedule dates that we display if the committee
         * has changed.
         */
        if (getActionHelper().getProtocolForm().isJavaScriptEnabled()) {
            committeeId = newCommitteeId;
            scheduleId = newScheduleId;
        } 
        else {
            if (!StringUtils.equals(committeeId, newCommitteeId)) {
                committeeId = newCommitteeId;
                scheduleId = "";
            }
            else if (!StringUtils.equals(scheduleId, newScheduleId)) {
                scheduleId = newScheduleId;
            }
        }
    }
    
    public String getCommitteeId() {
        return committeeId;
    }
    
    public void setCommitteeId(String committeeId) {
        this.newCommitteeId = committeeId;
    }
    
    public String getNewCommitteeId() {
        return newCommitteeId;
    } 
    
    public String getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(String scheduleId) {
        this.newScheduleId = scheduleId;
    }
    
    public String getNewScheduleId() {
        return newScheduleId;
    }
    
    public boolean committeeHasChanged() {
        return !StringUtils.equals(getNewCommitteeId(), getCommitteeId());
    }
    
    public boolean scheduleHasChanged() {
        //depends on both committee and schedule I think
        return committeeHasChanged() || (!StringUtils.equals(getScheduleId(), getNewScheduleId()));
    }
}
