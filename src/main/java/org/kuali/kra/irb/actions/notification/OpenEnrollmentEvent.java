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
import org.apache.log4j.Logger;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kew.util.XmlHelper;
import org.w3c.dom.Element;

/**
 * 
 * This class is for request to re-open enrollment notification event
 */
public class OpenEnrollmentEvent extends NotificationEventBase {

    private static final Logger LOG = Logger.getLogger(OpenEnrollmentEvent.class);

    public OpenEnrollmentEvent() {
    }

    public OpenEnrollmentEvent(Protocol protocol) {
        super(protocol);
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getRecipients(org.w3c.dom.Element)
     */
    public void getRecipients(Element recipients) {
        try {
            if (StringUtils.isNotBlank(getProtocol().getPrincipalInvestigator().getPersonId())) {
                // rolodex does not have username
                XmlHelper.appendXml(recipients, "<user>" + getProtocol().getPrincipalInvestigator().getPerson().getUserName()
                        + "</user>");
                // recipientUser.setTextContent(protocol.getPrincipalInvestigator().getPerson().getUserName());
            }
        }
        catch (Exception e) {
            LOG.info("Open Enrollment - get recipeint - exception " + e.getStackTrace());

        }
        getProtocolActionsNotificationService().addIrbAdminToRecipients(recipients, getProtocol());
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getTitle()
     */
    public String getTitle() {
        return "Protocol " + getProtocol().getProtocolNumber() + " Open Enrollment";
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
                + " is Open Enrollment";
        return messageBody;

    }

    private ProtocolActionsNotificationService getProtocolActionsNotificationService() {
        return KraServiceLocator.getService(ProtocolActionsNotificationService.class);
    }

    public String getTemplatePath() {
        // TODO Auto-generated method stub
        return "/org/kuali/kra/irb/notification/stylesheet/OpenEnrollmentNotification.xsl";
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT;
    }

}
