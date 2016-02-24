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
package org.kuali.kra.irb.actions.notification;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Renders additional fields for the Protocol Suspended by DSMB notification.
 */
public class ProtocolClosedNotificationRenderer extends IRBNotificationRenderer {

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
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{CLOSED_DATE}", (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(new Date()));
        params.put("{LAST_ACTION_DESCRIPTION}", notificationRequestBean.getDescription());
        return params;
    }
}
