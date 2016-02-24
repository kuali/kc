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
package org.kuali.kra.protocol.actions;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;

public interface ProtocolActionRequestService {

    public void createProtocol(ProtocolFormBase protocolForm) throws Exception;
    
    public void rejectedInRouting(ProtocolBase protocol) throws Exception;

    public void recalledInRouting(ProtocolBase protocol) throws Exception;

    public boolean checkToSendNotification(ProtocolFormBase protocolForm, ProtocolNotificationRequestBeanBase notificationRequestBean, String promptAfterNotification);
    
    /**
     * This method checks the document state to see if something has changed between the time the user
     * loaded the document to when they clicked on an action.
     * @param protocolForm
     * @return
     */
    public boolean hasDocumentStateChanged(ProtocolFormBase protocolForm);
    
    public ProtocolCorrespondence getProtocolCorrespondence (ProtocolFormBase protocolForm, String forwardName, ProtocolNotificationRequestBeanBase notificationRequestBean, boolean holdingPage);
    
}
