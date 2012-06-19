/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.sql.Date;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;

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
            String assignedCommitteeId = getProtocolAssigntoAgendaService().getAssignedCommitteeId(getProtocol());
            if (assignedCommitteeId != null) {
                this.committeeName = getProtocolAssigntoAgendaService().getAssignedCommitteeName(getProtocol());
                this.setComments(getProtocolAssigntoAgendaService().getAssignToAgendaComments(getProtocol()));
                this.assignToAgenda = getProtocolAssigntoAgendaService().isAssignedToAgenda(getProtocol());
                this.scheduleId = getProtocolAssignCmtSchedService().getAssignedScheduleId(getProtocol());
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
            this.agendaService = KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
        }
        return this.agendaService;
    }

    private ProtocolAssignCmtSchedService getProtocolAssignCmtSchedService() {
        return KraServiceLocator.getService(ProtocolAssignCmtSchedService.class);
    }
    

}