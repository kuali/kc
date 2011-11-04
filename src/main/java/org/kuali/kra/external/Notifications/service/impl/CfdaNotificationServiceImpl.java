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
package org.kuali.kra.external.Notifications.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.kuali.kra.external.Notifications.service.CfdaNotificationService;
import org.kuali.rice.ken.exception.InvalidXMLException;
import org.kuali.rice.ken.service.NotificationService;
import org.kuali.rice.ken.util.Util;
import org.kuali.rice.kew.util.XmlHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CfdaNotificationServiceImpl implements CfdaNotificationService{
    private List<String> notificationTemplates;
    private NotificationService notificationService;
    private String message;
    private String title;
    private String recipient;
    private String sender;

    /**
     * @see org.kuali.kra.external.Notifications.service.CfdaNotificationService#sendNotification()
     */
    public void sendNotification() throws ParserConfigurationException, IOException, SAXException, TransformerException, InvalidXMLException {
        // TODO Auto-generated method stub
        String actionNotificationTemplate = notificationTemplates.get(0);
        InputStream inputStream = this.getClass().getResourceAsStream(actionNotificationTemplate);
        Document notificationRequestDocument;

        try {
            notificationRequestDocument = Util.parse(new InputSource(inputStream), false, false, null);
            Element recipients = (Element) notificationRequestDocument.getElementsByTagName("recipients").item(0);
            XmlHelper.appendXml(recipients, "<user>" + getRecipient() + "</user>");
            
            Element senderElement = (Element) notificationRequestDocument.getElementsByTagName("sender").item(0);
            senderElement.setTextContent(getSender());
            
            Element messageElement = (Element) notificationRequestDocument.getElementsByTagName("message").item(0);
                         
            messageElement.setTextContent(getMessage());

            Element titleElement = (Element) notificationRequestDocument.getElementsByTagName("title").item(0);
            titleElement.setTextContent(getTitle());

            Element sendDateTimeElement = (Element) notificationRequestDocument.getElementsByTagName("sendDateTime").item(0);
            sendDateTimeElement.setTextContent(Util.toXSDDateTimeString(Calendar.getInstance().getTime()));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        String XML = Util.writeNode(notificationRequestDocument, true);
        // Waiting for rice KEN bootstrap to be corrected
        notificationService.sendNotification(XML);
        
    }
    
    public void setNotificationTemplates(List<String> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }
    
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
        // TODO Auto-generated method stub
        
    }

    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getRecipient() {
        return recipient;    
    }
    
    public void setRecipient(String recipient) {
        // TODO Auto-generated method stub
        this.recipient = recipient; 
    }

}
