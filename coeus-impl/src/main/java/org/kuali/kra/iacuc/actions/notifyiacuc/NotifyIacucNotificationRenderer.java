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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;

import java.util.Map;

/**
 * Renders additional fields for the Notify IRB notification.
 */
public class NotifyIacucNotificationRenderer extends IacucProtocolNotificationRenderer {

    private String actionComments;

    /**
     * Constructs a Notify IRB notification renderer.
     * @param protocol
     * @param actionComments
     */
    public NotifyIacucNotificationRenderer(IacucProtocol protocol, String actionComments) {
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
