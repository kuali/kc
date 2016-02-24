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
package org.kuali.kra.irb.actions.expeditedapprove;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;

import java.sql.Date;

/**
 * This class is really just a "form" for approving a protocol.
 */
public class ProtocolExpeditedApproveBean extends ProtocolApproveBean {

    private static final long serialVersionUID = -3437601491009148559L;
    
    private boolean assignToAgenda;
    private Date agendaDate;
    private String committeeName = "";
    private String scheduleId = "";
    
    private transient ProtocolAssignToAgendaService agendaService;

    /**
     * Constructs a ProtocolExpeditedApproveBean.
     * @param actionHelper a reference back to the parent helper
     */
    public ProtocolExpeditedApproveBean(ActionHelper actionHelper) {
        super(actionHelper, Constants.PROTOCOL_EXPEDITED_APPROVAL_ACTION_PROPERTY_KEY);
        assignToAgenda = false;
    }

    /**
     * Prepare the Assign to Committee and Schedule for rendering with JSP.
     */
    public void prepareView() {
        if (getProtocol() != null && getProtocol().getProtocolNumber() != null) {
            boolean assignedToAgenda = getProtocolAssigntoAgendaService().isAssignedToAgenda(getProtocol());
            // we refresh assign-to-agenda data (committee name, comments etc) from db only if the user is not 
            // currently working on this task since we do not want to lose user changes
            if( !(TaskName.EXPEDITE_APPROVAL.equalsIgnoreCase(getActionHelper().getCurrentTask())) ) {
                // refresh committee and schedule data from db only if protocol has been submitted but not yet in agenda, and set the checkbox flag
                if (StringUtils.equals(getProtocol().getProtocolSubmission().getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                    this.committeeName = getProtocolAssigntoAgendaService().getAssignedCommitteeName(getProtocol());
                    this.assignToAgenda = true;
                    this.scheduleId = getProtocolAssignCmtSchedService().getAssignedScheduleId(getProtocol());
                }
                // otherwise if protocol is in agenda then only refresh the agenda comments (and unset the agenda checkbox flag), 
                // but not the committee name or schedule, we will let their values from previous refreshes be carried over.
                else if(assignedToAgenda) {
                    this.setComments(getProtocolAssigntoAgendaService().getAssignToAgendaComments(getProtocol()));
                    this.assignToAgenda = false;
                }
            }
        }
    }

    public boolean isAssignToAgenda() {
        return assignToAgenda;
    }

    public void setAssignToAgenda(boolean assignToAgenda) {
        this.assignToAgenda = assignToAgenda;
    }

    public Date getAgendaDate() {
        return agendaDate;
    }

    public void setAgendaDate(Date agendaDate) {
        this.agendaDate = agendaDate;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    private ProtocolAssignToAgendaService getProtocolAssigntoAgendaService() {
        if (this.agendaService == null){
            this.agendaService = KcServiceLocator.getService(ProtocolAssignToAgendaService.class);
        }
        return this.agendaService;
    }

    private ProtocolAssignCmtSchedService getProtocolAssignCmtSchedService() {
        return KcServiceLocator.getService(ProtocolAssignCmtSchedService.class);
    }
    

}
