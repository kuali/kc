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
package org.kuali.kra.protocol.actions.assignagenda;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;

import java.io.Serializable;

/**
 * This class is really just a "form" for assigning a protocol to an agenda.
 */
public interface ProtocolAssignToAgendaBean extends ProtocolGenericActionBean, Serializable {


    public void setCommitteeId(String committeeId);

    public void setCommitteName(String committeName);


    public void setScheduleDate(String scheduleDate);


    public void setProtocolAssigned(boolean protocolAssigned);

    public String getCommitteeId();


    public String getCommitteName();


    public String getScheduleDate();


    public boolean isProtocolAssigned();

    /**
     * Prepare the Assign to Committee and Schedule for rendering with JSP.
     */
    public void prepareView();
    
    /**
     * 
     * This method returns the appropriate printable for this class
     * @return a Printable
     */
    public Printable getCorrespondence();
    
}
