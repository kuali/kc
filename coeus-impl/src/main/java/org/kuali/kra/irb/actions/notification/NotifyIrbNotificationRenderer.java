/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import java.util.Map;

/**
 * Renders additional fields for the Notify IRB notification.
 */
public class NotifyIrbNotificationRenderer extends IRBNotificationRenderer {

    private static final long serialVersionUID = -5315801471642797815L;
    
    private String actionComments;

    /**
     * Constructs a Notify IRB notification renderer.
     * @param protocol
     * @param actionComments
     */
    public NotifyIrbNotificationRenderer(Protocol protocol, String actionComments) {
        super(protocol);
        
        this.actionComments = actionComments;
    }
    
    public String getActionComments() {
        return actionComments;
    }

    public void setActionComments(String actionComments) {
        this.actionComments = actionComments;
    }
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{ACTION_COMMENTS}", getSafeMessage("{ACTION_COMMENTS}", actionComments));

        return params;
    }

}
