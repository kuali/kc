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