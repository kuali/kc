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

import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.irb.Protocol;
import org.w3c.dom.Element;

/**
 * 
 * This class defines API for action notification
 */
public interface ProtocolActionsNotificationService {

    /**
     * 
     * This method to send out KEN notification
     * @param protocol
     * @param notificationEvent
     * @throws Exception
     */
    void sendActionsNotification(Protocol protocol, NotificationEventBase notificationEvent) throws Exception;

    /**
     * 
     * This method is to add IRB administrator to recipients list
     * @param recipients
     * @param protocol
     */
    void addIrbAdminToRecipients(Element recipients, Protocol protocol, List<String> userNames);

    /**
     * 
     * This method is to add the document initiator to the notification list
     * @param recipients
     * @param protocol
     */
    void addInitiatorToRecipients(Element recipients, Protocol protocol, List<String> userNames);
    
    /**
     * 
     * This method is to add the reviewers to the recipient list.  This is for 'assign to agenda',
     * 'assign reviewer' and 'review complete'
     * @param recipients
     * @param protocol
     * @param userNames
     */
    void addReviewerToRecipients(Element recipients, Protocol protocol, List<String> userNames);
    

    String getTransFormData(Protocol protocol, StreamSource xsltSource) throws Exception;

}
