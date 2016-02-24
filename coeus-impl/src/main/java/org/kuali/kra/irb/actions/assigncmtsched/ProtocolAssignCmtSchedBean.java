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
