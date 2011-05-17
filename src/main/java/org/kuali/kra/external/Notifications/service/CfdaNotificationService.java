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
package org.kuali.kra.external.Notifications.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.kuali.rice.ken.exception.InvalidXMLException;
import org.xml.sax.SAXException;

/**
 * This class sends the CFDa notifications.
 */
public interface CfdaNotificationService {
    
    /**
     * This method sets the notification recipient
     * @param recipient
     */
    void setRecipient(String recipient);

    /**
     * This method sets message title
     * @param title
     */
    void setTitle(String title);
    
    /**
     * This method sets sender.
     * @param sender
     */
    void setSender(String sender);
    
    /**
     * This method sets the message body
     * @param message
     */
    void setMessage(String message);
    
    /**
     * This method sends the notification
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     * @throws InvalidXMLException
     */
    void sendNotification() throws ParserConfigurationException, IOException, SAXException, TransformerException, InvalidXMLException;
}
