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
package org.kuali.kra.protocol.notification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.kuali.kra.protocol.Protocol;

/**
 * Renders additional fields for the Protocol Suspended by DSMB notification.
 */
public class ProtocolClosedNotificationRenderer extends ProtocolNotificationRenderer {

    private ProtocolNotificationRequestBean notificationRequestBean;
    
    /**
     * Constructs a Protocol Suspended by DSMB notification renderer.
     * @param protocol
     * @param actionComments
     */
    public ProtocolClosedNotificationRenderer(Protocol protocol, ProtocolNotificationRequestBean notificationRequestBean) {
        super(protocol);
        this.notificationRequestBean = notificationRequestBean;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{CLOSED_DATE}", (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(new Date()));
        params.put("{LAST_ACTION_DESCRIPTION}", notificationRequestBean.getDescription());
        return params;
    }
}
