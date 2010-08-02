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
package org.kuali.kra.irb.actions.notification;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.w3c.dom.Element;

/**
 * 
 * This class is for request for data analysis notification event
 */
public class DataAnalysisEvent extends NotificationEventBase {


    public DataAnalysisEvent() {
    }

    public DataAnalysisEvent(Protocol protocol) {
        super(protocol);
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getRecipients(org.w3c.dom.Element)
     */
    public void getRecipients(Element recipients) {
        super.getRecipients(recipients);
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getTitle()
     */
    public String getTitle() {
        return "Protocol " + getProtocol().getProtocolNumber() + " Data Analysis";
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getMessage()
     */
    public String getMessage() {
        ProtocolPerson pi = getProtocol().getPrincipalInvestigator();
        String piName;
        if (StringUtils.isNotBlank(pi.getPersonId())) {
            piName = pi.getPerson().getFirstName() + " " + pi.getPerson().getLastName();
        }
        else {
            piName = pi.getRolodex().getFirstName() + " " + pi.getRolodex().getLastName();
        }
        String messageBody = "The IRB protocol number " + getProtocol().getProtocolNumber() + ", Principal Investigator " + piName
                + " is Data Analysis";
        return messageBody;

    }

    public String getTemplatePath() {
        // TODO Auto-generated method stub
        return "/org/kuali/kra/irb/notification/stylesheet/DataAnalysisNotification.xsl";
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY;
    }

}
