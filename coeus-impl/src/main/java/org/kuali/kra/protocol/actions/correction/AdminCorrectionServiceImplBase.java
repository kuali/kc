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
package org.kuali.kra.protocol.actions.correction;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.core.api.util.xml.XmlJotter;
import org.kuali.rice.ken.api.service.SendNotificationService;
import org.kuali.rice.ken.util.Util;
import org.kuali.rice.krad.util.GlobalVariables;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

public abstract class AdminCorrectionServiceImplBase implements AdminCorrectionService {
    private SendNotificationService sendNotificationService;
    private List<String> notificationTemplates;
    private static final String DOC_LINK = "<a title=\"\" target=\"_self\" href=\"../kew/DocHandler.do?command=displayDocSearchView&amp;docId=";
    
    public void sendCorrectionNotification(ProtocolBase protocol, AdminCorrectionBean adminCorrectionBean) throws Exception {
        String adminCorrectionNotificationTemplate = notificationTemplates.get(0);
        InputStream is = this.getClass().getResourceAsStream(adminCorrectionNotificationTemplate);
        Document notificationRequestDocument;

        try {
            notificationRequestDocument = Util.parse(new InputSource(is), false, false, null);
            Element recipientUser = (Element) notificationRequestDocument.getElementsByTagName("user").item(0);
            ProtocolPersonBase principalInvestigator = protocol.getPrincipalInvestigator();
            if (!principalInvestigator.isNonEmployee()) {
                recipientUser.setTextContent(principalInvestigator.getPerson().getUserName());
            } else {
                recipientUser.setTextContent(principalInvestigator.getRolodex().getFullName());
            }

            Element sender = (Element) notificationRequestDocument.getElementsByTagName("sender").item(0);
            sender.setTextContent(GlobalVariables.getUserSession().getPrincipalName());

            Element message = (Element) notificationRequestDocument.getElementsByTagName("message").item(0);
            message.setTextContent("The IRB ProtocolBase " + DOC_LINK + protocol.getProtocolDocument().getDocumentNumber() + "\">" 
                    + protocol.getProtocolNumber() + "</a> has administrative correction made to it. <br/>" 
                    + " Comments : " + adminCorrectionBean.getComments());

            Element title = (Element) notificationRequestDocument.getElementsByTagName("title").item(0);
            title.setTextContent("Administrative Correction has been made to ProtocolBase " + protocol.getProtocolNumber());

            Element sendDateTime = (Element) notificationRequestDocument.getElementsByTagName("sendDateTime").item(0);
            sendDateTime.setTextContent(Util.toXSDDateTimeString(Calendar.getInstance().getTime()));
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        String XML = XmlJotter.jotNode(notificationRequestDocument, true);
        //Waiting for rice KEN bootstrap to be corrected
        sendNotificationService.invoke(XML);
    }

    public void setSendNotificationService(SendNotificationService sendNotificationService) {
        this.sendNotificationService = sendNotificationService;
    }

    public void setNotificationTemplates(List<String> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }


}
