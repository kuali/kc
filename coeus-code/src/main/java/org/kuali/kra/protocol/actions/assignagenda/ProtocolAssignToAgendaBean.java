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
