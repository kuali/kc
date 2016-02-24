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
package org.kuali.kra.irb.actions.assignagenda;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;

/**
 * This class is really just a "form" for assigning a protocol to an agenda.
 */
public class ProtocolAssignToAgendaBean extends ProtocolGenericActionBean implements org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaBean {

    private static final long serialVersionUID = -1671485882883282877L;
    
    private String committeeId = "";
    private String committeName = "";
    private String scheduleDate = "";
    private boolean protocolAssigned;

    private transient ProtocolAssignToAgendaService agendaService;

    /**
     * Constructs a ProtocolAssignToAgendaBean.
     * @param actionHelper a reference back to the parent helper
     */
    public ProtocolAssignToAgendaBean(ActionHelper actionHelper) {
        super(actionHelper, Constants.PROTOCOL_ASSIGN_TO_AGENDA_ACTION_PROPERTY_KEY);
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }


    public void setCommitteName(String committeName) {
        this.committeName = committeName;
    }


    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }


    public void setProtocolAssigned(boolean protocolAssigned) {
        this.protocolAssigned = protocolAssigned;
    }

    private ProtocolAssignToAgendaService getProtocolAssigntoAgendaService() {
        if (this.agendaService == null){
            this.agendaService = KcServiceLocator.getService(ProtocolAssignToAgendaService.class);
        }
        return this.agendaService;
    }


    public String getCommitteeId() {
        return committeeId;
    }


    public String getCommitteName() {
        return committeName;
    }


    public String getScheduleDate() {
        return scheduleDate;
    }


    public boolean isProtocolAssigned() {
        return protocolAssigned;
    }

    /**
     * Prepare the Assign to Committee and Schedule for rendering with JSP.
     */
    public void prepareView() {
        if (getProtocol() != null && getProtocol().getProtocolNumber() != null) {
            // we refresh assign-to-agenda data (committee name, comments etc) from db only if the user is not 
            // currently working on this task since we do not want to lose user changes
            if( !(TaskName.ASSIGN_TO_AGENDA.equalsIgnoreCase(getActionHelper().getCurrentTask())) ) {
                String assignedCommitteeId = getProtocolAssigntoAgendaService().getAssignedCommitteeId(getProtocol());
                if (assignedCommitteeId != null) {
                    this.committeeId = assignedCommitteeId;
                    this.committeName = getProtocolAssigntoAgendaService().getAssignedCommitteeName(getProtocol());
                    this.setComments(getProtocolAssigntoAgendaService().getAssignToAgendaComments(getProtocol()));
                    this.protocolAssigned = getProtocolAssigntoAgendaService().isAssignedToAgenda(getProtocol());
                    this.scheduleDate = getProtocolAssigntoAgendaService().getAssignedScheduleDate(getProtocol());
                }
            }
        }
    }
    
    /**
     * 
     * This method returns the appropriate printable for this class
     * @return a Printable
     */
    public Printable getCorrespondence() {
        AssignToAgendaCorrespondence correspondence = new AssignToAgendaCorrespondence();
        return correspondence;
    }
}
